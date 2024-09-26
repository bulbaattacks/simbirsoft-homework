package exceptions;

public class NotRightPageException extends RuntimeException {
    private static final String MSG = "This is not right page %s";

    public NotRightPageException(String currentUrl) {
        super(MSG.formatted(currentUrl));
    }
}
