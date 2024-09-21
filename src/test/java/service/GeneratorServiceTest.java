package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class GeneratorServiceTest {

    @Test
    void getFirstName_return_abzap() {
        List<Integer> range = List.of(0, 0, 0, 1, 2, 5, 2, 6, 6, 7);
        var gsTest = new GeneratorService(range);

        var resultName = gsTest.getFirstName();
        Assertions.assertEquals("abzap", resultName);
    }

    @Test
    void getFirstName_return_dfzav() {
        List<Integer> range = List.of(0, 4, 0, 6, 2, 5, 7, 6, 9, 9);
        var gsTest = new GeneratorService(range);

        var resultName = gsTest.getFirstName();
        Assertions.assertEquals("egzyv", resultName);
    }
}
