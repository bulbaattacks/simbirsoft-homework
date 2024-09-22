package driver.impl;

import driver.DriverFactory;
import exceptions.DriverNotSupportedException;
import org.openqa.selenium.WebDriver;
import util.PropertiesLoader;


public class DriverFactoryImpl implements DriverFactory {
    private final String browserType = PropertiesLoader.load().getProperty("browserName");

    @Override
    public WebDriver createDriverInstance() throws DriverNotSupportedException {
        if (browserType.equals("chrome")) {
            System.err.println("CHROME xChromeWebDriver");
            return ChromeWebDriver.createDriver();
        }
        if (browserType.equals("ci")) {
            System.err.println("CI xChromeWebDriver");
            return ChromeWebDriver.createCiDriver();
        }
        throw new DriverNotSupportedException(browserType);
    }
}