package com.smmathews.aoc.y2023.d4.s2;

import com.smmathews.aoc.y2023.d4.Scratchcards;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ShellComponent
public class ScratchcardsTotalCards extends Scratchcards {
    @ShellMethod
    public String d4s2(
    ) throws IOException {
        try (var input = Scratchcards.class.getResourceAsStream("input.txt")) {
            return d4s2(input);
        }
    }

    String d4s2(InputStream inputStream) throws IOException {
        return run(new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8));
    }

    @Override
    public String run(String input) {
        Map<Integer, Integer> cardIdtoNumCards = new HashMap<>();
        Map<Integer, Card> cardIdtoCard = new HashMap<>();
        input.lines().forEach(line -> {
            var card = constructCard(line);
            constructCard(line).getPoints();
            cardIdtoCard.put(card.cardId(), card);
        });
        var toProcess = new ArrayDeque<>(cardIdtoCard.keySet());
        int cardId;
        long numCards = 0L;
        while(!toProcess.isEmpty()) {
            cardId = toProcess.removeLast();
            var points = cardIdtoCard.get(cardId).getNumMatching();
            Integer sum = 1;
            for(int i = 1; i <= points; ++i) {
                var resultingCardId = cardId + i;
                sum += cardIdtoNumCards.get(resultingCardId);
            }
            cardIdtoNumCards.put(cardId, sum);
            numCards += sum;
        }
        return Long.toString(numCards);
    }
}
