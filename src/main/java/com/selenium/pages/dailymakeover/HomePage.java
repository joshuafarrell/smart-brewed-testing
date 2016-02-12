package com.selenium.pages.dailymakeover;

import com.selenium.pages.AbstractPage;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Elements and actions that appear on www.dailymakeover.com
 *
 * @author Joshua.Farrell
 * @version 1.0
 */
@Data
public class HomePage extends AbstractPage {

    @FindBy(css = "#sc_adunit-2")
    private WebElement divTopAd;
    @FindBy(css = "#sc_adunit-3")
    private WebElement divSideAd;

    public HomePage(WebDriver driver) {
        super(driver);
    }

}
