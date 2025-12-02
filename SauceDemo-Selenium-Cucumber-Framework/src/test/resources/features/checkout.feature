Feature: Checkout Process
  As a user of SauceDemo
  I want to complete the checkout process
  So that I can purchase my selected items

  Background:
    Given I am logged in as "standard_user" with password "secret_sauce"
    And I have added "sauce-labs-backpack" to the cart

  @smoke @checkout @positive
  Scenario: TC12 - Complete checkout with valid information
    When I navigate to the cart page
    And I proceed to checkout
    And I fill checkout information with firstName "John", lastName "Doe", and postalCode "12345"
    And I click continue on checkout
    And I click finish on checkout
    Then I should see the order confirmation
    And the confirmation message should contain "Thank you for your order"

  @regression @checkout @negative
  Scenario: TC13 - Checkout fails with empty information
    When I navigate to the cart page
    And I proceed to checkout
    And I click continue on checkout without filling information
    Then I should see a checkout error message
    And the error should contain "First Name is required"

  @regression @checkout @positive
  Scenario: TC14 - Verify checkout overview displays correct information
    When I navigate to the cart page
    And I proceed to checkout
    And I fill checkout information with firstName "Jane", lastName "Smith", and postalCode "67890"
    And I click continue on checkout
    Then I should see the checkout overview page
    And the subtotal should be displayed
    And the tax should be displayed
    And the total should be displayed
