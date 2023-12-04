package com.smmathews.aoc.y2023.d4.s2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class ScratchcardsTotalCardsTest {

    @Test
    void d4s2() throws IOException {
        ScratchcardsTotalCards scratchcardsTotalCards = new ScratchcardsTotalCards();
        try(var input = getClass().getResourceAsStream("input.txt"); var expectedOutput = getClass().getResourceAsStream("output.txt")) {
            Assertions.assertEquals(new String(Objects.requireNonNull(expectedOutput).readAllBytes(), StandardCharsets.UTF_8), scratchcardsTotalCards.d4s2(input));
        }
    }
}