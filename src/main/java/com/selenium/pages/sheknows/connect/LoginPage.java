package com.selenium.pages.sheknows.connect;

import com.selenium.pages.AbstractPage;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Elements and actions that appear on 199.192.240.120/login
 *
 * @author Joshua.Farrell
 * @version 1.0
 */
@Data
public class LoginPage extends AbstractPage {

    @FindBy(id = "username")
    private WebElement txtbxUsername;
    @FindBy(id = "password")
    private WebElement txtbxPassword;
    @FindBy(id = "submit")
    private WebElement btnLogin;
    @FindBy(css = "body > div > div > div > form > div.form-errors")
    private WebElement divInvalidUserOrPass;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public <T> T loginToConnect(String username, String password, Class<T> expectedPage) {
        txtbxUsername.clear();
        txtbxPassword.clear();

        txtbxUsername.sendKeys(username);
        txtbxPassword.sendKeys(password);

        btnLogin.click();

        return PageFactory.initElements(driver, expectedPage);
    }

    public String returnErrorMessage() {
        return divInvalidUserOrPass.getText();
    }
}
