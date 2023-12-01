package com.smmathews.aoc.y2023.days;

import com.smmathews.aoc.y2023.Day;
import org.springframework.shell.standard.ShellComponent;

import java.util.Objects;

@ShellComponent
public class Trebuchet implements Day {

    @Override
    public String run(String input) {
        return String.valueOf(input.lines().mapToInt(line -> {
            Integer first = null;
            Integer last = null;
            for (int i = 0; i < line.length(); i++) {
                try {
                    last = Integer.valueOf(line.substring(i, i + 1));
                    if(first == null) {
                        first = last*10;
                    }
                } catch (NumberFormatException ignored) {
                }
            }
            return Objects.requireNonNull(first) + Objects.requireNonNull(last);
        }).sum());
    }
}
