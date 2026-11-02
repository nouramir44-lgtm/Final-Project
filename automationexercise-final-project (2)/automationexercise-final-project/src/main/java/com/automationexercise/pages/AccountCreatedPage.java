package com.automationexercise.pages;

import com.automationexercise.utils.AdHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** "ACCOUNT CREATED!" confirmation page. */
public class AccountCreatedPage extends BasePage {

    private static final By HEADING = By.cssSelector("h2[data-qa='account-created']");
    private static final By CONTINUE_BUTTON = By.cssSelector("a[data-qa='continue-button']");

    public AccountCreatedPage(WebDriver driver, AdHandler adHandler) {
        super(driver, adHandler);
    }

    public String getHeadingText() {
        adHandler.handleAll(); // the Google vignette often appears right after account creation
        return getText(HEADING);
    }

    public void clickContinue() {
        click(CONTINUE_BUTTON);
    }
}
