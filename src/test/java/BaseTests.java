import managers.DriverManager;
import managers.PageManager;
import managers.PropManager;
import org.junit.After;
import org.junit.Before;

public class BaseTests {

    private final DriverManager driverManager = DriverManager.getDriverManger();
    protected PageManager pageManager = PageManager.getPageManager();
    private final PropManager propManager = PropManager.getPropManager();

    @Before
    public void beforeEach() {
        driverManager.getDriver().get(propManager.getProperty("base.url"));
    }

    @After
    public void afterAll() {
        driverManager.quitDriver();
    }
}