package io.symbolik.tests.login;

import io.symbolik.pages.HomePage;
import io.symbolik.tests.SuperTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.symbolik.utils.Tools;

public class LoginTest extends SuperTest {
    private HomePage homePage;

    @BeforeClass()
    public void initTest() {
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.open("https://charting.qa3.demark.io");
    }

    @Test(groups = {"checkin"})
    public void checkSignInButton() throws InterruptedException{
        Thread.sleep(60000);
        Tools.captureBrowserScreenshot(driver, "test", "./test-output/selenium_screenshots/");
        Assert.assertTrue(homePage.elementHasContent(homePage.getBtnSubmit()), "Sign in button not found.");
    }
}
