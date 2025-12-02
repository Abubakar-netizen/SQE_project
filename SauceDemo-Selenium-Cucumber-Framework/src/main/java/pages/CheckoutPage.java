package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * CheckoutPage - Page Object Model for SauceDemo Checkout Pages
 */
public class CheckoutPage extends BasePage {

    // Checkout Step One Elements
    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    // Checkout Step Two Elements
    @FindBy(className = "summary_subtotal_label")
    private WebElement subtotalLabel;

    @FindBy(className = "summary_tax_label")
    private WebElement taxLabel;

    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;

    @FindBy(id = "finish")
    private WebElement finishButton;

    // Checkout Complete Elements
    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    @FindBy(className = "complete-text")
    private WebElement completeText;

    @FindBy(id = "back-to-products")
    private WebElement backToProductsButton;

    @FindBy(className = "title")
    private WebElement pageTitle;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // Checkout Step One Methods

    @Step("Enter first name: {firstName}")
    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    @Step("Enter last name: {lastName}")
    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameField));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    @Step("Enter postal code: {postalCode}")
    public void enterPostalCode(String postalCode) {
        wait.until(ExpectedConditions.visibilityOf(postalCodeField));
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }

    @Step("Fill checkout information")
    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }

    @Step("Click continue button")
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
    }

    @Step("Click cancel button")
    public void clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
        cancelButton.click();
    }

    @Step("Get error message")
    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    @Step("Check if error message is displayed")
    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Checkout Step Two Methods

    @Step("Get subtotal amount")
    public String getSubtotal() {
        wait.until(ExpectedConditions.visibilityOf(subtotalLabel));
        return subtotalLabel.getText();
    }

    @Step("Get tax amount")
    public String getTax() {
        wait.until(ExpectedConditions.visibilityOf(taxLabel));
        return taxLabel.getText();
    }

    @Step("Get total amount")
    public String getTotal() {
        wait.until(ExpectedConditions.visibilityOf(totalLabel));
        return totalLabel.getText();
    }

    @Step("Click finish button")
    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        finishButton.click();
    }

    // Checkout Complete Methods

    @Step("Get completion header")
    public String getCompleteHeader() {
        wait.until(ExpectedConditions.visibilityOf(completeHeader));
        return completeHeader.getText();
    }

    @Step("Get completion text")
    public String getCompleteText() {
        wait.until(ExpectedConditions.visibilityOf(completeText));
        return completeText.getText();
    }

    @Step("Verify order is complete")
    public boolean isOrderComplete() {
        try {
            if (driver.getCurrentUrl().contains("checkout-complete.html")) {
                return true;
            }
            wait.until(ExpectedConditions.visibilityOf(completeHeader));
            return completeHeader.getText().toLowerCase().contains("thank you");
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Click back to products")
    public void clickBackToProducts() {
        wait.until(ExpectedConditions.elementToBeClickable(backToProductsButton));
        backToProductsButton.click();
    }

    @Step("Get page title")
    public String getPageTitleText() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText();
    }

    @Step("Verify checkout step one page")
    public boolean isCheckoutStepOnePage() {
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(pageTitle, "Checkout: Your Information"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Verify checkout step two page")
    public boolean isCheckoutStepTwoPage() {
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(pageTitle, "Checkout: Overview"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
