package com.smmathews.aoc.y2023.d4;

import com.smmathews.aoc.y2023.StarSolutionRunner;

import java.util.HashSet;
import java.util.regex.Pattern;

public abstract class Scratchcards implements StarSolutionRunner {
    protected record Card(Integer cardId, HashSet<Integer> winners, HashSet<Integer> have) {
        public long getPoints() {
            var numMatching = getNumMatching();
            if(numMatching <= 0) {
                return 0;
            } else {
                return 1L << (numMatching - 1);
            }
        }

        public int getNumMatching() {
            var myWinners = new HashSet<>(have);
            myWinners.removeIf(have -> !winners.contains(have));
            return myWinners.size();
        }
    }

    private static final Pattern cardContentPattern = Pattern.compile("Card *(\\d+):([^\\|]+) *\\| (.*)");
    private static final Pattern numbersPattern = Pattern.compile("\\d+");

    protected Card constructCard(String input) {
        var matcher = cardContentPattern.matcher(input);
        if(!matcher.find()) {
            throw new RuntimeException("bad pattern");
        }
        var gameIdString = matcher.group(1);
        var winnersString = matcher.group(2);
        var haveString = matcher.group(3);
        var winnerStrings = numbersPattern.matcher(winnersString);
        HashSet<Integer> winners = new HashSet<>();
        while(winnerStrings.find()) {
            winners.add(Integer.parseInt(winnerStrings.group()));
        }
        var haveStrings = numbersPattern.matcher(haveString);
        HashSet<Integer> have = new HashSet<>();
        while(haveStrings.find()) {
            have.add(Integer.parseInt(haveStrings.group()));
        }
        return new Card(Integer.parseInt(gameIdString), winners, have);
    }
}
