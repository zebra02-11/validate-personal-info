package personalinfovalidation;

public final class Rules {

    public static final Rules DEFAULT = new Rules();

    final boolean allowInterimNumbers;

    final boolean allowCoordinationNumber;

    public Rules() {
        this.allowInterimNumbers = false;
        this.allowCoordinationNumber = true;
    }

    public Rules(boolean allowCoordinationNumber, boolean allowInterimNumbers) {
        this.allowCoordinationNumber = allowCoordinationNumber;
        this.allowInterimNumbers = allowInterimNumbers;
    }
}
