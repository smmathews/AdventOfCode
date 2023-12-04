package com.smmathews.aoc.y2023.d4.s1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class ScratchcardsWorthTest {
    @Test
    public void d4s1() throws IOException {
        ScratchcardsWorth scratchcardsWorth = new ScratchcardsWorth();
        try(var input = getClass().getResourceAsStream("input.txt"); var expectedOutput = getClass().getResourceAsStream("output.txt")) {
            Assertions.assertEquals(new String(Objects.requireNonNull(expectedOutput).readAllBytes(), StandardCharsets.UTF_8), scratchcardsWorth.d4s1(input));
        }
    }
}