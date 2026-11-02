package com.automationexercise.steps;

import com.automationexercise.model.User;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {

    private final TestContext context;

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    @When("the user logs in with the registered email and password")
    public void theUserLogsInWithTheRegisteredEmailAndPassword() {
        User user = context.getUser();
        context.loginSignup().login(user.email(), user.password());
    }

    @When("the user logs in with the registered email and an incorrect password")
    public void theUserLogsInWithAnIncorrectPassword() {
        context.loginSignup().login(context.getUser().email(), "WrongPassword#999");
    }

    @When("the user logs in with email {string} and password {string}")
    public void theUserLogsInWithEmailAndPassword(String email, String password) {
        context.loginSignup().login(email, password);
    }

    @When("the user clicks Logout")
    public void theUserClicksLogout() {
        context.home().clickLogout();
    }

    @Then("the user sees the message {string}")
    public void theUserSeesTheMessage(String expected) {
        String expectedText = expected.replace("<username>", context.getUser().name());
        Assert.assertTrue(context.home().isLoggedIn(), "The logged-in label was not displayed");
        Assert.assertTrue(context.home().getLoggedInText().contains(expectedText),
                "Expected the header to contain: " + expectedText);
    }

    @Then("the error {string} is displayed")
    public void theErrorIsDisplayed(String message) {
        Assert.assertTrue(context.loginSignup().isErrorDisplayed(message),
                "Error message not displayed: " + message);
    }

    @Then("the user is redirected to the login and signup page")
    public void theUserIsRedirectedToTheLoginAndSignupPage() {
        Assert.assertTrue(context.loginSignup().isLoginPageDisplayed(),
                "The user was not redirected to the login and signup page");
    }
}
