package pages;

import org.openqa.selenium.WebDriver;
import util.PropertiesLoader;

public class BankingManagerPage extends BasePage {
    public final String pathToPage;

    public BankingManagerPage(WebDriver driver) {
        super(driver);
        pathToPage = PropertiesLoader.load().getProperty("path.to.manager");
    }

    @Override
    public void open() {
        driver.get(super.baseUrl + pathToPage);
    }
}
