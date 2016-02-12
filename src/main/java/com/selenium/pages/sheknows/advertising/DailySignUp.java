package com.selenium.pages.sheknows.advertising;

import com.selenium.pages.AVeryAbstractPage;
import lombok.Data;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

/**
 * SignUp email dialog that appears when moving mouse off website
 *
 * @author Joshua.Farrell
 * @version 1.0
 */
@Data
public class DailySignUp extends AVeryAbstractPage {
    private static final Logger LOGGER = Logger.getLogger(DailySignUp.class);

    @FindBy(css = "(#sk-pushdown-timer-id > div.circle > span.btn-close")
    private WebElement lnkCloseCampaign1;
    @FindBy(xpath = "(//a[contains(@id,'bcx_close_')])[2]")
    private WebElement lnkCloseCampaign2;

    public DailySignUp(WebDriver driver) {
        super(driver, 60);
    }

    /**
     * Once the Daily SignUp dialog appears click the close button
     */
    public void waitAndCloseDailySignUp() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.elementToBeClickable(lnkCloseCampaign1)).click();
        } catch (TimeoutException | StaleElementReferenceException e) {
            LOGGER.info(e);
        }
    }

    /**
     * Move the mouse off the browser so that the SignUp dialog pops up
     */
    public DailySignUp popupDailySignUpDialog() {
        Actions action = new Actions(driver);

        LOGGER.info("Moving the mouse in hopes of triggering pop-up.");

        action.moveByOffset(0, 1200).perform();

        return PageFactory.initElements(driver, DailySignUp.class);
    }
}
