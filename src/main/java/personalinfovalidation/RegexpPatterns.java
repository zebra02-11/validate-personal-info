package personalinfovalidation;


public final class RegexpPatterns {
    static final String SSN_PATTERN = "^(\\d{2})?(\\d{2})(\\d{2})(\\d{2})([-+]?)?((?!000)\\d{3}|[TRSUWXJKLMN]\\d{2})(\\d?)$";
    static final String INTERIM_PATTERN = "(?![-+])\\D";
    static final String NAME_PATTERN = "^[a-zA-ZäÄåÅöÖ\\s]+$";
}
