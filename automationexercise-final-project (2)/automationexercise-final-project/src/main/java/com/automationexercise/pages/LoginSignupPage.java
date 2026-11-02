package com.automationexercise.pages;

import com.automationexercise.utils.AdHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** The Signup / Login page with its "New User Signup!" and "Login to your account" forms. */
public class LoginSignupPage extends BasePage {

    private static final By SIGNUP_NAME = By.cssSelector("input[data-qa='signup-name']");
    private static final By SIGNUP_EMAIL = By.cssSelector("input[data-qa='signup-email']");
    private static final By SIGNUP_BUTTON = By.cssSelector("button[data-qa='signup-button']");
    private static final By LOGIN_EMAIL = By.cssSelector("input[data-qa='login-email']");
    private static final By LOGIN_PASSWORD = By.cssSelector("input[data-qa='login-password']");
    private static final By LOGIN_BUTTON = By.cssSelector("button[data-qa='login-button']");

    public LoginSignupPage(WebDriver driver, AdHandler adHandler) {
        super(driver, adHandler);
    }

    public boolean isLoginPageDisplayed() {
        return waitForUrlContains("/login") && waitUntilDisplayed(LOGIN_EMAIL);
    }

    public void enterSignupDetails(String name, String email) {
        type(SIGNUP_NAME, name);
        type(SIGNUP_EMAIL, email);
    }

    public void clickSignup() {
        click(SIGNUP_BUTTON);
    }

    public void enterLoginDetails(String email, String password) {
        type(LOGIN_EMAIL, email);
        type(LOGIN_PASSWORD, password);
    }

    public void clickLogin() {
        click(LOGIN_BUTTON);
    }

    public void login(String email, String password) {
        enterLoginDetails(email, password);
        clickLogin();
    }

    /** Checks for an error paragraph (login or signup form) that contains the given text. */
    public boolean isErrorDisplayed(String message) {
        return waitUntilDisplayed(By.xpath("//p[contains(normalize-space(.), '" + message + "')]"));
    }
}
