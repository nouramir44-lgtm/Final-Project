@logout
Feature: Logout
  As a logged-in user
  I want to log out
  So that my session ends

  @smoke @positive
  Scenario: Verify logout
    Given a registered user exists
    And the user is on the home page
    And the user is logged in
    When the user clicks Logout
    Then the user is redirected to the login and signup page
