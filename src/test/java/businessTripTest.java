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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.Keys.TAB;

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
        // города
        driver.findElement(By.xpath("//input[contains(@data-name, 'departure-city')]")).clear();
        driver.findElement(By.xpath("//input[contains(@data-name, 'departure-city')]")).sendKeys("Россия, Сочи");
        driver.findElement(By.xpath("//input[contains(@data-name, 'arrival-city')]")).sendKeys("Россия, Анадырь");
        //даты
        WebElement departureDate = driver.findElement(By.xpath("//input[contains(@name, 'date_selector_crm_business_trip_departureDatePlan')]"));
        WebElement returnDate = driver.findElement(By.xpath("//input[contains(@name, 'date_selector_crm_business_trip_returnDatePlan')]"));
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String departureDateStr = formatter.format(today.getTime() + 7 * 24 * 3600 * 1000);
        String returnDateStr = formatter.format(today.getTime() + 15 * 24 * 3600 * 1000);
        departureDate.sendKeys(departureDateStr);
        returnDate.sendKeys(returnDateStr);
        returnDate.sendKeys(TAB);

        // проверяем введенные данные
        checkValues(By.xpath("//option[text()='Отдел внутренней разработки']"), "Отдел внутренней разработки");
        checkValues(By.xpath("//input[@name='crm_business_trip[company]']"), "(Хром) Призрачная Организация Охотников");
        checkValues(By.xpath("//input[contains(@data-name, 'departure-city')]"), "Россия, Сочи");
        checkValues(By.xpath("//input[contains(@data-name, 'arrival-city')]"), "Россия, Анадырь");
        checkValues(By.xpath("//input[contains(@name, 'date_selector_crm_business_trip_departureDatePlan')]"), departureDateStr);
        checkValues(By.xpath("//input[contains(@name, 'date_selector_crm_business_trip_returnDatePlan')]"), returnDateStr);
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Заказ билетов']/../input")).isEnabled());

        // подождем перед закрытием браузера
        Thread.sleep(5000);
    }

    public void checkValues(By locator, String checkStr) {
        Assert.assertEquals(driver.findElement(locator).getText(), checkStr);
    }

    @After
    public void afterTest(){
        driver.quit();
    }
}
