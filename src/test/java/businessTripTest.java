import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class businessTripTest {
    private WebDriver driver;
    private String startUrl;
    Wait<WebDriver> wait;

    @Before
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        startUrl = "http://training.appline.ru/user/login";
        wait = new WebDriverWait(driver, 5, 1000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(startUrl);
    }

    @Test
    public void createBusinessTripTest() throws InterruptedException{

        // Шаг 1. Вводим логин/пароль, нажимаем Войти
        //driver.findElement(By.id("prependedInput")).click();
        driver.findElement(By.id("prependedInput")).sendKeys("Taraskina Valeriya");
        //driver.findElement(By.id("prependedInput2")).click();
        driver.findElement(By.id("prependedInput2")).sendKeys("testing");
        driver.findElement(By.id("_submit")).click();

        Thread.sleep(5000);
    }

    @After
    public void afterTest(){
        driver.quit();
    }
}
