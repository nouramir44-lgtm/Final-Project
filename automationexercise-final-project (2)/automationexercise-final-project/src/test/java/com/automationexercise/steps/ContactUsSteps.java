package com.automationexercise.steps;

import com.automationexercise.utils.TestDataFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ContactUsSteps {

    private final TestContext context;

    public ContactUsSteps(TestContext context) {
        this.context = context;
    }

    @When("the user opens the Contact Us page")
    public void theUserOpensTheContactUsPage() {
        context.home().clickContactUs();
        Assert.assertTrue(context.contactUs().isDisplayed(), "The Contact Us page was not displayed");
    }

    @When("the user enters valid contact form data")
    public void theUserEntersValidContactFormData() throws IOException {
        context.contactUs().fillForm(TestDataFactory.validContactMessage());
        Path attachment = Files.createTempFile("contact-upload", ".txt");
        Files.writeString(attachment, "Attachment created by the automated test suite.");
        attachment.toFile().deleteOnExit();
        context.contactUs().uploadFile(attachment.toAbsolutePath().toString());
    }

    @When("the user enters contact form data with an invalid email address")
    public void theUserEntersContactFormDataWithAnInvalidEmailAddress() {
        context.contactUs().fillForm(TestDataFactory.invalidContactMessage());
    }

    @When("the user submits the contact form")
    public void theUserSubmitsTheContactForm() {
        context.contactUs().submit();
    }

    @Then("the contact confirmation {string} is displayed")
    public void theContactConfirmationIsDisplayed(String expected) {
        Assert.assertTrue(context.contactUs().isSuccessMessageDisplayed(),
                "The contact success message was not displayed");
        Assert.assertEquals(context.contactUs().getSuccessMessage(), expected);
    }

    @Then("the contact form is not submitted")
    public void theContactFormIsNotSubmitted() {
        Assert.assertTrue(context.contactUs().isEmailRejectedByForm(),
                "The form should reject the invalid email address");
        Assert.assertFalse(context.contactUs().isSuccessMessageDisplayed(),
                "The form must not be submitted with an invalid email address");
    }
}
