package personalinfovalidation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class NameValidatorTest {

    @ParameterizedTest
    @MethodSource("provideName")
    void isValidSsn(String input, boolean expected) {
        assertEquals(expected, NameValidator.isCorrectName(input));
    }


    private static Stream<Arguments> provideName() {
        return Stream.of(
                Arguments.of("Daria Kolesnik", true),
                Arguments.of("Däriå KölesnikÖ", true),
                Arguments.of("Ö", true),
                Arguments.of("ö", true),
                Arguments.of("ä", true),
                Arguments.of("Ä", true),
                Arguments.of("å", true),
                Arguments.of("Å", true),
                Arguments.of("201204129991", false),
                Arguments.of("askjdhjhd87874", false),
                Arguments.of("sdfjksn-sjbdf", false),
                Arguments.of("", false),
                Arguments.of(null, false),
                Arguments.of(" ", false)
        );
    }
}