package com.selenium.pages.sheknows.content;

import com.selenium.pages.AbstractPage;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
public class ArticlePage extends AbstractPage {

    @FindBy(id = "dfp-slot-leaderboard")
    private WebElement divLeaderboard;
    @FindBy(id = "dfp-slot-right-rail-bottom")
    private WebElement divRightBottomDesktopBottomDesktop;
    @FindBy(id = "outbrain_widget_0")
    private WebElement divOutBrain;
    @FindBy(className = "ui-sk-explorer")
    private WebElement divSkExplorer;
    @FindBy(id = "vidible-render")
    private WebElement divVidibleRender;
    @FindBy(id = "dfp-slot-adhesion-bottom")
    private WebElement divMobileBottom;
    @FindBy(id = "dfp-slot-right-rail-top-mobile")
    private WebElement divMobileRightTop;

    public ArticlePage(WebDriver driver) {
        super(driver);
    }


}
