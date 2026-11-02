package com.automationexercise.steps;

import com.automationexercise.driver.DriverFactory;
import com.automationexercise.model.User;
import com.automationexercise.pages.AccountCreatedPage;
import com.automationexercise.pages.AccountInformationPage;
import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.CheckoutPage;
import com.automationexercise.pages.ContactUsPage;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoginSignupPage;
import com.automationexercise.pages.PaymentPage;
import com.automationexercise.pages.ProductDetailsPage;
import com.automationexercise.pages.ProductsPage;
import com.automationexercise.pages.TestCasesPage;
import com.automationexercise.utils.AdHandler;
import org.openqa.selenium.WebDriver;

/** Per-scenario state shared by hooks and step definitions (injected by PicoContainer). */
public class TestContext {

    private WebDriver driver;
    private AdHandler adHandler;

    private HomePage homePage;
    private LoginSignupPage loginSignupPage;
    private AccountInformationPage accountInformationPage;
    private AccountCreatedPage accountCreatedPage;
    private ProductsPage productsPage;
    private ProductDetailsPage productDetailsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private PaymentPage paymentPage;
    private ContactUsPage contactUsPage;
    private TestCasesPage testCasesPage;

    private User user;
    private String selectedProductName;
    private String selectedProductPrice;

    public void initDriver() {
        driver = DriverFactory.createDriver();
        adHandler = new AdHandler(driver);
        homePage = new HomePage(driver, adHandler);
        loginSignupPage = new LoginSignupPage(driver, adHandler);
        accountInformationPage = new AccountInformationPage(driver, adHandler);
        accountCreatedPage = new AccountCreatedPage(driver, adHandler);
        productsPage = new ProductsPage(driver, adHandler);
        productDetailsPage = new ProductDetailsPage(driver, adHandler);
        cartPage = new CartPage(driver, adHandler);
        checkoutPage = new CheckoutPage(driver, adHandler);
        paymentPage = new PaymentPage(driver, adHandler);
        contactUsPage = new ContactUsPage(driver, adHandler);
        testCasesPage = new TestCasesPage(driver, adHandler);
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver driver() {
        return driver;
    }

    public HomePage home() {
        return homePage;
    }

    public LoginSignupPage loginSignup() {
        return loginSignupPage;
    }

    public AccountInformationPage accountInformation() {
        return accountInformationPage;
    }

    public AccountCreatedPage accountCreated() {
        return accountCreatedPage;
    }

    public ProductsPage products() {
        return productsPage;
    }

    public ProductDetailsPage productDetails() {
        return productDetailsPage;
    }

    public CartPage cart() {
        return cartPage;
    }

    public CheckoutPage checkout() {
        return checkoutPage;
    }

    public PaymentPage payment() {
        return paymentPage;
    }

    public ContactUsPage contactUs() {
        return contactUsPage;
    }

    public TestCasesPage testCases() {
        return testCasesPage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSelectedProductName() {
        return selectedProductName;
    }

    public String getSelectedProductPrice() {
        return selectedProductPrice;
    }

    public void setSelectedProduct(String name, String price) {
        this.selectedProductName = name;
        this.selectedProductPrice = price;
    }
}
