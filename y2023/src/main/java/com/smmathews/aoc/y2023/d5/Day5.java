package com.smmathews.aoc.y2023.d5;

import com.smmathews.aoc.y2023.StarSolutionRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class Day5 implements StarSolutionRunner {
    protected record MappingRange(long destinationNum, long sourceNum, long range){
        public long mapFromSource(long s) {
            return destinationNum + (s - sourceNum);
        }

        public boolean sourceInRange(long s) {
            return (s - sourceNum) >= 0 && (s - sourceNum) <= range;
        }
    }
    protected record Mapping(String destination, String source, List<MappingRange> ranges){}
    protected HashMap<String, Mapping> sourceToMapping = new HashMap<>();
    protected String startingSourceName;

    ArrayList<Long> staringSourceNums = new ArrayList<>();

    Pattern seedsPattern = Pattern.compile("^(?<startingsourcename>\\w+)s:(?<startingsourcenum>.*$)");
    Pattern mapNamePattern = Pattern.compile("^(?<source>\\w+)-to-(?<dest>\\w+) map:$");
    protected void fill(String input) {
        var lines = input.lines().iterator();
        {
            var matchedStartingSource = seedsPattern.matcher(lines.next());
            if(!matchedStartingSource.find()) {
                throw new RuntimeException("bad starting source pattern");
            }
            startingSourceName = matchedStartingSource.group("startingsourcename");
            Scanner scanner = new Scanner(matchedStartingSource.group("startingsourcenum"));
            while (scanner.hasNextLong()) {
                staringSourceNums.add(scanner.nextLong());
            }
        }
        lines.next(); // skip blank
        while(lines.hasNext()) {
            String line = lines.next();
            var nameMatcher = mapNamePattern.matcher(line);
            if(!nameMatcher.find()) {
                throw new RuntimeException("bad map name pattern");
            }
            var sourceName = nameMatcher.group("source");
            ArrayList<MappingRange> ranges = new ArrayList<>();
            while(lines.hasNext() && !(line = lines.next()).isBlank()) {
                Scanner scanner = new Scanner(line);
                ranges.add(new MappingRange(scanner.nextLong(), scanner.nextLong(), scanner.nextLong()));
            }
            sourceToMapping.put(sourceName, new Mapping(nameMatcher.group("dest"), sourceName, ranges));
        }
    }

    protected long findLowestDestination() {
        String curSourceName = startingSourceName;
        ArrayList<Long> curSourceNums = new ArrayList<>(staringSourceNums);
        while(sourceToMapping.containsKey(curSourceName)) {
            var mapping = sourceToMapping.get(curSourceName);
            ArrayList<Long> nextSourceNums = new ArrayList<>(curSourceNums.size());
            curSourceNums.forEach(s -> {
                var mappingRange = mapping.ranges.stream().filter(r -> r.sourceInRange(s)).findAny().stream().findAny();
                if(mappingRange.isPresent()) {
                    nextSourceNums.add(mappingRange.get().mapFromSource(s));
                } else {
                    nextSourceNums.add(s);
                }
            });
            curSourceName = mapping.destination;
            curSourceNums = nextSourceNums;
        }
        return curSourceNums.stream().min(Long::compare).orElseThrow();
    }
}
