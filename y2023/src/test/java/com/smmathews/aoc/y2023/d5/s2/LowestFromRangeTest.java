package com.smmathews.aoc.y2023.d5.s2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class LowestFromRangeTest {

    @Test
    void d5s2() throws IOException {
        LowestFromRange lowestFromRange = new LowestFromRange();
        try(var input = getClass().getResourceAsStream("input.txt"); var expectedOutput = getClass().getResourceAsStream("output.txt")) {
            Assertions.assertEquals(new String(Objects.requireNonNull(expectedOutput).readAllBytes(), StandardCharsets.UTF_8), lowestFromRange.d5s2(input));
        }
    }
}