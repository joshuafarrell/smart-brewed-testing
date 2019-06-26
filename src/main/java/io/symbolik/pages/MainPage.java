package io.symbolik.pages;

import io.symbolik.pages.left.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Data
@EqualsAndHashCode(callSuper=false)
public class MainPage extends AbstractPage {
    private StudiesPanel studyPanel;
    private DrawToolsPanel drawtoolsPanel;
    private ConditionsPanel conditionsPanel;
    private FavoritesPanel favoritesPanel;

    @FindBy(xpath = "//div[contains(@class, 'search-icon')]")
    private WebElement divSearchIcon;

    /**
     * Left side toolbar
     */
    @FindBy(xpath = "//div[contains(@class, 'study-icon')]/div")
    private WebElement divStudyIcon;

    @FindBy(xpath = "//div[contains(@class, 'draw-icon')]/div")
    private WebElement divDrawToolIcon;

    @FindBy(xpath = "//div[contains(@class, 'method-icon')]/div")
    private WebElement divConditionIcon;

    @FindBy(xpath = "//div[contains(@class, 'favorite-icon')]/div")
    private WebElement divFavoriteIcon;

    /**
     * Right side toolbar
     */
    @FindBy(xpath="//*[@id='briefcase'")
    private WebElement divPortfolioIcon;

    @FindBy(xpath="//*[@id='radar']")
    private WebElement divScanIcon;

    @FindBy(xpath="//*[@id='bell'")
    private WebElement divAlertIcon;

    public MainPage(final WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(divSearchIcon));
        wait.until(ExpectedConditions.visibilityOf(divStudyIcon));
    }

    public StudiesPanel openStudyList(){
        divStudyIcon.click();

        return studyPanel = PageFactory.initElements(driver, StudiesPanel.class);
    }

    public DrawToolsPanel openDrawToolList(){
        divDrawToolIcon.click();

        return drawtoolsPanel = PageFactory.initElements(driver, DrawToolsPanel.class);
    }

    public ConditionsPanel openConditionList(){
        divConditionIcon.click();

        return conditionsPanel = PageFactory.initElements(driver, ConditionsPanel.class);
    }

    public FavoritesPanel openFavoriteList(){
        divFavoriteIcon.click();

        return favoritesPanel = PageFactory.initElements(driver, FavoritesPanel.class);
    }
}