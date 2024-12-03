package com.smmathews.aoc.y2023;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public interface StarSolutionRunner {

    String run(String input);

    default void run(InputStream inputStream, OutputStream outputStream) throws IOException {
        outputStream.write(run(new String(inputStream.readAllBytes(), StandardCharsets.UTF_8)).getBytes());
    }
}
