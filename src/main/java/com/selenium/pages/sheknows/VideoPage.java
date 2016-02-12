package com.selenium.pages.sheknows;

import lombok.Data;
import org.openqa.selenium.WebDriver;

/**
 * Elements and actions that appear on sheknows.com/video
 *
 * @author Joshua.Farrell
 * @version 1.0
 */
@Data
public class VideoPage extends MainPage {
    public VideoPage(WebDriver driver) {
        super(driver);
    }
}
