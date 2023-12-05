package com.smmathews.aoc.y2023.d5;

import com.smmathews.aoc.y2023.StarSolutionRunner;
import org.roaringbitmap.IntConsumer;
import org.roaringbitmap.RoaringBitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class Fertilizer implements StarSolutionRunner {
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
                var toAdd = scanner.nextLong();
                staringSourceNums.add(toAdd);
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

    private long findLowestDestinationWithSpecifiedSeeds(String curSourceName, RoaringBitmap curSourceNums) {
        while(sourceToMapping.containsKey(curSourceName)) {
            var mapping = sourceToMapping.get(curSourceName);
            RoaringBitmap nextSourceNums = new RoaringBitmap();
            curSourceNums.forEach((IntConsumer) s -> {
                var unsigned = Integer.toUnsignedLong(s);
                var mappingRange = mapping.ranges.stream().filter(r -> r.sourceInRange(unsigned)).findAny().stream().findAny();
                if(mappingRange.isPresent()) {
                    nextSourceNums.add((int) mappingRange.get().mapFromSource(unsigned));
                } else {
                    nextSourceNums.add(s);
                }
            });
            curSourceName = mapping.destination;
            curSourceNums = nextSourceNums;
        }
        return curSourceNums.stream().mapToLong(Integer::toUnsignedLong).min().orElseThrow();
    }

    protected long findLowestDestinationWithIndividualSeeds() {
        RoaringBitmap curSourceNums = new RoaringBitmap();
        staringSourceNums.forEach(l -> curSourceNums.add(l.intValue()));
        return findLowestDestinationWithSpecifiedSeeds(startingSourceName, curSourceNums);
    }

    protected long findLowestDestinationWithSeedRanges() {
        RoaringBitmap curSourceNums = new RoaringBitmap();
        var ranges = staringSourceNums.stream().iterator();
        while(ranges.hasNext()) {
            var start = ranges.next();
            var range = ranges.next();
            curSourceNums.add(start, start + range);
        }
        return findLowestDestinationWithSpecifiedSeeds(startingSourceName, curSourceNums);
    }
}
