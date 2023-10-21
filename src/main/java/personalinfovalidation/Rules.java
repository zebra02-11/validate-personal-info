package personalinfovalidation;

public final class Rules {

    public static final Rules DEFAULT = new Rules();

    final boolean allowInterimNumbers;

    public Rules() {
        this.allowInterimNumbers = false;
    }

    public Rules(boolean allowInterimNumbers) {
        this.allowInterimNumbers = allowInterimNumbers;
    }
}
