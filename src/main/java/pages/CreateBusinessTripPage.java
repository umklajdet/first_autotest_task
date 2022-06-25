package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @FindBy(xpath = "//div[@id='oro-dropdown-mask']")
    private WebElement pageFocus;

    @FindBy(xpath = "//div[contains(@id, 'business_trip_businessUnit')]/span")
    private WebElement selectedDepartment;

    @FindBy(xpath = "//span[@class='select2-chosen']")
    private WebElement selectedOrg;

    @FindBy(xpath = "//button[contains(text(), 'Сохранить и закрыть')]")
    private WebElement saveButton;

    @FindBy(xpath = "//span[text()='Командированные сотрудники']/../../div/div/span")
    private WebElement employeeList;

    @FindBy(xpath = "//span[text()='Внештатные сотрудники']/../../div/div/span")
    private WebElement outsourceEmployeeList;

    private WebElement elementForCheck = null;

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
        arrivalCity.clear();
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
        pageFocus.click();
        return this;
    }


    // проверки заполнения полей (Значения, выбранные из выпадающего списка)
    public CreateBusinessTripPage checkSelectedData(String dataName, String expectedValue) {
        LocalDate today = LocalDate.now();
        switch (dataName) {
            case "Подразделение":
                elementForCheck = selectedDepartment;
                break;
            case "Организация":
                elementForCheck = selectedOrg;
                break;
            default:
                Assert.fail("Поле " + dataName + " отсутствует на странице или действие для него не задано");
        }
        assertEquals("Значение не сответвует ожидаемому",
                expectedValue,
                elementForCheck.getText());
        return this;
    }

    // проверки заполнения полей (Значения, введенные с клавиатуры)
    public CreateBusinessTripPage CheckEnteredData(String dataName, String expectedValue) {
        LocalDate today = LocalDate.now();
        switch (dataName) {
            case "Город выезда":
                elementForCheck = departureCity;
                break;
            case "Город прибытия":
                elementForCheck = arrivalCity;
                break;
            case "Дата выезда":
                elementForCheck = arrivalDate;
                expectedValue = today.plusDays(Integer.parseInt(expectedValue)).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                break;
            case "Дата возвращения":
                elementForCheck = returnDate;
                expectedValue = today.plusDays(Integer.parseInt(expectedValue)).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                break;
            default:
                Assert.fail("Поле " + dataName + " отсутствует на странице или действие для него не задано");
        }
        assertEquals("Значение не сответвует ожидаемому",
                expectedValue,
                elementForCheck.getAttribute("value"));
        return this;
    }

    // проверки заполнения полей (Выбранные чекбоксы)
    public CreateBusinessTripPage CheckSelectedCheckboxData(String dataName) {
        for (WebElement task : tasksList) {
            if (task.getText().contains(dataName)) {
                elementForCheck = task.findElement(By.xpath("../input"));
                assertTrue("Чекбокс " + dataName + " не выбран", elementForCheck.isSelected());
                return this;
            }
        }
        Assert.fail("Чекбокс " + dataName + " отсутствует на странице или действие для него не задано");
        return this;
    }

    public CreateBusinessTripPage clickSaveAndClose() {
        saveButton.click();
        return this;
    }

    public void checkErrorMessage(String expectedValue) {
        assertEquals("Значение не сответвует ожидаемому",
                expectedValue,
                employeeList.getText());
        assertEquals("Значение не сответвует ожидаемому",
                expectedValue,
                outsourceEmployeeList.getText());
    }
}