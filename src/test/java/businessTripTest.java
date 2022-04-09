import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class businessTripTest {
    private WebDriver driver;
    Wait<WebDriver> wait;

    @Before
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        String startUrl = "http://training.appline.ru/user/login";
        wait = new WebDriverWait(driver, 5, 1000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(startUrl);
    }

    @Test
    public void createBusinessTripTest() throws InterruptedException{

        // Вводим логин/пароль, нажимаем Войти
        //driver.findElement(By.id("prependedInput")).click();
        driver.findElement(By.id("prependedInput")).sendKeys("Taraskina Valeriya");
        //driver.findElement(By.id("prependedInput2")).click();
        driver.findElement(By.id("prependedInput2")).sendKeys("testing");
        driver.findElement(By.id("_submit")).click();

        // Проверяем наличие заголовка "Панель быстрого запуска"
        Assert.assertEquals("Панель быстрого запуска", driver.findElement(By.xpath("//h1[contains(@class, 'oro-subtitle')]")).getText());

        // Выбираем Расходы - Командировки
        driver.findElement(By.xpath("//span[@class='title'][text()='Расходы']")).click();
        //поставить проверку что появился выпадающий список
        driver.findElement(By.xpath("//div[@id='main-menu']/ul/li[3]/ul/li[4]/a/span")).click();

        // Нажимаем кнопку, проверяем наличие заголовка "Создать командировку"
        driver.findElement(By.xpath("//a[contains(@class,'btn back icons-holder-text')]")).click();
        Assert.assertEquals("Создать командировку", driver.findElement(By.xpath("//h1[@class='user-name']")).getText());

        // заполяем форму командировки
        driver.findElement(By.xpath("//select/option[text()='Выберите подразделение']")).click();
        driver.findElement(By.xpath("//option[text()='Отдел внутренней разработки']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Открыть список')]")).click();
        driver.findElement(By.xpath("//div/a/span[contains(@class, 'select2-chosen')]")).click();
        driver.findElement(By.xpath("//li/div[text()='(Хром) Призрачная Организация Охотников']")).click();
        driver.findElement(By.xpath("//label[text()='Заказ билетов']/../input")).click();
        // поверить что чекбокс установлен (?)
        //Assert.assertTrue(ExpectedConditions.elementToBeSelected(By.xpath("//label[text()='Заказ билетов']/../input")));
        // города
        driver.findElement(By.xpath("//input[contains(@data-name, 'arrival-city')]")).sendKeys("Россия, Анадырь");
        // проверяем что город отправления заполнен по умолчанию
        String departureCity = driver.findElement(By.xpath("//input[contains(@data-name, 'departure-city')]")).getAttribute("value");
        Assert.assertNotEquals("Поле пустое", "", departureCity);
        //даты
        WebElement departureDate = driver.findElement(By.xpath("//input[contains(@id, 'departureDatePlan')]"));
        WebElement returnDate = driver.findElement(By.xpath("//input[contains(@id, 'returnDatePlan')]"));
        departureDate.click();
        departureDate.sendKeys("25.04.2022");
        returnDate.click();
        returnDate.sendKeys("01.05.2022");







        // подождем перед закрытием браузера
        Thread.sleep(5000);
    }

    @After
    public void afterTest(){
        driver.quit();
    }
}
