package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BusinessTripsPage extends BasePage {
    @FindBy(xpath = "//a[@title]")
    private List<WebElement> allButtons;

    @FindBy(xpath = "//a[@title = 'Создать командировку']")
    private WebElement createBusinessTripButton;


    public CreateBusinessTripPage clickButton(String buttonName) throws InterruptedException {
        Thread.sleep(3000);
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
