package io.symbolik.pages;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Elements and actions that appear on www.blogher.com
 *
 * @author Joshua.Farrell
 * @version 1.0
 */
@Data
public class LoginPage extends AbstractPage {
    private MainPage mainPage;
    private LoginPage loginPage;

    @FindBy(xpath = "//*[@id=\"root\"]/main/div/div[6]/form/div/button")
    private WebElement btnSubmit;

    @FindBy(id = "username")
    private WebElement inputUsername;

    @FindBy(id = "password")
    private WebElement inputPassword;

    public LoginPage(final WebDriver driver) {
        super(driver);
    }

    public MainPage login(String username, String password) {
        inputUsername.clear();
        inputPassword.clear();
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);

        btnSubmit.click();

        return mainPage = PageFactory.initElements(driver, MainPage.class);
    }

    public LoginPage loginWrongInfo(String username, String wrongPassword){
        inputUsername.clear();
        inputPassword.clear();
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(wrongPassword);

        btnSubmit.click();

        return loginPage = PageFactory.initElements(driver, LoginPage.class);
    }
}
