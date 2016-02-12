package com.selenium.tests.sheknows.ads;

import com.selenium.pages.sheknows.VideoPage;
import com.selenium.tests.SuperTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VideoAdsTest extends SuperTest {
    private VideoPage videoPage;

    @BeforeClass()
    public void initTest() {
        videoPage = PageFactory.initElements(driver, VideoPage.class);
        videoPage.open("http://www.sheknows.com/video");

        videoPage.dismissPopUps();
    }

    @Test(groups = {"checkin"})
    public void checkLeaderboard() {
        Assert.assertTrue(videoPage.elementHasContent(videoPage.getDivLeaderboard()), "Leaderboard ad not found on the site.");
    }

    @Test(groups = {"checkin"})
    public void checkRightTopDesktop() {
        Assert.assertTrue(videoPage.elementHasContent(videoPage.getDivRightTopDesktop()), "Right top ad not found on the site.");
    }

    @Test(groups = {"checkin"})
    public void checkRightBottomDesktop() {
        Assert.assertTrue(videoPage.elementHasContent(videoPage.getDivRightBottomDesktop()), "Right bottom ad not found on the site.");
    }
}
