package com.smmathews.aoc.y2023.d1;

import com.smmathews.aoc.y2023.StarSolutionRunner;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class Trebuchet implements StarSolutionRunner {
    private final boolean checkWritten;

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
}
