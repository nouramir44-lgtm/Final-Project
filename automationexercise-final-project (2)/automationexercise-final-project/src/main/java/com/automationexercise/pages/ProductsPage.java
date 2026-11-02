package com.automationexercise.pages;

import com.automationexercise.utils.AdHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/** All Products / Searched Products page, including the "added to cart" confirmation modal. */
public class ProductsPage extends BasePage {

    private static final By HEADING = By.cssSelector(".features_items h2.title");
    private static final By PRODUCT_CARDS = By.cssSelector(".features_items .product-image-wrapper");
    private static final By PRODUCT_NAME = By.xpath(".//div[contains(@class,'productinfo')]/p");
    private static final By PRODUCT_PRICE = By.xpath(".//div[contains(@class,'productinfo')]/h2");
    private static final By OVERLAY_ADD_TO_CART =
            By.xpath(".//div[contains(@class,'product-overlay')]//a[contains(@class,'add-to-cart')]");
    private static final By VIEW_PRODUCT_LINK = By.xpath(".//a[contains(@href,'/product_details/')]");
    private static final By SEARCH_INPUT = By.id("search_product");
    private static final By SEARCH_BUTTON = By.id("submit_search");
    private static final By ADDED_MESSAGE = By.xpath("//div[@id='cartModal']//p[contains(.,'added to cart')]");
    private static final By VIEW_CART_LINK = By.xpath("//div[@id='cartModal']//a[@href='/view_cart']");

    public ProductsPage(WebDriver driver, AdHandler adHandler) {
        super(driver, adHandler);
    }

    public String getHeadingText() {
        return getText(HEADING);
    }

    public int getProductCount() {
        return driver.findElements(PRODUCT_CARDS).size();
    }

    /** Waits until at least one product card is present and returns the count. */
    public int waitForProductList() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCT_CARDS));
        return getProductCount();
    }

    public String getProductName(int index) {
        return card(index).findElement(PRODUCT_NAME).getText().trim();
    }

    public String getProductPrice(int index) {
        return card(index).findElement(PRODUCT_PRICE).getText().trim();
    }

    public void openProductDetails(int index) {
        adHandler.handleAll();
        WebElement link = card(index).findElement(VIEW_PRODUCT_LINK);
        scrollIntoView(link);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(link)).click();
        } catch (WebDriverException e) {
            adHandler.handleAll();
            jsClick(link);
        }
    }

    public void hoverAndAddToCart(int index) {
        adHandler.handleAll();
        WebElement card = card(index);
        scrollIntoView(card);
        new Actions(driver).moveToElement(card).perform();
        WebElement addButton = card.findElement(OVERLAY_ADD_TO_CART);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        } catch (WebDriverException e) {
            adHandler.handleAll();
            jsClick(addButton);
        }
    }

    public String getAddedMessage() {
        return getText(ADDED_MESSAGE);
    }

    public void clickViewCart() {
        click(VIEW_CART_LINK);
    }

    public void searchFor(String text) {
        type(SEARCH_INPUT, text);
        click(SEARCH_BUTTON);
        wait.until(d -> getHeadingText().toLowerCase().contains("searched products"));
    }

    private WebElement card(int index) {
        List<WebElement> cards = driver.findElements(PRODUCT_CARDS);
        if (index >= cards.size()) {
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(PRODUCT_CARDS, index));
            cards = driver.findElements(PRODUCT_CARDS);
        }
        return cards.get(index);
    }
}
