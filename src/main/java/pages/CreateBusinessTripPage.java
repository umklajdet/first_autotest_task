package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CreateBusinessTripPage extends BasePage {

    @FindBy(xpath = "//h1[@class='user-name']")
    private WebElement pageTitle;

    @FindBy(xpath = "//select/option[text()='Выберите подразделение']")
    private WebElement departmentChoosing;

    @FindBy(xpath = "//select[contains(@id, 'businessUnit')]/option")
    private List<WebElement> departmentList;

    @FindBy(xpath = "//a[contains(text(),'Открыть список')]")
    private WebElement openOrgList;

    @FindBy(xpath = "//div/a/span[contains(@class, 'select2-chosen')]")
    private WebElement orgChoosing;

    @FindBy(xpath = "//ul[@class='select2-results']/li/div")
    private List<WebElement> orgList;

    @FindBy(xpath = "//input/../label")
    private List<WebElement> tasksList;

    @FindBy(xpath = "//input[contains(@data-name, 'departure-city')]")
    private WebElement departureCity;

    @FindBy(xpath = "//input[contains(@data-name, 'arrival-city')]")
    private WebElement arrivalCity;

    @FindBy(xpath = "//input[contains(@name, 'date_selector_crm_business_trip_departureDatePlan')]")
    private WebElement arrivalDate;

    @FindBy(xpath = "//input[contains(@name, 'date_selector_crm_business_trip_returnDatePlan')]")
    private WebElement returnDate;


    // проверяет заголовок страницы
    public CreateBusinessTripPage checkPageTitle(String expectedTitle) {
        Assert.assertEquals("Заголовок не соответствует ожидаемому",
                expectedTitle,
                pageTitle.getText());
        return this;
    }

    // отрывает список подразделений
    public CreateBusinessTripPage openDepartmentsList() {
        departmentChoosing.click();
        return this;
    }

    // выбор подразделения
    public CreateBusinessTripPage chooseDepartment(String departmentName) {
        for (WebElement department : departmentList) {
            if (department.getText().contains(departmentName)) {
                department.click();
                return this;
            }
        }
        Assert.fail("Не найдено подразделение " + departmentName);
        return this;
    }

    // выбор организации
    public CreateBusinessTripPage chooseOrg(String orgName) {
        openOrgList.click();
        orgChoosing.click();
        for (WebElement org : orgList) {
            if (org.getText().contains(orgName)) {
                org.click();
                return this;
            }
        }
        Assert.fail("Не найдена организация " + orgName);
        return this;
    }

    // выбор задач
    public CreateBusinessTripPage chooseTask(String taskName) {
        for (WebElement task : tasksList) {
            if (task.getText().contains(taskName)) {
                task.click();
                return this;
            }
        }
        Assert.fail("Не найдена задача " + taskName);
        return this;
    }

    // ввод городов
    public CreateBusinessTripPage inputCities(String departureCityTitle, String arrivalCityTitle) {
        departureCity.clear();
        departureCity.sendKeys(departureCityTitle);
        arrivalCity.sendKeys(arrivalCityTitle);
        return this;
    }

    // ввод дат
    public CreateBusinessTripPage inputDates(int daysToArrivalDate, int daysToReturnDate) {
        LocalDate today = LocalDate.now();
        String arrivalDateStr = today.plusDays(daysToArrivalDate).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String returnDateStr = today.plusDays(daysToReturnDate).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        arrivalDate.sendKeys(arrivalDateStr);
        returnDate.sendKeys(returnDateStr);
        return this;
    }

    public CreateBusinessTripPage focusToPage() {
        pageTitle.click();
        return this;
    }


    // проверки заполнения полей - непонятно как делать
    public void checkSelectedFromListData(String expectedValue, WebElement element) {
        assertEquals("Значение не сответвует ожидаемому",
                expectedValue,
                element.getText());
    }

    public void checkFilledData(String expectedValue, WebElement element) {
        assertEquals("Значение не сответвует ожидаемому",
                expectedValue,
                element.getAttribute("value"));
    }

    public void checkSelectedCheckBox(){

    }
}
