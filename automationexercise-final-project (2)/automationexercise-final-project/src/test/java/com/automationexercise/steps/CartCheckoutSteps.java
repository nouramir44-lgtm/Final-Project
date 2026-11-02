package com.automationexercise.steps;

import com.automationexercise.model.User;
import com.automationexercise.utils.TestDataFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CartCheckoutSteps {

    private final TestContext context;

    public CartCheckoutSteps(TestContext context) {
        this.context = context;
    }

    @When("the user opens the cart")
    public void theUserOpensTheCart() {
        context.home().clickCart();
    }

    @Then("the cart contains the added product")
    public void theCartContainsTheAddedProduct() {
        Assert.assertTrue(context.cart().isCartPageDisplayed(), "The cart page was not displayed");
        Assert.assertTrue(context.cart().getProductNames().contains(context.getSelectedProductName()),
                "The cart does not contain: " + context.getSelectedProductName());
    }

    @Then("the cart is empty")
    public void theCartIsEmpty() {
        Assert.assertTrue(context.cart().isCartEmpty(), "The cart was expected to be empty");
        Assert.assertTrue(context.cart().getEmptyCartMessage().toLowerCase().contains("empty"));
    }

    @Then("the Proceed To Checkout option is not available")
    public void theProceedToCheckoutOptionIsNotAvailable() {
        Assert.assertFalse(context.cart().isProceedToCheckoutAvailable(),
                "Checkout must not be possible with an empty cart");
    }

    @When("the user proceeds to checkout")
    public void theUserProceedsToCheckout() {
        context.cart().clickProceedToCheckout();
    }

    @Then("the login required prompt is displayed")
    public void theLoginRequiredPromptIsDisplayed() {
        Assert.assertTrue(context.cart().isLoginPromptDisplayed(),
                "Guests must be asked to register or log in before checkout");
    }

    @When("the user chooses Register and Login from the prompt")
    public void theUserChoosesRegisterAndLoginFromThePrompt() {
        context.cart().clickRegisterLoginInPrompt();
    }

    @When("the user validates the delivery address and order review")
    public void theUserValidatesTheDeliveryAddressAndOrderReview() {
        User user = context.getUser();
        Assert.assertTrue(context.checkout().isDisplayed(), "The checkout page was not displayed");
        String delivery = context.checkout().getDeliveryAddress();
        Assert.assertTrue(delivery.contains(user.firstName()), "Delivery address is missing the first name");
        Assert.assertTrue(delivery.contains(user.lastName()), "Delivery address is missing the last name");
        Assert.assertTrue(delivery.contains(user.address1()), "Delivery address is missing the street address");
        Assert.assertTrue(delivery.contains(user.country()), "Delivery address is missing the country");
        Assert.assertTrue(context.checkout().getBillingAddress().contains(user.address1()),
                "Billing address is missing the street address");
        Assert.assertTrue(context.checkout().getReviewedProductNames().contains(context.getSelectedProductName()),
                "The order review does not list the added product");
    }

    @When("the user enters an order comment and clicks Place Order")
    public void theUserEntersAnOrderCommentAndClicksPlaceOrder() {
        context.checkout().enterComment("Automated test order - please ignore.");
        context.checkout().clickPlaceOrder();
        Assert.assertTrue(context.payment().isDisplayed(), "The payment page was not displayed");
    }

    @When("the user enters valid payment details and confirms the order")
    public void theUserEntersValidPaymentDetailsAndConfirmsTheOrder() {
        context.payment().enterPaymentDetails(TestDataFactory.validPayment());
        context.payment().clickPayAndConfirmOrder();
    }

    @When("the user submits the payment form with empty payment details")
    public void theUserSubmitsThePaymentFormWithEmptyPaymentDetails() {
        context.payment().enterPaymentDetails(TestDataFactory.invalidPayment());
        context.payment().clickPayAndConfirmOrder();
    }

    @Then("the order confirmation {string} is displayed")
    public void theOrderConfirmationIsDisplayed(String expectedMessage) {
        Assert.assertTrue(context.payment().isOrderPlaced(),
                "The order was not placed. Expected: " + expectedMessage);
    }

    @Then("the order is not placed")
    public void theOrderIsNotPlaced() {
        Assert.assertTrue(context.payment().isCardNumberRejectedByForm(),
                "The payment form should reject the empty card number");
        Assert.assertFalse(context.payment().getCurrentUrl().contains("payment_done"),
                "The order must not be confirmed with invalid payment details");
    }
}
