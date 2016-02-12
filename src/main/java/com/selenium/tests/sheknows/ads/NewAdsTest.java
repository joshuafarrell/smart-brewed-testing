package com.selenium.tests.sheknows.ads;

import com.selenium.pages.sheknows.NewPage;
import com.selenium.tests.SuperTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewAdsTest extends SuperTest {
    private NewPage newPage;

    @BeforeClass()
    public void initTest() {
        newPage = PageFactory.initElements(driver, NewPage.class);
        newPage.open("http://www.sheknows.com/new");

        newPage.dismissPopUps();
    }

    @Test(groups = {"checkin"})
    public void checkLeaderboard() {
        Assert.assertTrue(newPage.elementHasContent(newPage.getDivLeaderboard()), "Leaderboard ad not found on the site.");
    }

    @Test(groups = {"checkin"})
    public void checkRightTopDesktop() {
        Assert.assertTrue(newPage.elementHasContent(newPage.getDivRightTopDesktop()), "Right top ad not found on the site.");
    }

    @Test(groups = {"checkin"})
    public void checkRightBottomDesktop() {
        Assert.assertTrue(newPage.elementHasContent(newPage.getDivRightBottomDesktop()), "Right bottom ad not found on the site.");
    }
}
