package com.selenium.tests.chefmom.ads;

import com.selenium.pages.chefmom.HomePage;
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
        homePage.open("http://chefmom.sheknows.com");

    }

    @Test(groups = {"checkin"})
    public void checkBannerAd() {
        Assert.assertTrue(homePage.elementHasContent(homePage.getDivBannerAd()), "Banner ad not found on the site.");
    }

    @Test(groups = {"checkin"})
    public void checkSideAd() {
        Assert.assertTrue(homePage.elementHasContent(homePage.getDivSideAd()), "Side ad not found on the site.");
    }
}
