package com.selenium.pages.sheknows;

import com.selenium.pages.AbstractPage;
import lombok.Data;
import org.openqa.selenium.WebDriver;

/**
 * Elements and actions that appear on sheknows.com/hot
 *
 * @author Joshua.Farrell
 * @version 1.0
 */
@Data
public class HotPage extends MainPage {
    public HotPage(WebDriver driver) {
        super(driver);
    }
}
