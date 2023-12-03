package com.smmathews.aoc.y2023.d1.s2;

import com.smmathews.aoc.y2023.d1.Trebuchet;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@ShellComponent
public class S2Trebuchet extends Trebuchet {
    public S2Trebuchet() {
        super(true);
    }

    @ShellMethod
    public static String d1s2() throws IOException {
        try (var input = Trebuchet.class.getResourceAsStream("input.txt")) {
            return d1s2(input);
        }
    }

    static String d1s2(InputStream input) throws IOException {
        return new S2Trebuchet().run(new String(Objects.requireNonNull(input).readAllBytes(), StandardCharsets.UTF_8));
    }
}
