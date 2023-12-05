package com.smmathews.aoc.y2023.d5.s1;

import com.smmathews.aoc.y2023.d5.Fertilizer;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@ShellComponent
public class LowestFromIndividual extends Fertilizer {
    @ShellMethod
    public String d5s1(
    ) throws IOException {
        try (var input = Fertilizer.class.getResourceAsStream("input.txt")) {
            return d5s1(input);
        }
    }

    String d5s1(InputStream inputStream) throws IOException {
        return run(new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8));
    }

    @Override
    public String run(String input) {
        fill(input);
        return Long.toString(findLowestDestinationWithIndividualSeeds());
    }
}
