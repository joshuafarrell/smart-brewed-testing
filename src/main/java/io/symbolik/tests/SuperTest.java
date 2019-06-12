package io.symbolik.tests;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import io.symbolik.utils.CustomDriver;
import io.symbolik.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;

public class SuperTest implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {
    public static final SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication();
    private static final Logger LOGGER = Logger.getLogger(SuperTest.class);
    protected WebDriver driver;
    protected String name;

    private ThreadLocal<String> sessionId = new ThreadLocal<String>();

    @BeforeSuite
    public void beforeSuite() {
        LOGGER.info("Start BeforeSuite");

        LOGGER.info("End BeforeSuite");
    }

    @BeforeTest
    public void beforeTest() {
        LOGGER.info("   Start BeforeTest");

        LOGGER.info("   End BeforeTest");
    }

    @Parameters({"browser", "version", "os", "local"})
    @BeforeClass
    public void beforeClass(@Optional("firefox") String browser, @Optional("1.0") String version,
                            @Optional("Windows") String os, @Optional("true") Boolean local,
                            ITestContext context) {
        LOGGER.info("       Start BeforeClass");
        name = context.getName();

        if (local) {
            LOGGER.info("Creating a local driver.");

            driver = CustomDriver.createLocalDriver(browser);
        } else {
            LOGGER.info("Creating a SauceLabs driver");

            driver = CustomDriver.createSLDriver(browser, version, os, name, authentication);
        }

        WebDriverManager.setWebDriver(driver);

        sessionId.set(((RemoteWebDriver) driver).getSessionId().toString());

        LOGGER.info(name + " - Thread: " + Thread.currentThread().getId());

        LOGGER.info(name + " - Driver: " + driver.hashCode());

        driver.manage().window().maximize();

        LOGGER.info("       End BeforeClass");
    }

    @BeforeMethod()
    public void beforeMethod() {
        LOGGER.info("           BeforeMethod");
    }

    @AfterMethod()
    public void afterMethod(ITestResult result) {
        LOGGER.info("           AfterMethod");
    }

    @AfterClass
    public void afterClass() {
        LOGGER.info("       Start AfterClass");

        if (driver != null) {
            LOGGER.info("Attempting to quit driver.");
            driver.quit();
        }

        LOGGER.info("       End AfterClass");
    }

    @AfterTest
    public void afterTest() {
        LOGGER.info("    Start AfterTest");

        LOGGER.info("    End AfterTest");
    }

    @AfterSuite
    public void afterSuite() {
        LOGGER.info("AfterSuite");
    }

    @Override
    public SauceOnDemandAuthentication getAuthentication() {
        return authentication;
    }

    @Override
    public String getSessionId() {
        return sessionId.get();
    }

}