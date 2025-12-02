package com.saucedemo.automation.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigurationReader - Reads and provides access to application configuration
 * Centralized configuration management from config.properties file
 */
public class ConfigurationReader {
    private static Properties configProperties;
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    static {
        try {
            FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH);
            configProperties = new Properties();
            configProperties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file: " + CONFIG_FILE_PATH);
        }
    }

    /**
     * Get configuration property value by key
     * 
     * @param key Property key
     * @return Property value
     */
    public static String getConfigProperty(String key) {
        return configProperties.getProperty(key);
    }

    /**
     * Get application URL from configuration
     * 
     * @return Application URL
     */
    public static String getApplicationUrl() {
        return configProperties.getProperty("app.url");
    }

    /**
     * Get browser name from configuration
     * 
     * @return Browser name (chrome, firefox, edge)
     */
    public static String getBrowserName() {
        return configProperties.getProperty("browser");
    }

    /**
     * Get implicit wait timeout in seconds
     * 
     * @return Implicit wait timeout
     */
    public static int getImplicitWaitTimeout() {
        return Integer.parseInt(configProperties.getProperty("implicit.wait"));
    }

    /**
     * Get explicit wait timeout in seconds
     * 
     * @return Explicit wait timeout
     */
    public static int getExplicitWaitTimeout() {
        return Integer.parseInt(configProperties.getProperty("explicit.wait"));
    }

    /**
     * Get page load timeout in seconds
     * 
     * @return Page load timeout
     */
    public static int getPageLoadTimeout() {
        return Integer.parseInt(configProperties.getProperty("page.load.timeout"));
    }

    /**
     * Get Excel test data file path
     * 
     * @return Excel file path
     */
    public static String getExcelDataFilePath() {
        return configProperties.getProperty("excel.path");
    }

    /**
     * Get database connection URL
     * 
     * @return Database URL
     */
    public static String getDatabaseUrl() {
        return configProperties.getProperty("db.url");
    }

    /**
     * Get database username
     * 
     * @return Database username
     */
    public static String getDatabaseUsername() {
        return configProperties.getProperty("db.username");
    }

    /**
     * Get database password
     * 
     * @return Database password
     */
    public static String getDatabasePassword() {
        return configProperties.getProperty("db.password");
    }

    /**
     * Get Redis server host
     * 
     * @return Redis host
     */
    public static String getRedisServerHost() {
        return configProperties.getProperty("redis.host");
    }

    /**
     * Get Redis server port
     * 
     * @return Redis port
     */
    public static int getRedisServerPort() {
        return Integer.parseInt(configProperties.getProperty("redis.port"));
    }

    /**
     * Get screenshot save directory path
     * 
     * @return Screenshot directory path
     */
    public static String getScreenshotDirectoryPath() {
        return configProperties.getProperty("screenshot.path");
    }

    /**
     * Check if screenshot on failure is enabled
     * 
     * @return true if enabled, false otherwise
     */
    public static boolean isScreenshotOnFailureEnabled() {
        return Boolean.parseBoolean(configProperties.getProperty("take.screenshot.on.failure"));
    }
}
