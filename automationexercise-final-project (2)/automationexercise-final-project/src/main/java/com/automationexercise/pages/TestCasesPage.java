package com.automationexercise.pages;

import com.automationexercise.utils.AdHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** The site's Test Cases page. */
public class TestCasesPage extends BasePage {

    private static final By HEADING = By.xpath("//h2[contains(@class,'title')]");
    private static final By TEST_CASE_ENTRIES = By.xpath(
            "//h4[contains(@class,'panel-title')] | //u[contains(normalize-space(.),'Test Case')]");

    public TestCasesPage(WebDriver driver, AdHandler adHandler) {
        super(driver, adHandler);
    }

    public boolean isUrlCorrect() {
        return waitForUrlContains("/test_cases");
    }

    public String getHeadingText() {
        return getText(HEADING);
    }

    public int getTestCaseCount() {
        waitUntilDisplayed(TEST_CASE_ENTRIES);
        return driver.findElements(TEST_CASE_ENTRIES).size();
    }
}
