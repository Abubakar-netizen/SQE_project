Feature: UI Validation and Data Integration
  As a test automation framework
  I want to validate UI elements and integrate with data sources
  So that I can ensure application quality and data-driven testing

  @smoke @validation @positive
  Scenario: TC15 - Verify page titles and URLs
    Given I am on the SauceDemo login page
    Then the page URL should contain "saucedemo.com"
    When I login as "standard_user" with password "secret_sauce"
    Then the page URL should contain "inventory.html"
    And the page title should be "Swag Labs"

  @datadriven @validation @positive
  Scenario: TC16 - Verify product data from Excel
    Given I am logged in as "standard_user" with password "secret_sauce"
    When I retrieve product data from Excel
    Then the product "Sauce Labs Backpack" should exist on the page
    And the product price should match Excel data

  @database @validation @positive
  Scenario: TC17 - Validate login credentials from database
    Given I retrieve user credentials from database for user type "standard"
    When I login with database credentials
    Then I should successfully login to the application
    And I should see the products page

  @redis @validation @positive
  Scenario: TC18 - Verify session data in Redis
    Given I am logged in as "standard_user" with password "secret_sauce"
    When I store session data in Redis with key "test_session"
    Then the session data should be retrievable from Redis
    And the session should be active
