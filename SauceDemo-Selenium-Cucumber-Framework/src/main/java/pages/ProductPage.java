package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * ProductPage - Page Object Model for SauceDemo Products Page
 */
public class ProductPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(className = "inventory_item")
    private List<WebElement> productItems;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify products page is displayed")
    public boolean isProductsPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(pageTitle));
            return pageTitle.getText().equals("Products");
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Get page title")
    public String getPageTitleText() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText();
    }

    @Step("Sort products by: {option}")
    public void sortProducts(String option) {
        wait.until(ExpectedConditions.visibilityOf(sortDropdown));
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(option);
    }

    @Step("Get number of products")
    public int getProductCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productItems));
        return productItems.size();
    }

    @Step("Add product to cart: {productName}")
    public void addProductToCart(String productName) {
        String buttonId = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        WebElement addButton = driver.findElement(By.id(buttonId));
        wait.until(ExpectedConditions.elementToBeClickable(addButton));
        addButton.click();
    }

    @Step("Remove product from cart: {productName}")
    public void removeProductFromCart(String productName) {
        String buttonId = "remove-" + productName.toLowerCase().replace(" ", "-");
        WebElement removeButton = driver.findElement(By.id(buttonId));
        wait.until(ExpectedConditions.elementToBeClickable(removeButton));
        removeButton.click();
    }

    @Step("Get cart item count")
    public int getCartItemCount() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartBadge));
            return Integer.parseInt(cartBadge.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    @Step("Check if cart badge is displayed")
    public boolean isCartBadgeDisplayed() {
        try {
            return cartBadge.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Click on cart icon")
    public void clickCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        cartIcon.click();
    }

    @Step("Click on product: {productName}")
    public void clickProduct(String productName) {
        WebElement product = driver.findElement(
                By.xpath("//div[text()='" + productName + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(product));
        product.click();
    }

    @Step("Open menu")
    public void openMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton));
        menuButton.click();
    }

    @Step("Logout from application")
    public void logout() {
        openMenu();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        logoutLink.click();
    }

    @Step("Verify product is displayed: {productName}")
    public boolean isProductDisplayed(String productName) {
        try {
            WebElement product = driver.findElement(
                    By.xpath("//div[text()='" + productName + "']"));
            return product.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Get all product names")
    public List<String> getAllProductNames() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
        return productNames.stream()
                .map(WebElement::getText)
                .toList();
    }

    @Step("Get all product prices")
    public List<String> getAllProductPrices() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productPrices));
        return productPrices.stream()
                .map(WebElement::getText)
                .toList();
    }

    @Step("Check if product button shows Remove")
    public boolean isProductInCart(String productName) {
        try {
            String buttonId = "remove-" + productName.toLowerCase().replace(" ", "-");
            WebElement removeButton = driver.findElement(By.id(buttonId));
            return removeButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
