package exceptions;

public class DriverNotSupportedException extends RuntimeException {
    private static final String MSG = "Browser %s is not supported";

    public DriverNotSupportedException(String browserName) {
        super(MSG.formatted(browserName));
    }
}
