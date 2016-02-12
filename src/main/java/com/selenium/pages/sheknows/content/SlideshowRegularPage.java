package com.selenium.pages.sheknows.content;

import com.selenium.pages.AbstractPage;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
public class SlideshowRegularPage extends AbstractPage {

    @FindBy(id = "dfp-slot-leaderboard")
    private WebElement divLeaderboard;
    @FindBy(id = "outbrain_widget_0")
    private WebElement divOutBrain;
    @FindBy(id = "dfp-slot-right-rail-slideshow-top-desktop")
    private WebElement divRightRailTop;
    @FindBy(id = "dfp-slot-adhesion-bottom")
    private WebElement divMobileBottom;

    public SlideshowRegularPage(WebDriver driver) {
        super(driver);
    }
}
