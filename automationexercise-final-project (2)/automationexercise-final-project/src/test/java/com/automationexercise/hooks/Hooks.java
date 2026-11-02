package com.automationexercise.hooks;

import com.automationexercise.model.User;
import com.automationexercise.steps.TestContext;
import com.automationexercise.utils.UserApi;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

/** Scenario lifecycle: start a browser, attach screenshots on failure, delete test accounts, quit. */
public class Hooks {

    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void setUp() {
        context.initDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                attachScreenshot(scenario);
            }
            deleteTestAccount();
        } finally {
            context.quitDriver();
        }
    }

    private void attachScreenshot(Scenario scenario) {
        try {
            byte[] screenshot = ((TakesScreenshot) context.driver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure screenshot - " + scenario.getName());
        } catch (WebDriverException e) {
            scenario.log("Could not capture a screenshot: " + e.getMessage());
        }
    }

    private void deleteTestAccount() {
        User user = context.getUser();
        if (user != null) {
            UserApi.deleteAccount(user.email(), user.password());
        }
    }
}
