package com.automationexercise.steps;

import com.automationexercise.model.User;
import com.automationexercise.utils.TestDataFactory;
import com.automationexercise.utils.UserApi;

/** Precondition helpers: create a registered account and log in through the UI. */
public class AccountSetup {

    private final TestContext context;

    public AccountSetup(TestContext context) {
        this.context = context;
    }

    /** Creates a unique account through the REST API, falling back to the UI when the API is unavailable. */
    public void createRegisteredUser() {
        User user = TestDataFactory.newUser();
        context.setUser(user);
        if (!UserApi.createAccount(user)) {
            registerThroughUi(user);
        }
    }

    public void loginThroughUi() {
        User user = context.getUser();
        context.home().clickSignupLogin();
        context.loginSignup().login(user.email(), user.password());
    }

    private void registerThroughUi(User user) {
        context.home().open();
        context.home().clickSignupLogin();
        context.loginSignup().enterSignupDetails(user.name(), user.email());
        context.loginSignup().clickSignup();
        context.accountInformation().fillAccountInformation(user);
        context.accountInformation().clickCreateAccount();
        context.accountCreated().clickContinue();
        context.home().clickLogout();
    }
}
