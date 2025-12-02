package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.CartPage;
import pages.ProductPage;
import utils.DriverManager;

/**
 * CartSteps - Step definitions for cart feature
 */
public class CartSteps {
    private ProductPage productPage;
    private CartPage cartPage;

    @When("I navigate to the cart page")
    public void i_navigate_to_the_cart_page() {
        productPage = new ProductPage(DriverManager.getDriver());
        productPage.clickCartIcon();
        cartPage = new CartPage(DriverManager.getDriver());
    }

    @Then("I should see the cart page")
    public void i_should_see_the_cart_page() {
        cartPage = new CartPage(DriverManager.getDriver());
        Assert.assertTrue(cartPage.isCartPageDisplayed(),
                "Cart page should be displayed");
    }

    @Then("I should see {string} in the cart")
    public void i_should_see_in_the_cart(String itemName) {
        cartPage = new CartPage(DriverManager.getDriver());
        // Convert product ID to display name
        String displayName = convertProductIdToName(itemName);
        Assert.assertTrue(cartPage.isItemInCart(displayName),
                "Item should be in cart: " + displayName);
    }

    @Then("the cart should contain {int} item(s)")
    public void the_cart_should_contain_items(Integer expectedCount) {
        cartPage = new CartPage(DriverManager.getDriver());
        int actualCount = cartPage.getCartItemCount();
        Assert.assertEquals(actualCount, expectedCount.intValue(),
                "Cart item count should match");
    }

    @When("I remove {string} from cart page")
    public void i_remove_from_cart_page(String itemName) {
        cartPage = new CartPage(DriverManager.getDriver());
        cartPage.removeItemFromCart(itemName);
    }

    @Then("I should not see {string} in the cart")
    public void i_should_not_see_in_the_cart(String itemName) {
        cartPage = new CartPage(DriverManager.getDriver());
        String displayName = convertProductIdToName(itemName);
        Assert.assertFalse(cartPage.isItemInCart(displayName),
                "Item should not be in cart: " + displayName);
    }

    // Helper method to convert product ID to display name
    private String convertProductIdToName(String productId) {
        switch (productId) {
            case "sauce-labs-backpack":
                return "Sauce Labs Backpack";
            case "sauce-labs-bike-light":
                return "Sauce Labs Bike Light";
            case "sauce-labs-bolt-t-shirt":
                return "Sauce Labs Bolt T-Shirt";
            case "sauce-labs-fleece-jacket":
                return "Sauce Labs Fleece Jacket";
            case "sauce-labs-onesie":
                return "Sauce Labs Onesie";
            case "test.allthethings()-t-shirt-(red)":
                return "Test.allTheThings() T-Shirt (Red)";
            default:
                return productId;
        }
    }
}
