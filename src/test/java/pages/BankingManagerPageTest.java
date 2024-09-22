package pages;

import driver.impl.DriverFactoryImpl;
import exceptions.DriverNotSupportedException;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import service.GeneratorService;

class BankingManagerPageTest {
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
    @Step("Тест создания клиента.")
    void addCustomer() {
        page.open()
                .verifyPage()
                .clickAddCustomerButton();

        var gs = new GeneratorService();
        String name = gs.getFirstName();
        String surname = "Pupkin";
        String postcode = gs.getPostCode();
        page.setFirstname(name)
                .setLastname(surname)
                .setPostCode(postcode)
                .clickToSbmt();
        Assertions.assertTrue(page.getaAlertText().contains("Customer added successfully with customer id"));
        page.acceptAlert();
        page.clickCustomersButton();
        Assertions.assertTrue(page.customerExistsInTable(name, surname, postcode));
    }

    @Test
    @Step("Сортировка по первому имени")
    void sortCustomerstByFirstName() {
        page.open()
                .verifyPage()
                .clickCustomersButton()
                .sortByFirstNameAsc();
        Assertions.assertTrue(page.checkSortAsc("Albus"));
    }

    @Test
    @Step("Удаление клиента")
    void deleteCustomer() {
        page.open()
                .verifyPage()
                .clickCustomersButton();
        var targetCells = page.deleteCustomer();
        String name = targetCells.get(0),
                surname = targetCells.get(1),
                postCode = targetCells.get(2);
        Assertions.assertFalse(page.customerExistsInTable(name, surname, postCode));
    }
}
