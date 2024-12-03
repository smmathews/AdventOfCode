package com.smmathews.aoc.y2023.d1.s2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class S2TrebuchetTest {
    @Test
    void d1s2() throws IOException {
        try(var input = getClass().getResourceAsStream("input.txt"); var expectedOutput = getClass().getResourceAsStream("output.txt")) {
            Assertions.assertEquals(new String(Objects.requireNonNull(expectedOutput).readAllBytes(), StandardCharsets.UTF_8), S2Trebuchet.d1s2(input));
        }
    }
}