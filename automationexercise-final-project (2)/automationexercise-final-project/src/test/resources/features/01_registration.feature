@registration
Feature: User registration
  As a new visitor
  I want to create an account
  So that I can log in and place orders

  @smoke @positive
  Scenario: Verify that user can register successfully
    Given the user is on the home page
    When the user clicks on the Signup and Login link
    And the user enters a new name and email address in the Signup section
    And the user clicks the Signup button
    And the user fills all required fields on the account creation page
    And the user clicks Create Account
    Then the "ACCOUNT CREATED!" message appears

  @negative
  Scenario: Signup with an existing email is rejected
    Given a registered user exists
    And the user is on the home page
    When the user clicks on the Signup and Login link
    And the user enters a new name and the existing email address in the Signup section
    And the user clicks the Signup button
    Then the error "Email Address already exist!" is displayed
