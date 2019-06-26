package io.symbolik.tests.studies;

import io.symbolik.pages.MainPage;
import io.symbolik.pages.left.StudiesPanel;
import io.symbolik.tests.SuperTest;
import io.symbolik.utils.Tools;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StudyTest extends SuperTest {
    private MainPage mainPage;

    @BeforeClass()
    public void initTest() {
        mainPage = PageFactory.initElements(driver, MainPage.class);

    }

    @Test(priority = 1,groups = {"checkin"})
    public void openStudiesPanel(){
        StudiesPanel studiesPanel = mainPage.openStudyList();

        Assert.assertTrue(studiesPanel.getSequentialStudy().isDisplayed(), "Did not find the password input");
        Tools.captureBrowserScreenshot(driver, "StudiesPanel", "./test-output/selenium_screenshots/");
    }
}
