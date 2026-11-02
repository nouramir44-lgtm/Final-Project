@testcases
Feature: Test Cases page
  As a visitor
  I want to open the Test Cases page
  So that I can read the documented test cases

  @smoke @positive
  Scenario: Verify Test Cases page
    Given the user is on the home page
    When the user clicks Test Cases
    Then the Test Cases page loads successfully
