package com.selenium.pages.sheknows.connect;

import com.selenium.pages.AbstractPage;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Elements and actions that appear on connect.sheknows.com
 *
 * @author Joshua.Farrell
 * @version 1.0
 */
@Data
public class HomePage extends AbstractPage {

    @FindBy(css = "#main > a")
    private WebElement lnkLoginwithconnect;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickLoginWithConnect() {
        lnkLoginwithconnect.click();

        return PageFactory.initElements(driver, LoginPage.class);
    }
}
