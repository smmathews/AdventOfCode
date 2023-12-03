package com.smmathews.aoc.y2023.d2;

import com.smmathews.aoc.y2023.Day;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Pattern;

@ShellComponent
public class CubeConundrumMinDice implements Day {
    @ShellMethod(key = "d2s2")
    public String d1star1(
    ) throws IOException {
        try (var input = getClass().getResourceAsStream("input.txt")) {
            return run(new String(Objects.requireNonNull(input).readAllBytes(), StandardCharsets.UTF_8));
        }
    }

    private static final Pattern bagContentPattern = Pattern.compile("[:,;] (\\d+) (\\w+)");

    // 0 if the bag is impossible, ID if the bag is possible
    private long powerOfBag(String bag) {
        HashMap<String, Long> colorToMax = new HashMap<>();

        var restOfBag = bag.substring(bag.indexOf(":"));

        var matches = bagContentPattern.matcher(restOfBag);
        while(matches.find()) {
            long num = Long.parseLong(matches.group(1));
            String color = matches.group(2);
            colorToMax.compute(color, (s, existingMax) -> existingMax == null ? num : Long.max(existingMax, num));
        }
        return colorToMax.values().stream().mapToLong(l -> l).reduce((left, right) -> left*right).orElseThrow();
    }

    @Override
    public String run(String input) {
        return Long.toString(input.lines().mapToLong(this::powerOfBag).sum());
    }
}
