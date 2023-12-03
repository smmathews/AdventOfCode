package com.smmathews.aoc.y2023.d1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class TrebuchetTest {

    @Test
    void day1_star1_small() throws IOException {
        final Trebuchet trebuchet = new Trebuchet(false);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try(var input = getClass().getResourceAsStream("star1_small_input.txt"); var expectedOutput = getClass().getResourceAsStream("star1_small_output.txt")) {
            trebuchet.run(Objects.requireNonNull(input), outputStream);
            Assertions.assertEquals(new String(Objects.requireNonNull(expectedOutput).readAllBytes(), StandardCharsets.UTF_8), outputStream.toString());
        }
    }

    @Test
    void day1_star2_small() throws IOException {
        final Trebuchet trebuchet = new Trebuchet(true);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try(var input = getClass().getResourceAsStream("star2_small_input.txt"); var expectedOutput = getClass().getResourceAsStream("star2_small_output.txt")) {
            trebuchet.run(Objects.requireNonNull(input), outputStream);
            Assertions.assertEquals(new String(Objects.requireNonNull(expectedOutput).readAllBytes(), StandardCharsets.UTF_8), outputStream.toString());
        }
    }
}
