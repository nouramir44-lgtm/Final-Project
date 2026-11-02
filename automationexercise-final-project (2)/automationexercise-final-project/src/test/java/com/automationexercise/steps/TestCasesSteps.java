package com.automationexercise.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class TestCasesSteps {

    private final TestContext context;

    public TestCasesSteps(TestContext context) {
        this.context = context;
    }

    @When("the user clicks Test Cases")
    public void theUserClicksTestCases() {
        context.home().clickTestCases();
    }

    @Then("the Test Cases page loads successfully")
    public void theTestCasesPageLoadsSuccessfully() {
        Assert.assertTrue(context.testCases().isUrlCorrect(), "The URL does not point to the Test Cases page");
        Assert.assertTrue(context.testCases().getHeadingText().toLowerCase().contains("test cases"),
                "The Test Cases heading was not displayed");
        Assert.assertTrue(context.testCases().getTestCaseCount() > 0, "No test cases were listed");
    }
}
