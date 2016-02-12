package com.selenium.pages.sheknows;

import com.selenium.pages.AbstractPage;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Elements and actions that appear on sheknows.com/hot
 *
 * @author Joshua.Farrell
 * @version 1.0
 */
@Data
public abstract class MainPage extends AbstractPage {
    @FindBy(id = "dfp-slot-leaderboard")
    private WebElement divLeaderboard;

    @FindBy(id = "dfp-slot-right-rail-top-desktop")
    private WebElement divRightTopDesktop;

    @FindBy(id = "dfp-slot-right-rail-bottom")
    private WebElement divRightBottomDesktop;

    @FindBy(id = "dfp-slot-adhesion-bottom")
    private WebElement divMobileBottom;

    @FindBy(id = "dfp-slot-flow-display")
    private List<WebElement> divFlowDisplay;

    public MainPage(WebDriver driver) {
        super(driver);
    }
}
