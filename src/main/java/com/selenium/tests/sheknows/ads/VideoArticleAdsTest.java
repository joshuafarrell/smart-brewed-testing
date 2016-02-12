package com.selenium.tests.sheknows.ads;

import com.selenium.pages.sheknows.content.VideoArticlePage;
import com.selenium.tests.SuperTest;
import com.selenium.utils.Tools;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VideoArticleAdsTest extends SuperTest {
    private VideoArticlePage page;

    @Parameters("url")
    @BeforeClass()
    public void initTest(@Optional("http://www.sheknows.com/parenting/articles/1087898/how-to-transition-from-bottle-to-sippy-cup") String url) {
        page = PageFactory.initElements(driver, VideoArticlePage.class);
        page.open(url);

        page.dismissPopUps();
    }

    @Test(groups = {"checkin"})
    public void checkLeaderboard() {
        Assert.assertTrue(page.elementHasContent(page.getDivLeaderboard()), "Leaderboard ad not found on the site.");
    }

    @Test(groups = {"checkin"})
    public void checkRightBottomDesktop() {
        Assert.assertTrue(page.elementHasContent(page.getDivRightBottomDesktop()), "Right bottom ad not found on the site.");
    }

    @Test(groups = {"checkin"})
    public void checkOutBrain() {
        Assert.assertTrue(page.elementHasContent(page.getDivOutBrain()), "Out Brain ad not found on the site.");
    }

    @Test(groups = {"checkin"})
    public void checkSkExplorer() {
        Assert.assertTrue(page.elementHasContent(page.getDivSkExplorer()), "SK Explorer ad not found on the site.");
    }

    @Test(groups = {"checkin"})
    public void checkVidibleRender() {
        Tools.scrollToBottom(driver);
        Assert.assertTrue(page.elementHasContent(page.getDivVidibleRender()), "Vidible ad not found on the site.");
    }
}
