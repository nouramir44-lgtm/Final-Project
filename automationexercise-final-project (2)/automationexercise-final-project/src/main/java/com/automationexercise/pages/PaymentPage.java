package com.automationexercise.pages;

import com.automationexercise.model.PaymentDetails;
import com.automationexercise.utils.AdHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/** Payment form and the order confirmation that follows it. */
public class PaymentPage extends BasePage {

    private static final By NAME_ON_CARD = By.cssSelector("input[data-qa='name-on-card']");
    private static final By CARD_NUMBER = By.cssSelector("input[data-qa='card-number']");
    private static final By CVC = By.cssSelector("input[data-qa='cvc']");
    private static final By EXPIRY_MONTH = By.cssSelector("input[data-qa='expiry-month']");
    private static final By EXPIRY_YEAR = By.cssSelector("input[data-qa='expiry-year']");
    private static final By PAY_BUTTON = By.cssSelector("button[data-qa='pay-button']");
    private static final By SUCCESS_ALERT = By.cssSelector("#success_message .alert-success");
    private static final By ORDER_PLACED_HEADING = By.cssSelector("h2[data-qa='order-placed']");

    public PaymentPage(WebDriver driver, AdHandler adHandler) {
        super(driver, adHandler);
    }

    public boolean isDisplayed() {
        return waitForUrlContains("/payment") && waitUntilDisplayed(NAME_ON_CARD);
    }

    public void enterPaymentDetails(PaymentDetails details) {
        type(NAME_ON_CARD, details.nameOnCard());
        type(CARD_NUMBER, details.cardNumber());
        type(CVC, details.cvc());
        type(EXPIRY_MONTH, details.expiryMonth());
        type(EXPIRY_YEAR, details.expiryYear());
    }

    public void clickPayAndConfirmOrder() {
        click(PAY_BUTTON);
    }

    /**
     * True when the green "Your order has been placed successfully!" alert is shown, or the
     * "ORDER PLACED!" confirmation page is reached (the alert can disappear quickly on redirect).
     */
    public boolean isOrderPlaced() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(20))
                    .ignoring(StaleElementReferenceException.class)
                    .until(d -> isDisplayed(SUCCESS_ALERT)
                            || isDisplayed(ORDER_PLACED_HEADING)
                            || d.getCurrentUrl().contains("payment_done"));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isCardNumberRejectedByForm() {
        return isFieldInvalid(CARD_NUMBER);
    }
}
