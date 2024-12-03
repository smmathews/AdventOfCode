package com.smmathews.aoc.y2023.d1.s1;

import com.smmathews.aoc.y2023.d1.Trebuchet;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@ShellComponent
public class S1Trebuchet extends Trebuchet {
    public S1Trebuchet() {
        super(false);
    }

    @ShellMethod
    public static String d1s1() throws IOException {
        try (var input = Trebuchet.class.getResourceAsStream("input.txt")) {
            return d1s1(input);
        }
    }

    static String d1s1(InputStream input) throws IOException {
        return new S1Trebuchet().run(new String(Objects.requireNonNull(input).readAllBytes(), StandardCharsets.UTF_8));
    }
}
