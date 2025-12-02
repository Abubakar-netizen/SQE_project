package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * CartPage - Page Object Model for SauceDemo Cart Page
 */
public class CartPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPrices;

    @FindBy(className = "cart_quantity")
    private List<WebElement> itemQuantities;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify cart page is displayed")
    public boolean isCartPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(pageTitle));
            return pageTitle.getText().equals("Your Cart");
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Get page title")
    public String getPageTitleText() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText();
    }

    @Step("Get number of items in cart")
    public int getCartItemCount() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
            return cartItems.size();
        } catch (Exception e) {
            return 0;
        }
    }

    @Step("Remove item from cart: {itemName}")
    public void removeItemFromCart(String itemName) {
        String buttonId = "remove-" + itemName.toLowerCase().replace(" ", "-");
        WebElement removeButton = driver.findElement(By.id(buttonId));
        wait.until(ExpectedConditions.elementToBeClickable(removeButton));
        removeButton.click();
    }

    @Step("Click continue shopping")
    public void clickContinueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        continueShoppingButton.click();
    }

    @Step("Click checkout")
    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutButton.click();
    }

    @Step("Verify item is in cart: {itemName}")
    public boolean isItemInCart(String itemName) {
        try {
            WebElement item = driver.findElement(
                    By.xpath("//div[@class='inventory_item_name' and text()='" + itemName + "']"));
            return item.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Get all item names in cart")
    public List<String> getAllItemNames() {
        wait.until(ExpectedConditions.visibilityOfAllElements(itemNames));
        return itemNames.stream()
                .map(WebElement::getText)
                .toList();
    }

    @Step("Get all item prices in cart")
    public List<String> getAllItemPrices() {
        wait.until(ExpectedConditions.visibilityOfAllElements(itemPrices));
        return itemPrices.stream()
                .map(WebElement::getText)
                .toList();
    }

    @Step("Get item price: {itemName}")
    public String getItemPrice(String itemName) {
        try {
            WebElement priceElement = driver.findElement(
                    By.xpath("//div[text()='" + itemName
                            + "']/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']"));
            return priceElement.getText();
        } catch (Exception e) {
            return null;
        }
    }

    @Step("Verify cart is empty")
    public boolean isCartEmpty() {
        try {
            return cartItems.isEmpty();
        } catch (Exception e) {
            return true;
        }
    }
}
