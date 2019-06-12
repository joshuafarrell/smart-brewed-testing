package io.symbolik.utils;

import com.saucelabs.common.SauceOnDemandAuthentication;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
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

    private CustomDriver(){}

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

        capability.setCapability(CapabilityType.PLATFORM, os);
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

        capability.setCapability(CapabilityType.PLATFORM, os);
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
     * Create a local driver on a local instance of symbolik grid
     *
     * @param browser
     * @return WebDriver
     */
    public static WebDriver createLocalDriver(String browser) {
        WebDriver driver = null;
        DesiredCapabilities capability = new DesiredCapabilities();

        if ("firefox".equals(browser)) {
            FirefoxProfile firefoxProfile = new FirefoxProfile();

            firefoxProfile.setPreference("browser.download.folderList", 2);
            firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);

            //disable automatic adobe-like viewers
            firefoxProfile.setPreference("pdfjs.disabled", true);
            firefoxProfile.setPreference("plugin.scan.plid.all", false);
            firefoxProfile.setPreference("plugin.scan.Acrobat", "99.0");

            firefoxProfile.setPreference("browser.download.dir", "src/resources/");
            firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
            firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf,application/Zip");
            //this will stop a long page load, but still be bound by the implicit wait below
            firefoxProfile.setPreference("network.http.connection-timeout", 60);

            capability = DesiredCapabilities.firefox();
            capability.setCapability(FirefoxDriver.PROFILE, firefoxProfile);

        } else if ("chrome".equals(browser)) {
            capability = DesiredCapabilities.chrome();
            capability.setCapability("chrome.binary", "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");
        } else if ("iexplorer".equals(browser)) {
            capability = DesiredCapabilities.internetExplorer();

        }

        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            LOGGER.info(e);
        }

        return driver;
    }
}
