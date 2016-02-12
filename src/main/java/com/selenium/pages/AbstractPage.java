package com.selenium.pages;

import com.selenium.pages.sheknows.MenuBar;
import com.selenium.pages.sheknows.advertising.DailySignUp;
import com.selenium.pages.sheknows.advertising.FullAd;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Parent to all page objects, contains useful functions for pages
 *
 * @author Joshua.Farrell
 * @version 1.0
 */
public abstract class AbstractPage extends AVeryAbstractPage {
    // Universal elements on all sheknows pages
    private MenuBar menuBar;
    private DailySignUp dailySignUp;
    private FullAd fullAd;

    public AbstractPage(WebDriver driver) {
        super(driver, 30);

        menuBar = PageFactory.initElements(driver, MenuBar.class);
        dailySignUp = PageFactory.initElements(driver, DailySignUp.class);
        fullAd = PageFactory.initElements(driver, FullAd.class);

        PageFactory.initElements(this.driver, this);
    }

    /**
     * Get the title of the page
     *
     * @return
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * Get the current URL
     *
     * @return
     */
    public String getURL() {
        return driver.getCurrentUrl();
    }

    /**
     * Navigate to a given URL
     *
     * @param url
     */
    public void open(String url) {
        driver.get(url);
    }

    /**
     * Get back the menu bar for some super fun menu action
     *
     * @return
     */
    public MenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * Dismiss all the dang pop ups
     */
    public void dismissPopUps() {
        fullAd.waitAndCloseFullAd();
        dailySignUp.popupDailySignUpDialog();
        dailySignUp.waitAndCloseDailySignUp();
    }

    /**
     * Get back the daily sign up form
     *
     * @return
     */
    public DailySignUp getDailySignUp() {
        return dailySignUp;
    }

    /**
     * Get back the full ad that shows up whenever it feels like
     *
     * @return
     */
    public FullAd getFullAd() {
        return fullAd;
    }

    /**
     * Used to check that a tag has innerHTML, making sure content is rendered
     *
     * @param element
     * @return
     */
    public boolean elementHasContent(WebElement element) {
        return !(element.getAttribute("innerHTML").isEmpty());
    }
}
