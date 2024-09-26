package service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GeneratorService {
    private static final int UNICODE_TABLE_OFFSET = 97;
    private static final int LETTERS_IN_CICLE = 25;

    private final List<Integer> range;

    public GeneratorService() {
        range = new Random()
                .ints(0,9)
                .limit(10)
                .boxed()
                .toList();
    }

    public GeneratorService(List<Integer> range) {
        this.range = range;
    }

    public String getPostCode() {
        return range.stream().map(String::valueOf).collect(Collectors.joining(""));
    }

    public String getFirstName() {
        var sb = new StringBuilder();

        for (int i = 1; i < range.size(); i += 2) {
            int decimalDigit = range.get(i - 1);
            int singleDigit = range.get(i);

            int targetDigit = decimalDigit * 10 + singleDigit;

            if (targetDigit > LETTERS_IN_CICLE) {
                targetDigit = targetDigit % 26;
            }
            char targetChar = (char) (targetDigit + UNICODE_TABLE_OFFSET);
            sb.append(targetChar);
        }
        return sb.toString();
    }
}
