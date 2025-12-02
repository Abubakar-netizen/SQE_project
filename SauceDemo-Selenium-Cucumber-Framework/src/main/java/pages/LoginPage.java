package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigReader;

/**
 * LoginPage - Page Object Model for SauceDemo Login Page
 */
public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    @FindBy(className = "login_logo")
    private WebElement loginLogo;

    @FindBy(css = ".error-button")
    private WebElement errorButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Navigate to login page")
    public void navigateToLoginPage() {
        driver.get(ConfigReader.getAppUrl());
        wait.until(ExpectedConditions.visibilityOf(loginLogo));
    }

    @Step("Enter username: {username}")
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @Step("Click login button")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    @Step("Get error message text")
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

    @Step("Login with username: {username}")
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Verify login page is displayed")
    public boolean isLoginPageDisplayed() {
        try {
            return loginLogo.isDisplayed() && usernameField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Clear login fields")
    public void clearFields() {
        usernameField.clear();
        passwordField.clear();
    }

    @Step("Get login logo text")
    public String getLoginLogoText() {
        wait.until(ExpectedConditions.visibilityOf(loginLogo));
        return loginLogo.getText();
    }
}
