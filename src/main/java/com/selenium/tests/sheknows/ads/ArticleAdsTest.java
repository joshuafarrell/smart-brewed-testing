package com.selenium.tests.sheknows.ads;

import com.selenium.pages.sheknows.content.ArticlePage;
import com.selenium.tests.SuperTest;
import com.selenium.utils.Tools;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ArticleAdsTest extends SuperTest {
    private ArticlePage page;

    @Parameters("url")
    @BeforeClass()
    public void initTest(@Optional("http://www.sheknows.com/parenting/articles/1096707/professor-picks-up-single-moms-toddler") String url) {
        page = PageFactory.initElements(driver, ArticlePage.class);
        page.open(url);

        page.dismissPopUps();

    }

    @Test(groups = {"checkin"})
    public void checkLeaderboard() {
        Assert.assertTrue(page.elementHasContent(page.getDivLeaderboard()), "Leaderboard ad not found on the site.");
    }

    @Test(groups = {"checkin"})
    public void checkRightBottomDesktop() {
        Assert.assertTrue(page.elementHasContent(page.getDivRightBottomDesktopBottomDesktop()), "Right bottom ad not found on the site.");
    }

    @Test(groups = {"checkin"})
    public void checkOutBrain() {
        Assert.assertTrue(page.elementHasContent(page.getDivOutBrain()), "Out Brain ad not found at "
                + page.getDivOutBrain().getLocation());
    }

    @Test(groups = {"checkin"})
    public void checkSkExplorer() {
        Assert.assertTrue(page.elementHasContent(page.getDivSkExplorer()), "SK Explorer ad not found on the site."
                + page.getDivSkExplorer().getLocation());
    }

    @Test(groups = {"checkin"})
    public void checkVidibleRender() {
        Tools.scrollToBottom(driver);
        Assert.assertTrue(page.elementHasContent(page.getDivVidibleRender()), "Vidible ad not found on the site.");
    }
}
