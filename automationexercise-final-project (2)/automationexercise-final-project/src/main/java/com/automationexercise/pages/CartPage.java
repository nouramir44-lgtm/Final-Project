package com.automationexercise.pages;

import com.automationexercise.utils.AdHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/** Shopping cart page, including the "Register / Login" prompt shown to guests at checkout. */
public class CartPage extends BasePage {

    private static final By CART_PRODUCT_NAMES = By.xpath("//td[@class='cart_description']//a");
    private static final By EMPTY_CART_MESSAGE = By.id("empty_cart");
    private static final By PROCEED_TO_CHECKOUT = By.cssSelector("a.check_out");
    private static final By LOGIN_PROMPT = By.xpath("//div[@id='checkoutModal']//p[contains(.,'Register / Login')]");
    private static final By LOGIN_PROMPT_LINK = By.xpath("//div[@id='checkoutModal']//a[@href='/login']");

    public CartPage(WebDriver driver, AdHandler adHandler) {
        super(driver, adHandler);
    }

    public boolean isCartPageDisplayed() {
        return waitForUrlContains("/view_cart");
    }

    public List<String> getProductNames() {
        waitUntilDisplayed(CART_PRODUCT_NAMES);
        return driver.findElements(CART_PRODUCT_NAMES).stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();
    }

    public boolean isCartEmpty() {
        return waitUntilDisplayed(EMPTY_CART_MESSAGE);
    }

    public String getEmptyCartMessage() {
        return getText(EMPTY_CART_MESSAGE);
    }

    public boolean isProceedToCheckoutAvailable() {
        return !driver.findElements(PROCEED_TO_CHECKOUT).isEmpty();
    }

    public void clickProceedToCheckout() {
        click(PROCEED_TO_CHECKOUT);
    }

    public boolean isLoginPromptDisplayed() {
        return waitUntilDisplayed(LOGIN_PROMPT);
    }

    public void clickRegisterLoginInPrompt() {
        click(LOGIN_PROMPT_LINK);
    }
}
