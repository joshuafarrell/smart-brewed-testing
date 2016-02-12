package com.selenium.pages.soaps;

import com.selenium.pages.AbstractPage;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Elements and actions that appear on soaps.sheknows.com
 *
 * @author Joshua.Farrell
 * @version 1.0
 */
@Data
public class HomePage extends AbstractPage {

    @FindBy(css = "#oas_Top2")
    private WebElement divTop;
    @FindBy(css = "#oas_Top")
    private WebElement divBottom;
    @FindBy(css = "#oas_Middle3")
    private WebElement divMiddleAd3;
    @FindBy(css = "#oas_Middle1")
    private WebElement divMiddleAd1;

    public HomePage(WebDriver driver) {
        super(driver);
    }

}
