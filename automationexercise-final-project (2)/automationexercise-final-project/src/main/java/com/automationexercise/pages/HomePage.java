package com.automationexercise.pages;

import com.automationexercise.utils.AdHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** Home page, which also owns the header navigation shared by every page. */
public class HomePage extends BasePage {

    private static final By SIGNUP_LOGIN_LINK = By.cssSelector(".shop-menu a[href='/login']");
    private static final By PRODUCTS_LINK = By.cssSelector(".shop-menu a[href='/products']");
    private static final By CART_LINK = By.cssSelector(".shop-menu a[href='/view_cart']");
    private static final By CONTACT_US_LINK = By.cssSelector(".shop-menu a[href='/contact_us']");
    private static final By TEST_CASES_LINK = By.cssSelector(".shop-menu a[href='/test_cases']");
    private static final By LOGOUT_LINK = By.cssSelector(".shop-menu a[href='/logout']");
    private static final By LOGGED_IN_LABEL = By.xpath("//a[contains(.,'Logged in as')]");

    public HomePage(WebDriver driver, AdHandler adHandler) {
        super(driver, adHandler);
    }

    public void open() {
        open("/");
    }

    public boolean isHomePageVisible() {
        try {
            wait.until(ExpectedConditions.titleContains("Automation Exercise"));
            return waitUntilDisplayed(SIGNUP_LOGIN_LINK) || waitUntilDisplayed(LOGOUT_LINK);
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickSignupLogin() {
        click(SIGNUP_LOGIN_LINK);
    }

    public void clickProducts() {
        click(PRODUCTS_LINK);
    }

    public void clickCart() {
        click(CART_LINK);
    }

    public void clickContactUs() {
        click(CONTACT_US_LINK);
    }

    public void clickTestCases() {
        click(TEST_CASES_LINK);
    }

    public void clickLogout() {
        click(LOGOUT_LINK);
    }

    public boolean isLoggedIn() {
        return waitUntilDisplayed(LOGGED_IN_LABEL);
    }

    /** Returns the header label, e.g. "Logged in as AutoUser123". */
    public String getLoggedInText() {
        return getText(LOGGED_IN_LABEL);
    }
}
