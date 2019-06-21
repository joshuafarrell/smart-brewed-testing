package io.symbolik.utils;

import com.saucelabs.common.SauceOnDemandAuthentication;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.log4testng.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Collection of static functions for creating assorted WebDrivers
 *
 * @author Joshua.Farrell
 * @version 1.0
 */
public class CustomDriver {
    private static final Logger LOGGER = Logger.getLogger(CustomDriver.class);
    private static final String hubURI = "http://localhost:4444/wd/hub";

    private CustomDriver() {
    }

    /**
     * Create a remote driver using Sauce Labs
     *
     * @param browser
     * @param version
     * @param os
     * @param className
     * @return WebDriver
     */
    public static WebDriver createSLDriver(String browser, String version, String os, String className,
                                           SauceOnDemandAuthentication auth) {
        WebDriver driver = null;

        DesiredCapabilities capability = new DesiredCapabilities();

        capability.setCapability(CapabilityType.BROWSER_NAME, browser);
        capability.setCapability(CapabilityType.VERSION, version);
        capability.setCapability("name", className);

        try {
            driver = new RemoteWebDriver(new URL("http://" + auth.getUsername() + ":" + auth.getAccessKey() +
                    "@ondemand.saucelabs.com:80/wd/hub"), capability);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        } catch (MalformedURLException e) {
            LOGGER.info(e);
        }

        return driver;
    }

    /**
     * Create a remote driver using Browserstack driver
     *
     * @param browser
     * @param version
     * @param os
     * @param className
     * @return WebDriver
     */
    public static WebDriver createBSDriver(String browser, String version, String os, String className) {
        WebDriver driver = null;

        DesiredCapabilities capability = new DesiredCapabilities();

        capability.setCapability(CapabilityType.BROWSER_NAME, browser);
        capability.setCapability(CapabilityType.VERSION, version);
        capability.setCapability("name", className);

        try {
            driver = new RemoteWebDriver(new URL("http://skdev1:sFJFZ5nR4yaXXxTyiR3d@hub.browserstack.com/wd/hub"),
                    capability);

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        } catch (MalformedURLException e) {
            LOGGER.info(e);
        }

        return driver;
    }

    /**
     * Create a local driver locally or on a docker instance of symbolik grid
     *
     * @param browser
     * @return WebDriver
     */
    public static WebDriver createLocalDriver(String browser) {
        WebDriver driver = null;

        try {
            if ("edge".equals(browser)) {
                EdgeOptions edgeOptions = new EdgeOptions();

                driver = new RemoteWebDriver(new URL(hubURI), edgeOptions);
            } else if ("chrome".equals(browser)) {
                ChromeOptions chromeOptions = new ChromeOptions();

                chromeOptions.addArguments("--ignore-certificate-errors");
                chromeOptions.addArguments("--allow-running-insecure-content");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--start-maximized");

                driver = new RemoteWebDriver(new URL(hubURI), chromeOptions);
            } else {
                // Default to firefox if nothing matches
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                firefoxOptions.addPreference("browser.download.folderList", 2);
                firefoxOptions.addPreference("browser.download.manager.showWhenStarting", false);

                //disable automatic adobe-like viewers
                firefoxOptions.addPreference("pdfjs.disabled", true);
                firefoxOptions.addPreference("plugin.scan.plid.all", false);
                firefoxOptions.addPreference("plugin.scan.Acrobat", "99.0");

                firefoxOptions.addPreference("browser.download.dir", "src/resources/");
                firefoxOptions.addPreference("browser.helperApps.alwaysAsk.force", false);
                firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf,application/Zip");
                //this will stop a long page load, but still be bound by the implicit wait below
                firefoxOptions.addPreference("network.http.connection-timeout", 60);

                driver = new RemoteWebDriver(new URL(hubURI), firefoxOptions);
            }

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            LOGGER.info(e);
        }

        return driver;
    }
}
