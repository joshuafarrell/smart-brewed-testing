package io.symbolik.tests.login;

import io.symbolik.pages.LoginPage;
import io.symbolik.pages.MainPage;
import io.symbolik.tests.SuperTest;
import io.symbolik.utils.Tools;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends SuperTest {
    private LoginPage loginPage;
    private MainPage mainPage;

    @BeforeClass()
    public void initTest() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.open("https://charting.qa3.demark.io");
    }

    @Test(groups = {"checkin"})
    public void login() throws InterruptedException {
        mainPage = loginPage.login("qatester", "DeMark123");

        Assert.assertTrue(mainPage.getDivSearchIcon().isDisplayed(), "Did not find the search icon");

        for (int i = 0; i < 1000; i++) {
            driver.navigate().refresh();

            Thread.sleep(10000);
        }

        Tools.captureBrowserScreenshot(driver, "test", "./test-output/selenium_screenshots/");
    }
}
