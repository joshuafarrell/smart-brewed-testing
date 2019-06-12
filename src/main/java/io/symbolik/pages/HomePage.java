package io.symbolik.pages;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Elements and actions that appear on www.blogher.com
 *
 * @author Joshua.Farrell
 * @version 1.0
 */
@Data
public class HomePage extends AbstractPage {
    @FindBy(xpath = "//*[@id=\"root\"]/main/div/div[6]/form/div/button")
    private WebElement btnSubmit;

    @FindBy(css = "#oas_Middle3")
    private WebElement divMiddleAd3;

    @FindBy(css = "#oas_Middle2")
    private WebElement divMiddleAd2;

    public HomePage(final WebDriver driver) {
        super(driver);
    }

}
