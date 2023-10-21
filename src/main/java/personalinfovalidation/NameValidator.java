package personalinfovalidation;

import org.junit.platform.commons.util.StringUtils;

/**
 * Class used to validate personal name.
 * <p>
 * Requirements:
 * - Not blank
 * - Should include Swedish alphabetical characters including swedish å, ä and Ö
 *
 * @author Daria Kolesnik
 */
public class NameValidator {

    public static boolean isCorrectName(String line) {
        return !StringUtils.isBlank(line) && line.matches(RegexpPatterns.NAME_PATTERN);
    }
}
