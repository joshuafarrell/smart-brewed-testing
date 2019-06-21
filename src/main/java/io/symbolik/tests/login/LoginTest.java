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

    @BeforeClass()
    public void initTest() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.open("https://symbolik.com");
    }


    @Test(priority = 1,groups = {"checkin"})
    public void loginWrongPassword(){
        loginPage = loginPage.loginWrongInfo("qatester", "wrong");

        Assert.assertTrue(loginPage.getInputPassword().isDisplayed(), "Did not find the password input");
        Tools.captureBrowserScreenshot(driver, "WrongPassword", "./test-output/selenium_screenshots/");
    }

    @Test(priority = 1, groups = {"checkin"})
    public void loginWrongUsername(){
        loginPage = loginPage.loginWrongInfo("DoesNotExist", "DeMark123");

        Assert.assertTrue(loginPage.getInputPassword().isDisplayed(), "Did not find the password input");
        Tools.captureBrowserScreenshot(driver, "WrongUsername", "./test-output/selenium_screenshots/");
    }

    @Test(priority = 2, groups = {"checkin"})
    public void login() {
        MainPage mainPage = loginPage.login("qatester", "DeMark123");

        Assert.assertTrue(mainPage.getDivSearchIcon().isDisplayed(), "Did not find the search icon");

        Tools.captureBrowserScreenshot(driver, "test", "./test-output/selenium_screenshots/");
    }
}
