@login
Feature: Login
  As a registered user
  I want to log in with my credentials
  So that I can use my account

  Background:
    Given a registered user exists
    And the user is on the home page

  @smoke @positive
  Scenario: Verify that registered user can log in successfully
    When the user clicks on the Signup and Login link
    And the user logs in with the registered email and password
    Then the user sees the message "Logged in as <username>"

  @negative
  Scenario: Verify login fails with incorrect data
    When the user clicks on the Signup and Login link
    And the user logs in with email "wrong.user@example.com" and password "WrongPass123"
    Then the error "Your email or password is incorrect!" is displayed

  @negative
  Scenario: Login with incorrect password
    When the user clicks on the Signup and Login link
    And the user logs in with the registered email and an incorrect password
    Then the error "Your email or password is incorrect!" is displayed
