package com.selenium.tests.sheknows.ads;

import com.selenium.pages.sheknows.content.SlideshowListPage;
import com.selenium.tests.SuperTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SlideshowListAdsTest extends SuperTest {
    private SlideshowListPage page;

    @Parameters("url")
    @BeforeClass()
    public void initTest(@Optional("http://www.sheknows.com/living/slideshow/list/381/breathtaking-short-backpacking-trips-to-embrace-your-inner-cheryl-strayed") String url) {
        page = PageFactory.initElements(driver, SlideshowListPage.class);
        page.open(url);

        page.dismissPopUps();
    }

    @Test(groups = {"checkin"})
    public void checkLeaderboard() {
        Assert.assertTrue(page.elementHasContent(page.getDivLeaderboard()), "Leaderboard ad not found on the site.");
    }

}
