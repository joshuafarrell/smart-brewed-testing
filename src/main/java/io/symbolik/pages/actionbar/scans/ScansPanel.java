package io.symbolik.pages.right.scans;

import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
public class ScansPanel {
    @FindBy(xpath="//*[@id='radar']")
    private WebElement divNewScan;
}
