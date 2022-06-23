package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BusinessTripsPage extends BasePage {
    @FindBy(xpath = "//a[@title]")
    private List<WebElement> allButtons;


    public CreateBusinessTripPage clickButton(String buttonName) {
        for (WebElement button : allButtons) {
            if (button.getText().contains(buttonName)) {
                button.click();
                return pageManager.getCreateBusinessTripPage();
            }
        }
        Assert.fail("Не найдена кнопка " + buttonName);
        return pageManager.getCreateBusinessTripPage();
    }
}
