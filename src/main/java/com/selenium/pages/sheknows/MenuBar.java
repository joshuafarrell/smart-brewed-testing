package com.selenium.pages.sheknows;

import com.selenium.pages.AVeryAbstractPage;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Top navigation menu that appears on Sheknows.com
 *
 * @author Joshua.Farrell
 * @version 1.0
 */
@Data
public class MenuBar extends AVeryAbstractPage {
    @FindBy(xpath = "//html/body/div[5]/div[1]/h5/a")
    private WebElement lnkSheKnows;

    @FindBy(xpath = "//div[@data-appular-component='header']//a[@class='home-link']")
    private WebElement lnkHome;

    @FindBy(xpath = "//a[@href='/new']")
    private WebElement lnkNew;

    @FindBy(xpath = "//a[@href='/hot']")
    private WebElement lnkHot;

    @FindBy(xpath = "//a[@href='/video']")
    private WebElement lnkVideo;

    @FindBy(xpath = "//a[@href='/my-feed']")
    private WebElement lnkMyFeed;

    public MenuBar(WebDriver driver) {
        super(driver, 60);
    }
}
