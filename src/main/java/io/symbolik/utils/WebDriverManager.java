package io.symbolik.utils;

import org.openqa.selenium.WebDriver;

/**
 * Manage drivers and keep unique to different threads.
 */
public class WebDriverManager {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }

}
