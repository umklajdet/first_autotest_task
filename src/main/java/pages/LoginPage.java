package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id = "prependedInput")
    private WebElement loginInput;

    @FindBy(id = "prependedInput2")
    private WebElement passwordInput;

    @FindBy(id = "_submit")
    private WebElement submitButton;

    public StartPage logIn(String login, String password) {
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        submitButton.click();
        return pageManager.getStartPage();
    }

}
