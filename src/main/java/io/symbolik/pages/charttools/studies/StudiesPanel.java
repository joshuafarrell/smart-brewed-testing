package io.symbolik.pages.left.studies;

import io.symbolik.pages.AbstractPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openqa.selenium.By;
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

    public void applyStudy(String name) {
       driver.findElement(By.xpath("//div[contains(@class, 'study-name')][1]/div[1]/div[1]/span[text()='"
               + name + "']")).click();


    }

    public void removeStudy(String name) {
        driver.findElements(By.xpath("//div[@title='Remove " + name + "']")).get(0).click();

    }

    public void editAppliedStudy(String name) {
        driver.findElements(By.xpath("//div[@title='Edit " + name + "']")).get(0).click();

    }

    public void duplicateStudy(String name) {
        driver.findElement(By.xpath("")).click();
        driver.findElement(By.xpath("")).click();
    }

    public void deleteStudy(String name) {

    }

    public void createPreset(String name, String preset) {

    }

    public void applyPreset(String name, String preset) {

    }
}
