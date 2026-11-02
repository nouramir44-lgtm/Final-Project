@cart @checkout
Feature: Cart and checkout
  As a logged-in customer
  I want to add products to my cart and pay for them
  So that I can place an order

  @smoke @positive
  Scenario: Verify that user can add products to cart and complete checkout
    Given a registered user exists
    And the user is on the home page
    And the user is logged in
    # Step 1: add a product to the cart
    When the user opens the Products page
    And the user hovers over a product and clicks Add to Cart
    Then the product added message is displayed
    When the user opens the cart from the confirmation
    Then the cart contains the added product
    # Step 2: checkout
    When the user proceeds to checkout
    And the user validates the delivery address and order review
    And the user enters an order comment and clicks Place Order
    And the user enters valid payment details and confirms the order
    Then the order confirmation "Your order has been placed successfully!" is displayed

  @negative
  Scenario: Checkout with empty cart
    Given a registered user exists
    And the user is on the home page
    And the user is logged in
    When the user opens the cart
    Then the cart is empty
    And the Proceed To Checkout option is not available

  @negative
  Scenario: Attempt checkout without login
    Given the user is on the home page
    When the user opens the Products page
    And the user hovers over a product and clicks Add to Cart
    And the user opens the cart from the confirmation
    And the user proceeds to checkout
    Then the login required prompt is displayed
    When the user chooses Register and Login from the prompt
    Then the user is redirected to the login and signup page

  @negative
  Scenario: Invalid payment information does not place the order
    Given a registered user exists
    And the user is on the home page
    And the user is logged in
    When the user opens the Products page
    And the user hovers over a product and clicks Add to Cart
    And the user opens the cart from the confirmation
    And the user proceeds to checkout
    And the user enters an order comment and clicks Place Order
    And the user submits the payment form with empty payment details
    Then the order is not placed
