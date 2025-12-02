package com.saucedemo.automation.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

/**
 * WebDriverFactory - Factory class for creating and managing WebDriver
 * instances
 * Implements ThreadLocal pattern for thread-safe parallel execution
 * Handles automatic driver management using WebDriverManager
 */
public class WebDriverFactory {
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    /**
     * Initialize WebDriver based on browser from configuration
     */
    public static void initializeWebDriver() {
        String browserName = ConfigurationReader.getBrowserName();
        initializeWebDriverForBrowser(browserName);
    }

    /**
     * Initialize WebDriver for specific browser
     * 
     * @param browserName Browser name (chrome, firefox, edge)
     */
    public static void initializeWebDriverForBrowser(String browserName) {
        WebDriver driver;

        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        // Configure timeouts
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(ConfigurationReader.getImplicitWaitTimeout()));
        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(ConfigurationReader.getPageLoadTimeout()));
        driver.manage().window().maximize();

        webDriverThreadLocal.set(driver);
    }

    /**
     * Get current WebDriver instance for this thread
     * 
     * @return WebDriver instance
     */
    public static WebDriver getWebDriver() {
        if (webDriverThreadLocal.get() == null) {
            initializeWebDriver();
        }
        return webDriverThreadLocal.get();
    }

    /**
     * Quit WebDriver and remove from ThreadLocal
     */
    public static void quitWebDriver() {
        if (webDriverThreadLocal.get() != null) {
            webDriverThreadLocal.get().quit();
            webDriverThreadLocal.remove();
        }
    }
}
