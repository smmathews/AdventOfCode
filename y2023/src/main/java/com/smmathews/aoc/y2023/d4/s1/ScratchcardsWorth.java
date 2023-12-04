package com.smmathews.aoc.y2023.d4.s1;

import com.smmathews.aoc.y2023.d4.Scratchcards;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@ShellComponent
public class ScratchcardsWorth extends Scratchcards {

    @ShellMethod
    public String d4s1(
    ) throws IOException {
        try (var input = Scratchcards.class.getResourceAsStream("input.txt")) {
            return d4s1(input);
        }
    }

    String d4s1(InputStream inputStream) throws IOException {
        return run(new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8));
    }

    @Override
    public String run(String input) {
        return Long.toString(input.lines().mapToLong(line -> constructCard(line).getWorth()).sum());
    }
}
