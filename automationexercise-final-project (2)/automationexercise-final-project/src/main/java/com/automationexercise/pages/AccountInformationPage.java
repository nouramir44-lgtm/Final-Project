package com.automationexercise.pages;

import com.automationexercise.model.User;
import com.automationexercise.utils.AdHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** "Enter Account Information" form shown after a successful signup step. */
public class AccountInformationPage extends BasePage {

    private static final By HEADING = By.xpath("//b[normalize-space()='Enter Account Information']");
    private static final By TITLE_MR = By.id("id_gender1");
    private static final By TITLE_MRS = By.id("id_gender2");
    private static final By PASSWORD = By.cssSelector("input[data-qa='password']");
    private static final By DAY = By.cssSelector("select[data-qa='days']");
    private static final By MONTH = By.cssSelector("select[data-qa='months']");
    private static final By YEAR = By.cssSelector("select[data-qa='years']");
    private static final By NEWSLETTER = By.id("newsletter");
    private static final By SPECIAL_OFFERS = By.id("optin");
    private static final By FIRST_NAME = By.cssSelector("input[data-qa='first_name']");
    private static final By LAST_NAME = By.cssSelector("input[data-qa='last_name']");
    private static final By COMPANY = By.cssSelector("input[data-qa='company']");
    private static final By ADDRESS_1 = By.cssSelector("input[data-qa='address']");
    private static final By ADDRESS_2 = By.cssSelector("input[data-qa='address2']");
    private static final By COUNTRY = By.cssSelector("select[data-qa='country']");
    private static final By STATE = By.cssSelector("input[data-qa='state']");
    private static final By CITY = By.cssSelector("input[data-qa='city']");
    private static final By ZIPCODE = By.cssSelector("input[data-qa='zipcode']");
    private static final By MOBILE = By.cssSelector("input[data-qa='mobile_number']");
    private static final By CREATE_ACCOUNT = By.cssSelector("button[data-qa='create-account']");

    public AccountInformationPage(WebDriver driver, AdHandler adHandler) {
        super(driver, adHandler);
    }

    public boolean isDisplayed() {
        return waitUntilDisplayed(HEADING);
    }

    public void fillAccountInformation(User user) {
        jsClick(waitForVisible("Mrs".equalsIgnoreCase(user.title()) ? TITLE_MRS : TITLE_MR));
        type(PASSWORD, user.password());
        selectByValue(DAY, user.birthDay());
        selectByVisibleText(MONTH, user.birthMonth());
        selectByValue(YEAR, user.birthYear());
        jsClick(waitForVisible(NEWSLETTER));
        jsClick(waitForVisible(SPECIAL_OFFERS));
        type(FIRST_NAME, user.firstName());
        type(LAST_NAME, user.lastName());
        type(COMPANY, user.company());
        type(ADDRESS_1, user.address1());
        type(ADDRESS_2, user.address2());
        selectByVisibleText(COUNTRY, user.country());
        type(STATE, user.state());
        type(CITY, user.city());
        type(ZIPCODE, user.zipcode());
        type(MOBILE, user.mobileNumber());
    }

    public void clickCreateAccount() {
        click(CREATE_ACCOUNT);
    }
}
