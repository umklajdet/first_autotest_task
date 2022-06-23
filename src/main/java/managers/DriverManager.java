package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {
    private WebDriver driver;
    private static DriverManager INSTANCE;

    // пустой конструктор (singleton)
    private DriverManager() {
    }

    // инициализация DriverManager
    public static DriverManager getDriverManger() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    // инициализация веб-драйвера
    public WebDriver getDriver(){
        if(driver == null){
            initDriver();
        }
        return driver;
    }

    private void initDriver(){
        // пока только для Хром, далее будет вынесено в properties
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void quitDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }

}
