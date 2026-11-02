package com.automationexercise.pages;

import com.automationexercise.utils.AdHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/** Checkout page: address details, order review, order comment and "Place Order". */
public class CheckoutPage extends BasePage {

    private static final By DELIVERY_ADDRESS = By.id("address_delivery");
    private static final By BILLING_ADDRESS = By.id("address_invoice");
    private static final By REVIEW_PRODUCT_NAMES = By.xpath("//div[@id='cart_info']//td[@class='cart_description']//a");
    private static final By COMMENT = By.cssSelector("textarea[name='message']");
    private static final By PLACE_ORDER = By.cssSelector("a[href='/payment']");

    public CheckoutPage(WebDriver driver, AdHandler adHandler) {
        super(driver, adHandler);
    }

    public boolean isDisplayed() {
        return waitForUrlContains("/checkout") && waitUntilDisplayed(DELIVERY_ADDRESS);
    }

    public String getDeliveryAddress() {
        return getText(DELIVERY_ADDRESS);
    }

    public String getBillingAddress() {
        return getText(BILLING_ADDRESS);
    }

    public List<String> getReviewedProductNames() {
        waitUntilDisplayed(REVIEW_PRODUCT_NAMES);
        return driver.findElements(REVIEW_PRODUCT_NAMES).stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();
    }

    public void enterComment(String comment) {
        type(COMMENT, comment);
    }

    public void clickPlaceOrder() {
        click(PLACE_ORDER);
    }
}
