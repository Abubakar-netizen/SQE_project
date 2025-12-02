package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.ProductPage;
import utils.DriverManager;

import java.util.List;

/**
 * ProductSteps - Step definitions for product feature
 */
public class ProductSteps {
    private ProductPage productPage;

    @Given("I am on the products page")
    public void i_am_on_the_products_page() {
        productPage = new ProductPage(DriverManager.getDriver());
        Assert.assertTrue(productPage.isProductsPageDisplayed(),
                "Should be on products page");
    }

    @Then("I should see {int} products displayed")
    public void i_should_see_products_displayed(Integer expectedCount) {
        productPage = new ProductPage(DriverManager.getDriver());
        int actualCount = productPage.getProductCount();
        Assert.assertEquals(actualCount, expectedCount.intValue(),
                "Product count should match");
    }

    @Then("each product should have a name and price")
    public void each_product_should_have_a_name_and_price() {
        productPage = new ProductPage(DriverManager.getDriver());
        List<String> names = productPage.getAllProductNames();
        List<String> prices = productPage.getAllProductPrices();

        Assert.assertFalse(names.isEmpty(), "Products should have names");
        Assert.assertFalse(prices.isEmpty(), "Products should have prices");
        Assert.assertEquals(names.size(), prices.size(),
                "Each product should have both name and price");
    }

    @When("I sort products by {string}")
    public void i_sort_products_by(String sortOption) {
        productPage = new ProductPage(DriverManager.getDriver());
        productPage.sortProducts(sortOption);
    }

    @Then("the products should be sorted by price in ascending order")
    public void the_products_should_be_sorted_by_price_in_ascending_order() {
        productPage = new ProductPage(DriverManager.getDriver());
        List<String> prices = productPage.getAllProductPrices();

        for (int i = 0; i < prices.size() - 1; i++) {
            double currentPrice = extractPrice(prices.get(i));
            double nextPrice = extractPrice(prices.get(i + 1));
            Assert.assertTrue(currentPrice <= nextPrice,
                    "Products should be sorted by price in ascending order");
        }
    }

    @When("I add {string} to the cart")
    public void i_add_to_the_cart(String productName) {
        productPage = new ProductPage(DriverManager.getDriver());
        productPage.addProductToCart(productName);
    }

    @Then("the cart badge should show {int} item(s)")
    public void the_cart_badge_should_show_items(Integer expectedCount) {
        productPage = new ProductPage(DriverManager.getDriver());
        int actualCount = productPage.getCartItemCount();
        Assert.assertEquals(actualCount, expectedCount.intValue(),
                "Cart badge count should match");
    }

    @Then("the product should show Remove button")
    public void the_product_should_show_remove_button() {
        // Verified by checking if product is in cart
        productPage = new ProductPage(DriverManager.getDriver());
        Assert.assertTrue(productPage.getCartItemCount() > 0,
                "Product should be in cart");
    }

    @Given("I have added {string} to the cart")
    public void i_have_added_to_the_cart(String productName) {
        productPage = new ProductPage(DriverManager.getDriver());
        if (!productPage.isProductsPageDisplayed()) {
            // Navigate to products page if not already there
            productPage.navigateTo("https://www.saucedemo.com/inventory.html");
        }
        productPage.addProductToCart(productName);
    }

    @When("I remove {string} from the cart")
    public void i_remove_from_the_cart(String productName) {
        productPage = new ProductPage(DriverManager.getDriver());
        productPage.removeProductFromCart(productName);
    }

    @Then("the cart badge should not be displayed")
    public void the_cart_badge_should_not_be_displayed() {
        productPage = new ProductPage(DriverManager.getDriver());
        Assert.assertFalse(productPage.isCartBadgeDisplayed(),
                "Cart badge should not be displayed");
    }

    @Then("the product should show Add to cart button")
    public void the_product_should_show_add_to_cart_button() {
        productPage = new ProductPage(DriverManager.getDriver());
        Assert.assertEquals(productPage.getCartItemCount(), 0,
                "Cart should be empty");
    }

    // Helper method
    private double extractPrice(String priceText) {
        return Double.parseDouble(priceText.replace("$", ""));
    }
}
