Feature: Shopping Cart Management
  As a user of SauceDemo
  I want to manage my shopping cart
  So that I can review and modify items before checkout

  Background:
    Given I am logged in as "standard_user" with password "secret_sauce"

  @smoke @cart @positive
  Scenario: TC09 - Add multiple products to cart
    Given I am on the products page
    When I add "sauce-labs-backpack" to the cart
    And I add "sauce-labs-bike-light" to the cart
    And I add "sauce-labs-bolt-t-shirt" to the cart
    Then the cart badge should show 3 items

  @regression @cart @positive
  Scenario: TC10 - View cart with added items
    Given I have added "sauce-labs-backpack" to the cart
    When I navigate to the cart page
    Then I should see the cart page
    And I should see "sauce-labs-backpack" in the cart
    And the cart should contain 1 item

  @regression @cart @positive
  Scenario: TC11 - Remove item from cart page
    Given I have added "sauce-labs-backpack" to the cart
    And I have added "sauce-labs-bike-light" to the cart
    When I navigate to the cart page
    And I remove "sauce-labs-backpack" from cart page
    Then the cart should contain 1 item
    And I should not see "sauce-labs-backpack" in the cart
