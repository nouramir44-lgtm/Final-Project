package com.automationexercise.pages;

import com.automationexercise.utils.AdHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Single product details page. */
public class ProductDetailsPage extends BasePage {

    private static final By NAME = By.cssSelector(".product-information h2");
    private static final By PRICE = By.cssSelector(".product-information span span");
    private static final By CATEGORY = By.xpath("//div[@class='product-information']/p[contains(.,'Category')]");
    private static final By AVAILABILITY = By.xpath("//div[@class='product-information']/p[contains(.,'Availability')]");
    private static final By CONDITION = By.xpath("//div[@class='product-information']/p[contains(.,'Condition')]");
    private static final By BRAND = By.xpath("//div[@class='product-information']/p[contains(.,'Brand')]");

    public ProductDetailsPage(WebDriver driver, AdHandler adHandler) {
        super(driver, adHandler);
    }

    public boolean isDisplayed() {
        return waitForUrlContains("/product_details/") && waitUntilDisplayed(NAME);
    }

    public String getName() {
        return getText(NAME);
    }

    public String getPrice() {
        return getText(PRICE);
    }

    public String getCategory() {
        return getText(CATEGORY);
    }

    public String getAvailability() {
        return getText(AVAILABILITY);
    }

    public String getCondition() {
        return getText(CONDITION);
    }

    public String getBrand() {
        return getText(BRAND);
    }
}
