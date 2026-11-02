package com.automationexercise.pages;

import com.automationexercise.config.ConfigReader;
import com.automationexercise.utils.AdHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/** Shared, reusable browser actions. Every page object extends this class. */
public abstract class BasePage {

    private static final int MAX_CLICK_ATTEMPTS = 3;

    protected final WebDriver driver;
    protected final AdHandler adHandler;
    protected final FluentWait<WebDriver> wait;
    private final String baseUrl;

    protected BasePage(WebDriver driver, AdHandler adHandler) {
        this.driver = driver;
        this.adHandler = adHandler;
        this.baseUrl = ConfigReader.get("base.url", "https://automationexercise.com");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("timeout.seconds", 15)))
                .ignoring(StaleElementReferenceException.class);
    }

    protected void open(String path) {
        driver.get(baseUrl + path);
        adHandler.handleAll();
    }

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /** Clicks an element; on interception it re-handles popups/ads and retries before using a JS click. */
    protected void click(By locator) {
        for (int attempt = 1; attempt <= MAX_CLICK_ATTEMPTS; attempt++) {
            adHandler.handleAll();
            try {
                wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
                return;
            } catch (ElementNotInteractableException | StaleElementReferenceException e) {
                // ElementClickInterceptedException is a subclass of ElementNotInteractableException
                // an ad or popup was in the way: handle it again on the next loop
            }
        }
        adHandler.handleAll();
        jsClick(driver.findElement(locator));
    }

    protected void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    protected void type(By locator, String text) {
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void selectByVisibleText(By locator, String text) {
        new Select(waitForVisible(locator)).selectByVisibleText(text);
    }

    protected void selectByValue(By locator, String value) {
        new Select(waitForVisible(locator)).selectByValue(value);
    }

    protected String getText(By locator) {
        return waitForVisible(locator).getText().trim();
    }

    /** Immediate check, no waiting. */
    protected boolean isDisplayed(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }

    protected boolean waitUntilDisplayed(By locator) {
        try {
            waitForVisible(locator);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean waitForUrlContains(String fragment) {
        try {
            wait.until(ExpectedConditions.urlContains(fragment));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /** True when the browser's built-in (HTML5) validation rejects the field's current value. */
    protected boolean isFieldInvalid(By locator) {
        WebElement element = waitForVisible(locator);
        Object valid = ((JavascriptExecutor) driver).executeScript("return arguments[0].checkValidity();", element);
        return Boolean.FALSE.equals(valid);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
