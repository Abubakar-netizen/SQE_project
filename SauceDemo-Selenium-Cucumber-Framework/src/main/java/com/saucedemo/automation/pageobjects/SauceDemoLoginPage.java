package com.saucedemo.automation.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.saucedemo.automation.utilities.ConfigurationReader;

/**
 * SauceDemoLoginPage - Page Object for SauceDemo Login functionality
 * Handles all login-related operations and element interactions
 */
public class SauceDemoLoginPage extends AbstractBasePage {

    // Web Elements - Login Page
    @FindBy(id = "user-name")
    private WebElement usernameInputField;

    @FindBy(id = "password")
    private WebElement passwordInputField;

    @FindBy(id = "login-button")
    private WebElement loginSubmitButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessageContainer;

    @FindBy(className = "login_logo")
    private WebElement sauceDemoLogoImage;

    @FindBy(css = ".error-button")
    private WebElement errorDismissButton;

    /**
     * Constructor
     * 
     * @param driver WebDriver instance
     */
    public SauceDemoLoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to SauceDemo login page
     */
    @Step("Navigate to SauceDemo login page")
    public void navigateToLoginPage() {
        driver.get(ConfigurationReader.getApplicationUrl());
        explicitWait.until(ExpectedConditions.visibilityOf(sauceDemoLogoImage));
    }

    /**
     * Enter username in the username field
     * 
     * @param username Username to enter
     */
    @Step("Enter username: {username}")
    public void enterUsernameInField(String username) {
        explicitWait.until(ExpectedConditions.visibilityOf(usernameInputField));
        usernameInputField.clear();
        usernameInputField.sendKeys(username);
    }

    /**
     * Enter password in the password field
     * 
     * @param password Password to enter
     */
    @Step("Enter password in field")
    public void enterPasswordInField(String password) {
        explicitWait.until(ExpectedConditions.visibilityOf(passwordInputField));
        passwordInputField.clear();
        passwordInputField.sendKeys(password);
    }

    /**
     * Click the login submit button
     */
    @Step("Click login submit button")
    public void clickLoginSubmitButton() {
        explicitWait.until(ExpectedConditions.elementToBeClickable(loginSubmitButton));
        loginSubmitButton.click();
    }

    /**
     * Get error message text displayed on login failure
     * 
     * @return Error message text
     */
    @Step("Get error message text from login page")
    public String getErrorMessageText() {
        explicitWait.until(ExpectedConditions.visibilityOf(errorMessageContainer));
        return errorMessageContainer.getText();
    }

    /**
     * Check if error message is displayed
     * 
     * @return true if error message is visible, false otherwise
     */
    @Step("Verify error message is displayed")
    public boolean isErrorMessageDisplayed() {
        try {
            explicitWait.until(ExpectedConditions.visibilityOf(errorMessageContainer));
            return errorMessageContainer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Perform complete login action with username and password
     * 
     * @param username Username
     * @param password Password
     */
    @Step("Perform login with username: {username}")
    public void performLoginWithCredentials(String username, String password) {
        enterUsernameInField(username);
        enterPasswordInField(password);
        clickLoginSubmitButton();
    }

    /**
     * Verify if login page is currently displayed
     * 
     * @return true if login page elements are visible
     */
    @Step("Verify login page is displayed")
    public boolean isLoginPageDisplayed() {
        try {
            return sauceDemoLogoImage.isDisplayed() && usernameInputField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clear all input fields on login page
     */
    @Step("Clear all login input fields")
    public void clearAllLoginFields() {
        usernameInputField.clear();
        passwordInputField.clear();
    }

    /**
     * Get SauceDemo logo text
     * 
     * @return Logo text
     */
    @Step("Get SauceDemo logo text")
    public String getSauceDemoLogoText() {
        explicitWait.until(ExpectedConditions.visibilityOf(sauceDemoLogoImage));
        return sauceDemoLogoImage.getText();
    }
}
