package com.smmathews.aoc.y2023.d2.s1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class CubeConundrumPossibleGamesTest {
    @Test
    void d2s1() throws IOException {
        final CubeConundrumPossibleGames cubeConundrum = new CubeConundrumPossibleGames();
        try(var input = getClass().getResourceAsStream("input.txt"); var expectedOutput = getClass().getResourceAsStream("output.txt")) {
            var output = cubeConundrum.d2s1(input);
            Assertions.assertEquals(new String(Objects.requireNonNull(expectedOutput).readAllBytes(), StandardCharsets.UTF_8), output);
        }
    }
}