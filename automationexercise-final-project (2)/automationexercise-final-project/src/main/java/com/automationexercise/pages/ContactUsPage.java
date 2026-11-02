package com.automationexercise.pages;

import com.automationexercise.model.ContactMessage;
import com.automationexercise.utils.AdHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/** Contact Us ("Get In Touch") form. */
public class ContactUsPage extends BasePage {

    private static final By HEADING = By.cssSelector(".contact-form h2.title");
    private static final By NAME = By.cssSelector("input[data-qa='name']");
    private static final By EMAIL = By.cssSelector("input[data-qa='email']");
    private static final By SUBJECT = By.cssSelector("input[data-qa='subject']");
    private static final By MESSAGE = By.cssSelector("textarea[data-qa='message']");
    private static final By UPLOAD = By.cssSelector("input[name='upload_file']");
    private static final By SUBMIT = By.cssSelector("input[data-qa='submit-button']");
    private static final By SUCCESS_MESSAGE = By.cssSelector(".status.alert-success");

    public ContactUsPage(WebDriver driver, AdHandler adHandler) {
        super(driver, adHandler);
    }

    public boolean isDisplayed() {
        return waitForUrlContains("/contact_us") && waitUntilDisplayed(HEADING);
    }

    public void fillForm(ContactMessage contact) {
        type(NAME, contact.name());
        type(EMAIL, contact.email());
        type(SUBJECT, contact.subject());
        type(MESSAGE, contact.message());
    }

    public void uploadFile(String absolutePath) {
        driver.findElement(UPLOAD).sendKeys(absolutePath);
    }

    /** Clicks Submit and accepts the browser confirmation ("Press OK to proceed!") when it appears. */
    public void submit() {
        click(SUBMIT);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.alertIsPresent())
                    .accept();
        } catch (TimeoutException ignored) {
            // no confirmation: the browser blocked the submission (invalid form data)
        }
    }

    public boolean isSuccessMessageDisplayed() {
        return waitUntilDisplayed(SUCCESS_MESSAGE);
    }

    public String getSuccessMessage() {
        return getText(SUCCESS_MESSAGE);
    }

    public boolean isEmailRejectedByForm() {
        return isFieldInvalid(EMAIL);
    }
}
