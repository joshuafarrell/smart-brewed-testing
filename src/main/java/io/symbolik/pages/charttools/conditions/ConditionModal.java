package io.symbolik.pages.left.conditions;

import io.symbolik.pages.AbstractPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Data
@EqualsAndHashCode(callSuper=false)
public class ConditionModal extends AbstractPage {
    @FindBy(xpath = "//div[contains(@class, 'name-editor')]/div/input")
    private WebElement inputName;

    @FindBy(xpath = "//div[contains(@class, 'sidebar-modal-content')]/div/div[contains(@class, 'primary')]/div/div")
    private WebElement btnSave;

    public ConditionModal(final WebDriver driver) { super(driver);}

    public void setMonacoInput(final String code) {
        js.executeScript("this.monaco.editor.getModels()[0].setValue('" + code + "');");
    }
}
