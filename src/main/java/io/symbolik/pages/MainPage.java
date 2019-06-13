package io.symbolik.pages;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Elements and actions that appear on www.blogher.com
 *
 * @author Joshua.Farrell
 * @version 1.0
 */
@Data
public class MainPage extends AbstractPage {
    @FindBy(xpath = "//div[contains(@class, 'search-icon')]")
    private WebElement divSearchIcon;

    public MainPage(final WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(divSearchIcon));
    }
}