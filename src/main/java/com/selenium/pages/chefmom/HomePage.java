package com.selenium.pages.chefmom;

import com.selenium.pages.AbstractPage;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Elements and actions that appear on chefmom.sheknows.com
 *
 * @author Joshua.Farrell
 * @version 1.0
 */
@Data
public class HomePage extends AbstractPage {

    @FindBy(css = "#nav > div.banners.clearfix > div")
    private WebElement divBannerAd;

    @FindBy(css = "#div-adtag-1450373255-1")
    private WebElement divSideAd;

    public HomePage(WebDriver driver) {
        super(driver);
    }

}
