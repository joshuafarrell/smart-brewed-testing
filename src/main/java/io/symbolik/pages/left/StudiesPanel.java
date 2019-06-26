package io.symbolik.pages.left;

import io.symbolik.pages.AbstractPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
@EqualsAndHashCode(callSuper=false)
public class StudiesPanel extends AbstractPage {

    @FindBy(xpath = "//div[contains(@class, 'study-name')][1]/div[1]/div[1]/span[text()='Sequential']")
    private WebElement sequentialStudy;

    public StudiesPanel(final WebDriver driver) {
        super(driver);
    }

}
