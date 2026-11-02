package com.automationexercise.steps;

import com.automationexercise.model.User;
import com.automationexercise.utils.TestDataFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class RegistrationSteps {

    private final TestContext context;

    public RegistrationSteps(TestContext context) {
        this.context = context;
    }

    @When("the user enters a new name and email address in the Signup section")
    public void theUserEntersANewNameAndEmailAddress() {
        User user = TestDataFactory.newUser();
        context.setUser(user);
        context.loginSignup().enterSignupDetails(user.name(), user.email());
    }

    @When("the user enters a new name and the existing email address in the Signup section")
    public void theUserEntersANewNameAndTheExistingEmailAddress() {
        context.loginSignup().enterSignupDetails("Another User", context.getUser().email());
    }

    @When("the user clicks the Signup button")
    public void theUserClicksTheSignupButton() {
        context.loginSignup().clickSignup();
    }

    @When("the user fills all required fields on the account creation page")
    public void theUserFillsAllRequiredFields() {
        Assert.assertTrue(context.accountInformation().isDisplayed(),
                "The account information page was not displayed after Signup");
        context.accountInformation().fillAccountInformation(context.getUser());
    }

    @When("the user clicks Create Account")
    public void theUserClicksCreateAccount() {
        context.accountInformation().clickCreateAccount();
    }

    @Then("the {string} message appears")
    public void theMessageAppears(String expected) {
        Assert.assertEquals(context.accountCreated().getHeadingText().toUpperCase(), expected.toUpperCase());
    }
}
