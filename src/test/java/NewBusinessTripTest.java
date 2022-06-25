import org.junit.Test;

public class NewBusinessTripTest extends BaseTests {

    @Test
    public void createBusinessTripTest() throws InterruptedException {
        String login = "Taraskina Valeriya";
        String password = "testing";

        pageManager.getLoginPage()
                .logIn(login, password)
                .checkPageTitle("Панель быстрого запуска")
                .selectMainMenuElement("Расходы")
                .selectSubMenuElement("Командировки")
                .clickButton("Создать командировку")
                .checkPageTitle("Создать командировку")
                .openDepartmentsList()
                .chooseDepartment("Отдел внутренней разработки")
                .chooseOrg("(Хром) Призрачная Организация Охотников")
                .chooseTask("Заказ билетов")
                .inputCities("Россия, Красногорск", "Венгрия, Будапешт")
                .inputDates(7, 15)
                .focusToPage()
                .checkSelectedData("Подразделение", "Отдел внутренней разработки")
                .checkSelectedData("Организация", "(Хром) Призрачная Организация Охотников")
                .CheckEnteredData("Город выезда", "Россия, Красногорск")
                .CheckEnteredData("Город прибытия", "Венгрия, Будапешт")
                .CheckEnteredData("Дата выезда", String.valueOf(7))
                .CheckEnteredData("Дата возвращения", String.valueOf(15))
                .clickSaveAndClose()
                .checkErrorMessage("Список командируемых сотрудников не может быть пустым");

        // подождем перед закрытием браузера, чтобы увидеть ошибки
        Thread.sleep(5000);
    }
}
