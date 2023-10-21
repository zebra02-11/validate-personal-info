package personalinfovalidation;

import org.junit.platform.commons.util.StringUtils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class used to validate personalNumber/socialSecurityNumber/Ssn.
 * <p>
 * <p>
 * Requirements:
 * - 10 or 12 digits
 * - Can include hyphen or plus sign
 * - Calculate and validate checksum using the Luhn algorithm
 *
 * @author Daria Kolesnik
 */

public class SsnValidator {
    private static final Pattern regexPattern = Pattern.compile(RegexpPatterns.SSN_PATTERN);

    private static final Pattern interimPatternTest = Pattern.compile(RegexpPatterns.INTERIM_PATTERN);

    /**
     * The method should be called to check is id number is valid.
     * Currently, we use default settings/rules when interim numbers are not available
     *
     * @param input         - ssn we want to check
     * @param optionalRules - default for now, a param can be used to set interim numbers property to true
     */
    public static boolean isValidSsn(String input, Optional<Rules> optionalRules) {
        try {
            Rules rules = optionalRules.isEmpty() ? Rules.DEFAULT : optionalRules.get();
            validateSsn(input, rules);
            return true;
        } catch (SsnException e) {
            return false;
        }
    }

    private static void validateSsn(String input, Rules rules) throws SsnException {
        checkSsnLength(input);

        Matcher matches = regexPattern.matcher(input);
        if (!matches.find()) {
            throw new SsnException("Failed to parse personal identity number. Invalid input.");
        }

        if (!rules.allowInterimNumbers && interimPatternTest.matcher(input).find()) {
            throw new SsnException(input + " contains non-integer characters and options are set to not allow interim numbers");
        }

        Ssn personalSecurityNumber = getSsn(matches);

        checkDate(personalSecurityNumber);

        String nums = matches.group(6);
        if (rules.allowInterimNumbers) {
            nums = nums.replaceFirst(RegexpPatterns.INTERIM_PATTERN, "1");
        }
        String personalNumber = personalSecurityNumber.getYear() + personalSecurityNumber.getMonth() + personalSecurityNumber.getDay() + nums;
        checkControlNumber(personalNumber, Integer.parseInt(personalSecurityNumber.getControlNumber()));
    }

    private static Ssn getSsn(Matcher matches) {
        String century;
        String decade = matches.group(2);
        if (!StringUtils.isBlank(matches.group(1))) {
            century = matches.group(1);
        } else {
            int born = LocalDate.now().getYear() - Integer.parseInt(decade);
            if (!StringUtils.isBlank(matches.group(5)) && matches.group(5).equals("+")) {
                born -= 100;
            }
            century = Integer.toString(born).substring(0, 2);
        }

        int day = 1 + (Integer.parseInt(matches.group(4)) - 1) % 60;

        return new Ssn(day,
                century,
                decade,
                century + decade,
                matches.group(3),
                matches.group(4),
                matches.group(6) + matches.group(7),
                matches.group(7)
        );
    }

    private static void checkDate(Ssn personalSecurityNumber) throws SsnException {
        try {
            LocalDate.of(Integer.parseInt(personalSecurityNumber.getFullYear()),
                    Integer.parseInt(personalSecurityNumber.getMonth()),
                    personalSecurityNumber.getRealDay());
        } catch (DateTimeException e) {
            throw new SsnException("Invalid personal identity number: " + e.getMessage(), e);
        }
    }

    private static void checkSsnLength(String ssn) throws SsnException {
        if (ssn == null) {
            throw new SsnException("Failed to parse personal identity number. Invalid input.");
        }
        if (ssn.length() < 10 || ssn.length() > 13) {
            throw new SsnException("The personal identity number is incorrect. Invalid input.");
        }
    }

    /**
     * Luhn/mod10 algorithm implementation.
     * Used to calculate a checksum from the passed value.
     * <p>
     * The checksum is returned and tested against the control number in the personal identity number
     * to make sure that it is a valid number.
     */
    private static void checkControlNumber(String personalNumber, Integer controlNumber) throws SsnException {
        // Get the sum of all the digits, however we need to replace the value
        // of the first digit, and every other digit, with the same digit
        // multiplied by 2. If this multiplication yields a number greater
        // than 9, then add the two digits together to get a single digit
        // number.
        //
        // The digits we need to replace will be those in an even position for
        // card numbers whose length is an even number, or those are an odd
        // position for card numbers whose length is an odd number. This is
        // because the Luhn algorithm reverses the card number, and doubles
        // every other number starting from the second number from the last
        // position.


        int sum = 0;

        for (int i = 0; i < personalNumber.length(); i++) {
            int digit = Integer.parseInt(personalNumber.substring(i, (i + 1)));
            if (i % 2 == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }

        // The check digit is the number required to make the sum a multiple of 10.
        int mod = sum % 10;
        var calculatedNumber = (mod == 0) ? 0 : 10 - mod;

        if (calculatedNumber != controlNumber) {
            throw new SsnException("Invalid personal identity number.");
        }
    }
}