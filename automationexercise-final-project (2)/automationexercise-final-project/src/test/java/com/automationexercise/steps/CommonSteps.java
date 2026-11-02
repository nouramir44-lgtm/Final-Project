package com.automationexercise.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CommonSteps {

    private final TestContext context;
    private final AccountSetup accountSetup;

    public CommonSteps(TestContext context, AccountSetup accountSetup) {
        this.context = context;
        this.accountSetup = accountSetup;
    }

    @Given("a registered user exists")
    public void aRegisteredUserExists() {
        accountSetup.createRegisteredUser();
    }

    @Given("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        context.home().open();
        Assert.assertTrue(context.home().isHomePageVisible(), "Home page was not displayed");
    }

    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        accountSetup.loginThroughUi();
        Assert.assertTrue(context.home().isLoggedIn(), "User is not logged in");
    }

    @When("the user clicks on the Signup and Login link")
    public void theUserClicksOnTheSignupAndLoginLink() {
        context.home().clickSignupLogin();
    }
}
