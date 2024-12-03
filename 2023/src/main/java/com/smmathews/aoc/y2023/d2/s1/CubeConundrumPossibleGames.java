package com.smmathews.aoc.y2023.d2.s1;

import com.smmathews.aoc.y2023.StarSolutionRunner;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@ShellComponent
public class CubeConundrumPossibleGames implements StarSolutionRunner {
    @ShellMethod
    public String d2s1(
    ) throws IOException {
        try (var input = getClass().getResourceAsStream("input.txt")) {
            return d2s1(input);
        }
    }

    String d2s1(InputStream inputStream) throws IOException {
        return run(new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8));
    }

    enum CubeColors {
        red {
            @Override
            public int maxCubes() {
                return 12;
            }
        },
        green {
            @Override
            public int maxCubes() {
                return 13;
            }
        },
        blue {
            @Override
            public int maxCubes() {
                return 14;
            }
        };
        public abstract int maxCubes();
    }

    private static final Map<String, Integer> colorsToMax = Arrays.stream(CubeColors.values()).collect(Collectors.toMap(Enum::name, CubeColors::maxCubes));
    private static final Pattern bagContentPattern = Pattern.compile("[:,;] (\\d+) (\\w+)");

    // 0 if the bag is impossible, ID if the bag is possible
    private static int scoreOfBag(String bag) {
        int gameId = Integer.parseInt(bag.substring("Game ".length(), bag.indexOf(":")));

        boolean possible = true;

        var restOfBag = bag.substring(bag.indexOf(":"));

        var matches = bagContentPattern.matcher(restOfBag);
        while(possible && matches.find()) {
            int num = Integer.parseInt(matches.group(1));
            String color = matches.group(2);

            var colorGroup = colorsToMax.get(color);
            if(colorGroup == null || colorGroup < num) {
                possible = false;
            }
        }
        return possible ? gameId : 0;
    }

    @Override
    public String run(String input) {
        return Integer.toString(input.lines().mapToInt(CubeConundrumPossibleGames::scoreOfBag).sum());
    }
}
