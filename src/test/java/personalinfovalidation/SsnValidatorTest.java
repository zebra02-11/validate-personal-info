package personalinfovalidation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SsnValidatorTest {

    @ParameterizedTest
    @MethodSource("provideSsn")
    void isValidSsn(String input, boolean expected) {
        assertEquals(expected, SsnValidator.isValidSsn(input, Optional.empty()));
    }


    @ParameterizedTest
    @MethodSource("provideSsnWhenInterimNumbersAreAllowed")
    void isValidSsnWhenInterimNumbersAreAllowed(String input, boolean expected) {
        Optional<Rules> rules = Optional.of(new Rules(true, true));
        assertEquals(expected, SsnValidator.isValidSsn(input, rules));
    }

    private static Stream<Arguments> provideSsn() {
        return Stream.of(
                Arguments.of(null, false),
                Arguments.of("not blank", false),
                Arguments.of("20120412-9991", true),
                Arguments.of("201204129991", true),
                Arguments.of("1204129991", true),
                Arguments.of("120412-9991", true),
                Arguments.of("19850864-9962", true),
                Arguments.of("111111-0000", false),
                //older then 100
                Arguments.of("185204269996", true),
                Arguments.of("18520426+9996", true),
                Arguments.of("520426+9996", true),
                Arguments.of("5204269996", true)
        );
    }

    private static Stream<Arguments> provideSsnWhenInterimNumbersAreAllowed() {
        return Stream.of(
                Arguments.of("20000101T220", true),
                Arguments.of("000101T220", true),
                Arguments.of("20000101-T220", true),
                Arguments.of("20000101E221", false)
        );
    }
}