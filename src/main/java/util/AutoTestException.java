package util;

import java.util.function.Supplier;

public class AutoTestException extends RuntimeException implements Supplier<AutoTestException> {
    public AutoTestException(String message, Throwable cause) {
        super(message, cause);
    }

    public AutoTestException(String message) {
        super(message);
    }

    @Override
    public AutoTestException get() {
        return null;
    }
}
