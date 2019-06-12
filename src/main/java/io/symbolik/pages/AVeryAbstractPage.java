package io.symbolik.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

public abstract class AVeryAbstractPage {
    private static final Logger LOGGER = Logger.getLogger(AVeryAbstractPage.class);

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public AVeryAbstractPage(final WebDriver driver, final int timeout) {
        this.wait = new WebDriverWait(driver, timeout);
        this.js = (JavascriptExecutor) driver;
        this.driver = driver;
    }
}
