package driver;

import exceptions.DriverNotSupportedException;
import org.openqa.selenium.WebDriver;

public interface DriverFactory {
    WebDriver createDriverInstance() throws DriverNotSupportedException;
}