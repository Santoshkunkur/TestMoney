package com.example.manage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumDriverSingleton {
    private static WebDriver driver;

    // Private constructor to prevent instantiation of the class
    private SeleniumDriverSingleton() {
        // Set up the WebDriver instance (e.g., initialize ChromeDriver)
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("start-maximized");
        options.addArguments("--enable-notifications");
        driver = new ChromeDriver(options);

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            synchronized (SeleniumDriverSingleton.class) {
                if (driver == null) {
                    new SeleniumDriverSingleton();
                }
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}