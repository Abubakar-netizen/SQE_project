Feature: Login Functionality
  As a user of SauceDemo
  I want to be able to login to the application
  So that I can access the products and features

  Background:
    Given I am on the SauceDemo login page

  @smoke @login @positive
  Scenario: TC01 - Successful login with valid credentials
    When I enter username "standard_user" and password "secret_sauce"
    And I click on the login button
    Then I should be redirected to the products page
    And I should see "Products" as the page title

  @regression @login @negative
  Scenario: TC02 - Login fails with invalid username
    When I enter username "invalid_user" and password "secret_sauce"
    And I click on the login button
    Then I should see an error message
    And the error message should contain "Username and password do not match"

  @regression @login @negative
  Scenario: TC03 - Login fails with empty credentials
    When I leave the username field empty
    And I leave the password field empty
    And I click on the login button
    Then I should see an error message
    And the error message should contain "Username is required"

  @smoke @login @positive
  Scenario: TC04 - Successful logout
    Given I am logged in as "standard_user" with password "secret_sauce"
    When I logout from the application
    Then I should be redirected to the login page
    And the login page should be displayed
