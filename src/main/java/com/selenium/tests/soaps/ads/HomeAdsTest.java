package com.selenium.tests.soaps.ads;

import com.selenium.pages.soaps.HomePage;
import com.selenium.tests.SuperTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomeAdsTest extends SuperTest {
    private HomePage homePage;

    @BeforeClass()
    public void initTest() {
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.open("http://soaps.sheknows.com");

    }

    @Test(groups = {"checkin"})
    public void checkTopAd() {
        Assert.assertTrue(homePage.elementHasContent(homePage.getDivTop()), "Top ad not found on the site.");
    }

    @Test(groups = {"checkin"})
    public void checkFirstSideAd() {
        Assert.assertTrue(homePage.elementHasContent(homePage.getDivMiddleAd1()), "1st side ad not found on the site.");
    }

    @Test(groups = {"checkin"})
    public void checkSecondSideAd() {
        Assert.assertTrue(homePage.elementHasContent(homePage.getDivMiddleAd3()), "2nd side ad not found on the site.");
    }

    @Test(groups = {"checkin"})
    public void checkBottomAd() {
        Assert.assertTrue(homePage.elementHasContent(homePage.getDivBottom()), "Bottom ad not found on the site.");
    }
}
