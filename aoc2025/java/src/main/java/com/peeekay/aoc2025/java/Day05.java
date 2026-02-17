package com.peeekay.aoc2025.java;

import com.peeekay.aocCommon.AOCPuzzle;

import java.util.List;

public class Day05 extends AOCPuzzle {
    LongRange[] freshRanges;
    List<Long> ingredients;

    public Day05(boolean isTest) {
        super(2025, 5, isTest);

        String[] inp = getResourceAsString().split(System.lineSeparator()+System.lineSeparator());
        freshRanges = inp[0].lines().map(LongRange::of).toArray(LongRange[]::new);
        ingredients = inp[1].lines().mapToLong(Long::parseLong).boxed().toList();
    }

    record LongRange(long min, long max) {
        static LongRange of(String line) {
            var parts = line.split("-");
            return new LongRange(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
        }
    }

    @Override
    public Object part1() {
        return ingredients.stream()
                .filter(i -> {
                    for (LongRange range : freshRanges) {
                        if (i >= range.min && i <= range.max) {
                            return true;
                        }
                    }
                    return false;
                    })
                .count();
    }

    @Override
    public Object part2() {
        return 0;
    }
}
