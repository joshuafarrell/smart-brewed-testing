package com.selenium.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Useful universal tools for tests and page objects.
 *
 * @author Joshua.Farrell
 * @version 1.0
 */
public class Tools {
    private static final Logger LOGGER = Logger.getLogger(Tools.class);

    /**
     * Verifies that an element is displayed and saves a screenshot to the provided filepath
     *
     * @param target
     * @param filePath
     * @return a true or false depending on if the element is displayed
     */
    public static boolean verifyAndCaptureScreenshot(WebDriver driver, WebElement target, String filePath) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(target));

        captureElementScreenshot(driver, target, filePath);

        return target.isDisplayed();
    }

    /**
     * Scrolls to the bottom of a page
     *
     * @param driver
     */
    public static void scrollToBottom(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("scroll(0,document.body.offsetHeight)");
    }

    /**
     * Capture screenshot of specific element
     *
     * @param driver
     * @param target
     * @param filePath
     */
    public static void captureElementScreenshot(WebDriver driver, WebElement target, String filePath) {
        final boolean mkdirs = new File(filePath).mkdirs();

        if (mkdirs) {
            File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            BufferedImage img;
            try {
                img = ImageIO.read(screen);
                File f;
                if ("".equals(target.getAttribute("id"))) {
                    f = new File(filePath + "default.png");
                } else {
                    f = new File(filePath + target.getAttribute("id").replaceAll("/", "_") + ".png");
                }

                Point p = target.getLocation();
                int width = target.getSize().getWidth();
                int height = target.getSize().getHeight();
                BufferedImage dest = img.getSubimage(p.getX(), p.getY(), width, height);
                ImageIO.write(dest, "png", f);

            } catch (IOException e) {
                LOGGER.info(e);
            }
        }
    }
}

