package service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GeneratorService {

    private List<Integer> range;

    public GeneratorService() {
        range = new Random()
                .ints(0,9)
                .limit(10)
                .boxed()
                .collect(Collectors.toList());
    }

    public String getPostCode() {
        return range.stream().map(String::valueOf).collect(Collectors.joining(""));
    }

    public String getFirstName() {
        var sb = new StringBuilder();

        for (int i = 1; i < range.size(); i += 2) {
            int firstDigit = range.get(i - 1);
            int secondDigit = range.get(i);

            int targetDigit;
            if (firstDigit == 0 && secondDigit == 0) {
                targetDigit = 0;
            } else if (firstDigit == 0 && secondDigit != 0) {
                targetDigit = secondDigit;
            } else {
                targetDigit = firstDigit * 10 + secondDigit;
            }

            while (targetDigit > 25) {
                targetDigit = targetDigit - 25 - 1;
            }

            char targetChar = (char) (targetDigit + 97);
            sb.append(targetChar);
        }
        return sb.toString();
    }
}
