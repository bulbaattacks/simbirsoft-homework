package pages;

import driver.impl.DriverFactoryImpl;
import exceptions.DriverNotSupportedException;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import service.GeneratorService;

import java.time.Duration;

class BankingManagerPageTest {
    private static final String SUCCESS_ADDED_CLIENT_MSG = "Customer added successfully with customer id";

    private WebDriver driver;
    private BankingManagerPage page;

    @BeforeEach
    void setUp() throws DriverNotSupportedException {
        driver = new DriverFactoryImpl().createDriverInstance();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
    @DisplayName("Тест создания клиента.")
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
        Assertions.assertTrue(page.getaAlertText().contains(SUCCESS_ADDED_CLIENT_MSG),
                "Текст не содержит сообщения %s".formatted(SUCCESS_ADDED_CLIENT_MSG));
        page.acceptAlert();
        page.clickCustomersButton();
        Assertions.assertTrue(page.customerExistsInTable(name, surname, postcode));
    }

    @Test
    @DisplayName("Сортировка по первому имени")
    void sortCustomerstByFirstName() {
        page.open()
                .verifyPage()
                .clickCustomersButton()
                .sortByFirstNameAsc();
        Assertions.assertTrue(page.checkSortAsc("Albus"));
    }

    @Test
    @DisplayName("Удаление клиента")
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
