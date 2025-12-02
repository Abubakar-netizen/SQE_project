package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.CheckoutPage;
import utils.DriverManager;

/**
 * CheckoutSteps - Step definitions for checkout feature
 */
public class CheckoutSteps {
    private CheckoutPage checkoutPage;

    @When("I proceed to checkout")
    public void i_proceed_to_checkout() {
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        // Click checkout button from cart page
        DriverManager.getDriver().findElement(
                org.openqa.selenium.By.id("checkout")).click();
    }

    @When("I fill checkout information with firstName {string}, lastName {string}, and postalCode {string}")
    public void i_fill_checkout_information(String firstName, String lastName, String postalCode) {
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        checkoutPage.fillCheckoutInformation(firstName, lastName, postalCode);
    }

    @When("I click continue on checkout")
    public void i_click_continue_on_checkout() {
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        checkoutPage.clickContinue();
    }

    @When("I click finish on checkout")
    public void i_click_finish_on_checkout() {
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        checkoutPage.clickFinish();
    }

    @Then("I should see the order confirmation")
    public void i_should_see_the_order_confirmation() {
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        Assert.assertTrue(checkoutPage.isOrderComplete(),
                "Order confirmation should be displayed");
    }

    @Then("the confirmation message should contain {string}")
    public void the_confirmation_message_should_contain(String expectedText) {
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        String confirmationHeader = checkoutPage.getCompleteHeader();
        Assert.assertTrue(confirmationHeader.contains(expectedText),
                "Confirmation message should contain: " + expectedText);
    }

    @When("I click continue on checkout without filling information")
    public void i_click_continue_on_checkout_without_filling_information() {
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        checkoutPage.clickContinue();
    }

    @Then("I should see a checkout error message")
    public void i_should_see_a_checkout_error_message() {
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        Assert.assertTrue(checkoutPage.isErrorMessageDisplayed(),
                "Checkout error message should be displayed");
    }

    @Then("the error should contain {string}")
    public void the_error_should_contain(String expectedText) {
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        String errorMessage = checkoutPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains(expectedText),
                "Error should contain: " + expectedText);
    }

    @Then("I should see the checkout overview page")
    public void i_should_see_the_checkout_overview_page() {
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        Assert.assertTrue(checkoutPage.isCheckoutStepTwoPage(),
                "Checkout overview page should be displayed");
    }

    @Then("the subtotal should be displayed")
    public void the_subtotal_should_be_displayed() {
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        String subtotal = checkoutPage.getSubtotal();
        Assert.assertNotNull(subtotal, "Subtotal should be displayed");
        Assert.assertTrue(subtotal.contains("$"), "Subtotal should contain price");
    }

    @Then("the tax should be displayed")
    public void the_tax_should_be_displayed() {
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        String tax = checkoutPage.getTax();
        Assert.assertNotNull(tax, "Tax should be displayed");
        Assert.assertTrue(tax.contains("$"), "Tax should contain price");
    }

    @Then("the total should be displayed")
    public void the_total_should_be_displayed() {
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        String total = checkoutPage.getTotal();
        Assert.assertNotNull(total, "Total should be displayed");
        Assert.assertTrue(total.contains("$"), "Total should contain price");
    }
}
