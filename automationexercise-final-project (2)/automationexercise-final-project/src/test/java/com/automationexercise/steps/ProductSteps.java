package com.automationexercise.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ProductSteps {

    private static final int FIRST_PRODUCT = 0;

    private final TestContext context;

    public ProductSteps(TestContext context) {
        this.context = context;
    }

    @When("the user opens the Products page")
    public void theUserOpensTheProductsPage() {
        context.home().clickProducts();
    }

    @Then("the {string} heading is displayed")
    public void theHeadingIsDisplayed(String expected) {
        Assert.assertEquals(context.products().getHeadingText().toUpperCase(), expected.toUpperCase());
    }

    @Then("the products list is displayed")
    public void theProductsListIsDisplayed() {
        Assert.assertTrue(context.products().waitForProductList() > 0, "No products were listed");
    }

    @When("the user opens the details of the first product")
    public void theUserOpensTheDetailsOfTheFirstProduct() {
        context.setSelectedProduct(
                context.products().getProductName(FIRST_PRODUCT),
                context.products().getProductPrice(FIRST_PRODUCT));
        context.products().openProductDetails(FIRST_PRODUCT);
    }

    @Then("the product details match the selected product")
    public void theProductDetailsMatchTheSelectedProduct() {
        var details = context.productDetails();
        Assert.assertTrue(details.isDisplayed(), "The product details page was not displayed");
        Assert.assertEquals(details.getName(), context.getSelectedProductName());
        Assert.assertEquals(details.getPrice(), context.getSelectedProductPrice());
        Assert.assertFalse(details.getCategory().isBlank(), "Category is missing");
        Assert.assertFalse(details.getAvailability().isBlank(), "Availability is missing");
        Assert.assertFalse(details.getCondition().isBlank(), "Condition is missing");
        Assert.assertFalse(details.getBrand().isBlank(), "Brand is missing");
    }

    @When("the user searches for {string}")
    public void theUserSearchesFor(String text) {
        context.products().searchFor(text);
    }

    @Then("no products are listed")
    public void noProductsAreListed() {
        Assert.assertEquals(context.products().getProductCount(), 0, "Products were listed for an invalid search");
    }

    @When("the user hovers over a product and clicks Add to Cart")
    public void theUserHoversOverAProductAndClicksAddToCart() {
        Assert.assertTrue(context.products().waitForProductList() > 0, "No products were listed");
        context.setSelectedProduct(
                context.products().getProductName(FIRST_PRODUCT),
                context.products().getProductPrice(FIRST_PRODUCT));
        context.products().hoverAndAddToCart(FIRST_PRODUCT);
    }

    @Then("the product added message is displayed")
    public void theProductAddedMessageIsDisplayed() {
        Assert.assertTrue(context.products().getAddedMessage().toLowerCase().contains("added to cart"),
                "The product added message was not displayed");
    }

    @When("the user opens the cart from the confirmation")
    public void theUserOpensTheCartFromTheConfirmation() {
        context.products().clickViewCart();
    }
}
