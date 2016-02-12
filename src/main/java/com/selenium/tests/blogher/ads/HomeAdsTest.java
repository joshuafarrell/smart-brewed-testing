package com.selenium.tests.blogher.ads;

import com.selenium.pages.blogher.HomePage;
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
        homePage.open("http://www.blogher.com");

    }

    @Test(groups = {"checkin"})
    public void checkTopAd() {
        Assert.assertTrue(homePage.elementHasContent(homePage.getDivTopAd()), "Top ad not found on the site.");
    }

    @Test(groups = {"checkin"})
    public void checkFirstMiddleAd() {
        Assert.assertTrue(homePage.elementHasContent(homePage.getDivMiddleAd2()), "1st middle ad not found on the site.");
    }

    @Test(groups = {"checkin"})
    public void checkSecondMiddleAd() {
        Assert.assertTrue(homePage.elementHasContent(homePage.getDivMiddleAd3()), "2nd middle ad not found on the site.");
    }
}
