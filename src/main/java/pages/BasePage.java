package pages;

import org.openqa.selenium.WebDriver;
import util.PropertiesLoader;

public abstract class BasePage {
    protected final String baseUrl = PropertiesLoader.load().getProperty("base.url");
    protected WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected abstract void open();
}
