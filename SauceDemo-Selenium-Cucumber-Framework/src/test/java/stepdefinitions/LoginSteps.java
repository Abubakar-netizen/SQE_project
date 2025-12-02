package stepdefinitions;

import io.cucumber.java.en.*;
import io.qameta.allure.Allure;
import org.testng.Assert;
import pages.LoginPage;
import pages.ProductPage;
import utils.DriverManager;

/**
 * LoginSteps - Step definitions for login feature
 */
public class LoginSteps {
    private LoginPage loginPage;
    private ProductPage productPage;

    @Given("I am on the SauceDemo login page")
    public void i_am_on_the_saucedemo_login_page() {
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigateToLoginPage();
        Allure.step("Navigated to SauceDemo login page");
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("I should be redirected to the products page")
    public void i_should_be_redirected_to_the_products_page() {
        productPage = new ProductPage(DriverManager.getDriver());
        Assert.assertTrue(productPage.isProductsPageDisplayed(),
                "Should be redirected to products page");
    }

    @Then("I should see {string} as the page title")
    public void i_should_see_as_the_page_title(String expectedTitle) {
        productPage = new ProductPage(DriverManager.getDriver());
        String actualTitle = productPage.getPageTitleText();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title should match");
    }

    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed");
    }

    @Then("the error message should contain {string}")
    public void the_error_message_should_contain(String expectedText) {
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertTrue(actualMessage.contains(expectedText),
                "Error message should contain: " + expectedText);
    }

    @When("I leave the username field empty")
    public void i_leave_the_username_field_empty() {
        loginPage.enterUsername("");
    }

    @When("I leave the password field empty")
    public void i_leave_the_password_field_empty() {
        loginPage.enterPassword("");
    }

    @Given("I am logged in as {string} with password {string}")
    public void i_am_logged_in_as_with_password(String username, String password) {
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigateToLoginPage();
        loginPage.login(username, password);
        productPage = new ProductPage(DriverManager.getDriver());
        Assert.assertTrue(productPage.isProductsPageDisplayed(),
                "Should be logged in successfully");
    }

    @When("I logout from the application")
    public void i_logout_from_the_application() {
        productPage = new ProductPage(DriverManager.getDriver());
        productPage.logout();
    }

    @Then("I should be redirected to the login page")
    public void i_should_be_redirected_to_the_login_page() {
        loginPage = new LoginPage(DriverManager.getDriver());
        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "Should be redirected to login page");
    }

    @Then("the login page should be displayed")
    public void the_login_page_should_be_displayed() {
        loginPage = new LoginPage(DriverManager.getDriver());
        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "Login page should be displayed");
    }

    @When("I login as {string} with password {string}")
    public void i_login_as_with_password(String username, String password) {
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login(username, password);
    }
}
