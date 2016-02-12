package com.selenium.pages.drinksmixer;

import com.selenium.pages.AbstractPage;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Elements and actions that appear on www.drinksmixer.com
 *
 * @author Joshua.Farrell
 * @version 1.0
 */
@Data
public class HomePage extends AbstractPage {

    // @Value("${pages.home.advertisement.top:#adtag-728x90_ad_container}")
    @FindBy(css = "#adtag-728x90_ad_container")
    private WebElement divTopAd;
    // @Value("${pages.home.advertisement.side:#adtag-300x250_ad_container}")
    @FindBy(css = "#adtag-300x250_ad_container")
    private WebElement divSideAd;

    public HomePage(WebDriver driver) {
        super(driver);
    }

}
