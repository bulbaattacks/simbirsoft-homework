package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

class GeneratorServiceTest {

    @ParameterizedTest
    @CsvSource({
            "0 0 0 1 2 5 2 6 6 7, abzap",
            "0 4 0 6 2 5 7 6 9 9, egzyv"
    })
    void getFirstName(String rangeForPostCode, String expectedFirstName) {
        List<Integer> range = Arrays.stream(rangeForPostCode.split("\\s"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
        var gsTest = new GeneratorService(range);

        var resultName = gsTest.getFirstName();
        Assertions.assertEquals(expectedFirstName, resultName);
    }
}
