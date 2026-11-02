@products
Feature: Products
  As a visitor
  I want to browse and search products
  So that I can find what I want to buy

  @smoke @positive
  Scenario: Verify all products and product details
    Given the user is on the home page
    When the user opens the Products page
    Then the "ALL PRODUCTS" heading is displayed
    And the products list is displayed
    When the user opens the details of the first product
    Then the product details match the selected product

  @negative
  Scenario: Invalid search text returns no products
    Given the user is on the home page
    When the user opens the Products page
    And the user searches for "zzzxxyy-no-such-product"
    Then the "SEARCHED PRODUCTS" heading is displayed
    And no products are listed
