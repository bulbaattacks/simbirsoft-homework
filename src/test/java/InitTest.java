import driver.impl.DriverFactoryImpl;
import exceptions.DriverNotSupportedException;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.BankingManagerPage;
import service.GeneratorService;

class InitTest {
    WebDriver driver;
    BankingManagerPage page;

    @BeforeEach
    void setUp() throws DriverNotSupportedException {
        driver = new DriverFactoryImpl().createDriverInstance();
        page = new BankingManagerPage(driver);
    }

    @AfterEach
    public void close() {
        if (this.driver != null) {
            this.driver.close();
            this.driver.quit();
        }
    }

    @Test
    @Step("Add customer")
    @DisplayName("Тест создания клиента.")
    void addCustomer() {
        page.open();
        page.verifyPage();
        page.clickAddCustomerButton();

        var gs = new GeneratorService();
        String name = gs.getFirstName();
        String surname = "Pupkin";
        String postcode = gs.getPostCode();
        page.setFirstname(name);
        page.setLastname(surname);
        page.setPostCode(postcode);
        page.clickToSbmt();
        Assertions.assertTrue(page.getaAlertText().contains("Customer added successfully with customer id"));
        page.acceptAlert();
        page.clickCustomersButton();
        Assertions.assertTrue(page.customerExistsInTable(name, surname, postcode));
    }

    @Test
    @DisplayName("Сортировка по первому имени")
    void sortCustomerstByFirstName() {
        page.open();
        page.verifyPage();
        page.clickCustomersButton();
        page.sortByFirstNameAsc();
        Assertions.assertTrue(page.checkSortAsc("Albus"));
    }

    @Test
    @DisplayName("Удаление клиента")
    void deleteCustomer() {
        page.open();
        page.verifyPage();
        page.clickCustomersButton();
        var targetCells = page.deleteCustomer();
        String name = targetCells.get(0),
                surname = targetCells.get(1),
                postCode = targetCells.get(2);
        Assertions.assertFalse(page.customerExistsInTable(name, surname, postCode));
    }
}
