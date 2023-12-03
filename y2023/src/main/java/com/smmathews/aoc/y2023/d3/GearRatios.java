package com.smmathews.aoc.y2023.d3;

import com.smmathews.aoc.y2023.StarSolutionRunner;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class GearRatios implements StarSolutionRunner {

    @ShellMethod
    public String d3s2(
    ) throws IOException {
        try (var input = getClass().getResourceAsStream("input.txt")) {
            return d3s2(input);
        }
    }

    String d3s2(InputStream inputStream) throws IOException {
        return run(new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8));
    }

    @Override
    public String run(String input) {
        return null;
    }
}
