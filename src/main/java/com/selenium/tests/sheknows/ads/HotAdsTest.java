package com.selenium.tests.sheknows.ads;

import com.selenium.pages.sheknows.HotPage;
import com.selenium.tests.SuperTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HotAdsTest extends SuperTest {
    private HotPage hotPage;

    @BeforeClass()
    public void initTest() {
        hotPage = PageFactory.initElements(driver, HotPage.class);
        hotPage.open("http://www.sheknows.com/hot");

        hotPage.dismissPopUps();
    }

    @Test(groups = {"checkin"})
    public void checkLeaderboard() {
        Assert.assertTrue(hotPage.elementHasContent(hotPage.getDivLeaderboard()), "Leaderboard ad not found on the site.");
    }

    @Test(groups = {"checkin"})
    public void checkRightTopDesktop() {
        Assert.assertTrue(hotPage.elementHasContent(hotPage.getDivRightTopDesktop()), "Right top ad not found on the site.");
    }

    @Test(groups = {"checkin"})
    public void checkRightBottomDesktop() {
        Assert.assertTrue(hotPage.elementHasContent(hotPage.getDivRightBottomDesktop()), "Right bottom ad not found on the site.");
    }
}
