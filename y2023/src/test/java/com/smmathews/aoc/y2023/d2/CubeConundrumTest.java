package com.smmathews.aoc.y2023.d2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class CubeConundrumTest {
    @Test
    void star1_small() throws IOException {
        final CubeConundrum cubeConundrum = new CubeConundrum();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try(var input = getClass().getResourceAsStream("star1_small_input.txt"); var expectedOutput = getClass().getResourceAsStream("star1_small_output.txt")) {
            cubeConundrum.run(Objects.requireNonNull(input), outputStream);
            Assertions.assertEquals(new String(Objects.requireNonNull(expectedOutput).readAllBytes(), StandardCharsets.UTF_8), outputStream.toString());
        }
    }
}