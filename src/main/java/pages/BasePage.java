package pages;

import managers.DriverManager;
import managers.PageManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected DriverManager driverManager = DriverManager.getDriverManger();
    protected Wait<WebDriver> wait = new WebDriverWait(driverManager.getDriver(), 5, 1000);
    protected PageManager pageManager = PageManager.getPageManager();

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }
}
