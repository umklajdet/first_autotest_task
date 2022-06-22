import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StartPage extends BasePage {
    @FindBy(xpath = "//h1[contains(@class, 'oro-subtitle')]")
    private WebElement title;

    @FindBy(xpath = "//ul[contains(@class, 'main-menu')]/li")
    private List<WebElement> mainMenu;


    public String getTitle(){
        return title.getText();
    }

    public void selectMainMenuElement(String elementName){
        // реализовать iterator или listIterator вместо for?
        for(WebElement menuElement : mainMenu){
            if (menuElement.getText().contains(elementName)) {
                menuElement.click();
                return;
            }
        }
        Assert.fail("В меню не найден пункт " + elementName);
    }

}
