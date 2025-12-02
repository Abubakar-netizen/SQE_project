package com.saucedemo.automation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.saucedemo.automation.utilities.ConfigurationReader;

import java.time.Duration;

/**
 * AbstractBasePage - Base class for all page objects
 * Provides common functionality and WebDriver instance management
 * Implements Page Object Model design pattern
 */
public abstract class AbstractBasePage {
    protected WebDriver driver;
    protected WebDriverWait explicitWait;

    /**
     * Constructor initializes WebDriver and PageFactory
     * 
     * @param driver WebDriver instance
     */
    public AbstractBasePage(WebDriver driver) {
        this.driver = driver;
        this.explicitWait = new WebDriverWait(
                driver,
                Duration.ofSeconds(ConfigurationReader.getExplicitWaitTimeout()));
        PageFactory.initElements(driver, this);
    }

    /**
     * Get current page URL
     * 
     * @return Current URL as String
     */
    public String getCurrentPageUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get current page title
     * 
     * @return Page title as String
     */
    public String getCurrentPageTitle() {
        return driver.getTitle();
    }

    /**
     * Navigate to specific URL
     * 
     * @param url Target URL
     */
    public void navigateToUrl(String url) {
        driver.get(url);
    }

    /**
     * Refresh current page
     */
    public void refreshCurrentPage() {
        driver.navigate().refresh();
    }

    /**
     * Navigate back to previous page
     */
    public void navigateToPreviousPage() {
        driver.navigate().back();
    }

    /**
     * Navigate forward to next page
     */
    public void navigateToNextPage() {
        driver.navigate().forward();
    }
}
