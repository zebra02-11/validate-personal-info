package personalinfovalidation;

/**
 * Class used to validate personal name.
 * <p>
 * Requirements:
 * - Not blank
 * - Should include Swedish alphabetical characters including swedish å, ä and Ö
 *
 * @author Daria Kolesnik
 */
public class NameValidator implements Validator {

    public static boolean isCorrectName(String line) {
        return Validator.validateByContext(line, RegexpPatterns.NAME_PATTERN);
    }
}
