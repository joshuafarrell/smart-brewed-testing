package com.selenium.pages.sheknows.content;

import com.selenium.pages.AbstractPage;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
public class SlideshowListPage extends AbstractPage {

    @FindBy(id = "dfp-slot-leaderboard")
    private WebElement divLeaderboard;

    public SlideshowListPage(WebDriver driver) {
        super(driver);
    }

}
