package io.symbolik.pages.left.drawtools;

import io.symbolik.pages.AbstractPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openqa.selenium.WebDriver;

@Data
@EqualsAndHashCode(callSuper = false)
public class DrawToolsPanel extends AbstractPage {

    public DrawToolsPanel(final WebDriver driver) {
        super(driver);
    }

    public void searchForDrawTool(String query) {

    }

    public void selectDrawTool(String name) {

    }

    public void selectSpecificPreset(String name, String preset) {

    }

    public void createNewPreset(String name, String preset) {

    }

    public void removeDrawTool(String name) {

    }
}
