package com.smmathews.aoc.y2023.d3.s1;

import com.smmathews.aoc.y2023.d3.GearRatios;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@ShellComponent
public class NumbersNearSymbols extends GearRatios {
    @ShellMethod
    public String d3s1(
    ) throws IOException {
        try (var input = GearRatios.class.getResourceAsStream("input.txt")) {
            return d3s1(input);
        }
    }

    String d3s1(InputStream inputStream) throws IOException {
        return run(new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8));
    }

    @Override
    public String run(String input) {
        fill(input);
        return Integer.toString(getNumbersNearSymbolsSum());
    }
}
