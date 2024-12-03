package com.smmathews.aoc.y2023.d3;

import com.smmathews.aoc.y2023.StarSolutionRunner;
import lombok.Getter;
import lombok.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class GearRatios implements StarSolutionRunner {
    private enum PartMatchers {
        empty("^\\.{1}") {
            @Override
            public Part constructPart(String stringValue) {
                return Empty.INSTANCE;
            }
        },
        symbol("^[^\\d^\\.]{1}") {
            @Override
            public Part constructPart(String stringValue) {
                return new Symbol(stringValue);
            }
        },
        number("^(\\d+)") {
            @Override
            public Part constructPart(String stringValue) {
                return new Number(stringValue);
            }
        };

        private final Pattern matcher;

        PartMatchers(String matcherString) {
            this.matcher = Pattern.compile(matcherString);
        }

        public abstract Part constructPart(String stringValue);
    }

    private interface Part {

    }

    @Getter
    private static class Number implements Part {
        private final int number;
        private final int length;

        public Number(String stringValue) {
            this.number = Integer.parseInt(stringValue);
            this.length = stringValue.length();
        }
    }

    @Getter
    private static class Symbol implements Part {
        private final char symbol;

        public Symbol(String stringValue) {
            this.symbol = stringValue.charAt(0);
        }
    }

    private static class Empty implements Part {
        public static final Empty INSTANCE = new Empty();
    }

    @Value
    private static class PartMatchersAndMatcher {
        PartMatchers partMatchers;
        Matcher matcher;

        public Part constructPart() {
            return partMatchers.constructPart(matcher.group());
        }

        public int consumedLength() {
            return matcher.group().length();
        }
    }

    ArrayList<Part> parts = new ArrayList<>();
    int width = 0;
    int height = 0;

    protected void fill(String input) {
        input.lines().forEach(line -> {
            int consumed = 0;
            height++;
            while (consumed < line.length()) {
                var remaining = line.substring(consumed);
                var partAndMatcher = Arrays.stream(PartMatchers.values()).map(p -> new PartMatchersAndMatcher(p, p.matcher.matcher(remaining))).filter(m -> m.getMatcher().find()).findAny().orElseThrow();
                var partLength = partAndMatcher.consumedLength();
                var part = partAndMatcher.constructPart();
                for (int i = 0; i < partLength; ++i) {
                    parts.add(part);
                    consumed++;
                }
            }
            if (width == 0) {
                width = parts.size();
            }
        });
    }

    Part getPart(int x, int y) {
        try {
            return parts.get(x + (y * width));
        } catch (IndexOutOfBoundsException ignored) {
            return Empty.INSTANCE;
        }
    }

    protected int getNumbersNearSymbolsSum() {
        int sum = 0;
        HashSet<Number> countedNumbers = new HashSet<>();
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                var part = getPart(x, y);
                if (part instanceof Number numberPart && countedNumbers.add(numberPart)) {
                    boolean nearSymbol = false;
                    for (int xDiff = -1; !nearSymbol && xDiff < numberPart.getLength() + 1; xDiff++) {
                        for (int yDiff = -1; !nearSymbol && yDiff < 2; yDiff++) {
                            if (yDiff != 0 || xDiff == -1 || xDiff == numberPart.getLength()) {
                                var nearPart = getPart(x + xDiff, y + yDiff);
                                nearSymbol = (nearPart instanceof Symbol);
                            }
                        }
                    }
                    if (nearSymbol) {
                        sum += numberPart.getNumber();
                    }
                }
            }
        }
        return sum;
    }

    protected long getGearRatiosSum() {
        long sum = 0;
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                var part = getPart(x, y);
                if (part instanceof Symbol symbolPart && symbolPart.getSymbol() == '*') {
                    HashSet<Number> nearNumberParts = new HashSet<>();
                    for (int xDiff = -1; nearNumberParts.size() <= 2 && xDiff < 2; xDiff++) {
                        for (int yDiff = -1; nearNumberParts.size() <= 2 && yDiff < 2; yDiff++) {
                            var nearPart = getPart(x + xDiff, y + yDiff);
                            if (nearPart instanceof Number numberPart) {
                                nearNumberParts.add(numberPart);
                            }
                        }
                    }
                    if(nearNumberParts.size() == 2) {
                        sum += nearNumberParts.stream().mapToLong(Number::getNumber).reduce((left, right) -> left*right).orElseThrow();
                    }
                }
            }
        }
        return sum;
    }
}
