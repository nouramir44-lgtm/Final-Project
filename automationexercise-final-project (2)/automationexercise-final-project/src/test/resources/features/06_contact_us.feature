@contact
Feature: Contact Us
  As a visitor
  I want to send a message to the site owners
  So that I can get support

  @smoke @positive
  Scenario: Verify Contact Us form
    Given the user is on the home page
    When the user opens the Contact Us page
    And the user enters valid contact form data
    And the user submits the contact form
    Then the contact confirmation "Success! Your details have been submitted successfully." is displayed

  @negative
  Scenario: Invalid Contact Us submission
    Given the user is on the home page
    When the user opens the Contact Us page
    And the user enters contact form data with an invalid email address
    And the user submits the contact form
    Then the contact form is not submitted
