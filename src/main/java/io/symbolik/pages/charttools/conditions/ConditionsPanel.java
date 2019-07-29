package io.symbolik.pages.left.conditions;

import io.symbolik.pages.AbstractPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
@EqualsAndHashCode(callSuper=false)
public class ConditionsPanel extends AbstractPage {
    @FindBy(xpath = "//div[contains(@class, 'condition-pane-header')]/div/div/div[contains(@class, 'ui-button')]")
    private WebElement btnNewCondition;

    @FindBy(xpath = "//input[contains(@class, 'search-input')]")
    private WebElement inputSearch;

    public ConditionsPanel(final WebDriver driver) {
        super(driver);
    }

    public void searchForCondition(String query) {

    }

    public void createCondition(String name, String code) {

    }

    public void applyCondition(String name) {
        driver.findElement(By.xpath("//div[contains(@class, 'study-name')][1]/div[1]/div[1]/span[text()='"
                + name + "']")).click();
    }

    public void editAppliedCondition(String name) {

    }

    public void editCondition(String name) {

    }

    public void deleteCondition(String name){
        action.moveToElement(driver.findElement(By.xpath("//div[contains(@class, 'study-name')][1]/div[1]/div[1]/" +
                "span[text()='" + name + "']"))).perform();

        WebElement editMenu = driver.findElement(By.xpath("//div[contains(@class, 'study-name')]/div/div/span[text()='"+
                name+"']/../../../../div[contains(@class, 'edit')]/div/div"));

        editMenu.click();
        editMenu.findElement(By.xpath("../../../div/ul/li/div/div/span[text() = 'Delete']/..")).click();
    }

    public void removeChartedCondition(String name) {
        driver.findElement(By.xpath("//div[contains(@title, 'Remove " + name + "')]/div")).click();
    }

    public void favoriteCondition(String name) {

    }
}
