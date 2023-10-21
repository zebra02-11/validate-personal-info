package personalinfovalidation;

public class SsnException extends Exception {
    public SsnException(String message) {
        super(message);
    }

    public SsnException(String message, Throwable cause) {
        super(message, cause);
    }
}
