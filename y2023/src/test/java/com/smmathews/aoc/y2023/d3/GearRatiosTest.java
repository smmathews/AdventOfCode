package com.smmathews.aoc.y2023.d3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class GearRatiosTest {
    @Test
    public void d3s1() throws IOException {
        GearRatios gearRatios = new GearRatios();
        try(var input = getClass().getResourceAsStream("input.txt"); var expectedOutput = getClass().getResourceAsStream("output.txt")) {
            Assertions.assertEquals(new String(Objects.requireNonNull(expectedOutput).readAllBytes(), StandardCharsets.UTF_8), gearRatios.d3s2(input));
        }
    }
}