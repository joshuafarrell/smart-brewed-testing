package com.selenium.tests.stylecaster.ads;

import com.selenium.pages.stylecaster.HomePage;
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
        homePage.open("http://www.stylecaster.com");

    }

    @Test(groups = {"checkin"})
    public void checkTopAd() {
        Assert.assertTrue(homePage.elementHasContent(homePage.getDivTop()), "Top ad not found on the site.");
    }

}
