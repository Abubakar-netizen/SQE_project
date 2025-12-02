Feature: Product Management
  As a user of SauceDemo
  I want to browse and manage products
  So that I can add items to my cart

  Background:
    Given I am logged in as "standard_user" with password "secret_sauce"
    And I am on the products page

  @smoke @product @positive
  Scenario: TC05 - View all products on the products page
    Then I should see 6 products displayed
    And each product should have a name and price

  @regression @product @positive
  Scenario: TC06 - Sort products by price low to high
    When I sort products by "Price (low to high)"
    Then the products should be sorted by price in ascending order

  @smoke @product @positive
  Scenario: TC07 - Add a single product to cart
    When I add "sauce-labs-backpack" to the cart
    Then the cart badge should show 1 item
    And the product should show Remove button

  @regression @product @positive
  Scenario: TC08 - Remove product from cart on products page
    Given I have added "sauce-labs-backpack" to the cart
    When I remove "sauce-labs-backpack" from the cart
    Then the cart badge should not be displayed
    And the product should show Add to cart button
