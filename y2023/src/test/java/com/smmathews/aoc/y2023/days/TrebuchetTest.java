package com.smmathews.aoc.y2023.days;

import com.smmathews.aoc.y2023.d1.Trebuchet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class TrebuchetTest {
    private final Trebuchet trebuchet = new Trebuchet();

    @Test
    void day1_star1_small() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try(var input = getClass().getResourceAsStream("day1_star1_small_input.txt"); var expectedOutput = getClass().getResourceAsStream("day1_star1_small_output.txt")) {
            trebuchet.run(Objects.requireNonNull(input), outputStream);
            Assertions.assertEquals(new String(Objects.requireNonNull(expectedOutput).readAllBytes(), StandardCharsets.UTF_8), outputStream.toString());
        }
    }
}
