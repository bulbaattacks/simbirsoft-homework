import driver.impl.DriverFactoryImpl;
import exceptions.DriverNotSupportedException;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.BankingManagerPage;

class InitTest {
    WebDriver driver;

    @BeforeEach
    void setUp() throws DriverNotSupportedException {
        driver = new DriverFactoryImpl().createDriverInstance();
    }

    @AfterEach
    public void close() {
        if (this.driver != null) {
            this.driver.close();
            this.driver.quit();
        }
    }

    @Test
    @Step("Add Customer")
    void openPage() {
        new BankingManagerPage(driver).open();

    }
}
