package com.smmathews.aoc.y2023.d5.s1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class LowestTest {

    @Test
    void d5s1() throws IOException {
        Lowest lowest = new Lowest();
        try(var input = getClass().getResourceAsStream("input.txt"); var expectedOutput = getClass().getResourceAsStream("output.txt")) {
            Assertions.assertEquals(new String(Objects.requireNonNull(expectedOutput).readAllBytes(), StandardCharsets.UTF_8), lowest.d5s1(input));
        }
    }
}