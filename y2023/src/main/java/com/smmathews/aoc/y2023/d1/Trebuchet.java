package com.smmathews.aoc.y2023.d1;

import com.smmathews.aoc.y2023.Day;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@ShellComponent
public class Trebuchet implements Day {

    enum writtenNumbers {
        zero,
        one,
        two,
        three,
        four,
        five,
        six,
        seven,
        eight,
        nine
    }

    private final Map<String, Integer> writtenLookups = Arrays.stream(writtenNumbers.values()).collect(Collectors.toMap(Enum::name, Enum::ordinal));

    private boolean checkWritten = false;

    @Override
    public String run(String input) {
        return String.valueOf(input.lines().mapToInt(line -> {
            Integer[] first = {null};
            int[] firstIndex = {line.length()};
            Integer[] last = {null};
            int[] lastIndex = {0};
            for (int i = 0; i < line.length(); i++) {
                try {
                    last[0] = Integer.valueOf(line.substring(i, i + 1));
                    lastIndex[0] = i;
                    if(first[0] == null) {
                        first[0] = last[0];
                        firstIndex[0] = i;
                    }
                } catch (NumberFormatException ignored) {
                }
            }

            if(checkWritten) {
                if (firstIndex[0] > 0) {
                    writtenLookups.forEach((key, value) -> {
                        var found = line.indexOf(key, 0, line.length());
                        if (found != -1 && found < firstIndex[0]) {
                            firstIndex[0] = found;
                            first[0] = value;
                        }
                    });
                }
                for (int i = line.length() - 1; i > lastIndex[0]; --i) {
                    int finalI = i;
                    writtenLookups.forEach((key, value) -> {
                        var found = line.indexOf(key, finalI, line.length());
                        if (found != -1) {
                            lastIndex[0] = found;
                            last[0] = value;
                        }
                    });
                }
            }

            return Objects.requireNonNull(first[0])*10 + Objects.requireNonNull(last[0]);
        }).sum());
    }

    @ShellMethod(key = "d1s1")
    public String d1star1(
    ) throws IOException {
        try (var input = getClass().getResourceAsStream("star1.txt")) {
            return run(new String(Objects.requireNonNull(input).readAllBytes(), StandardCharsets.UTF_8));
        }
    }

    @ShellMethod(key = "d1s2")
    public String d1star2(
    ) throws IOException {
        this.checkWritten = true;
        return d1star1();
    }
}
