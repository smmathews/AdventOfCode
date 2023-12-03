package com.smmathews.aoc.y2023.d2.s2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class CubeConundrumMinDiceTest {
    @Test
    void d2s2() throws IOException {
        final CubeConundrumMinDice cubeConundrum = new CubeConundrumMinDice();
        try(var input = getClass().getResourceAsStream("input.txt"); var expectedOutput = getClass().getResourceAsStream("output.txt")) {
            var output = cubeConundrum.d2s2(input);
            Assertions.assertEquals(new String(Objects.requireNonNull(expectedOutput).readAllBytes(), StandardCharsets.UTF_8), output);
        }
    }
}