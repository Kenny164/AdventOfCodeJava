package com.peeekay.aoc2025.java;

import com.peeekay.aocCommon.AOCPuzzle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day05 extends AOCPuzzle {
    List<LongRange> freshRanges;
    List<Long> ingredients;

    public Day05(boolean isTest) {
        super(2025, 5, isTest);

        String[] inp = getResourceAsString().split(System.lineSeparator()+System.lineSeparator());
        freshRanges = combineRanges(inp[0].lines().map(LongRange::of).toList());
        ingredients = inp[1].lines().mapToLong(Long::parseLong).boxed().toList();
    }

    private List<LongRange> combineRanges(List<LongRange> ranges) {
        if (ranges.isEmpty()) {
            return List.of();
        }

        List<LongRange> sorted = new ArrayList<>(ranges);
        sorted.sort(Comparator.comparingLong(LongRange::min));

        List<LongRange> merged = new ArrayList<>();
        LongRange current = sorted.getFirst();

        for (int i = 1; i < sorted.size(); i++) {
            LongRange next = sorted.get(i);
            if (next.min <= current.max + 1) {
                current = new LongRange(current.min, Math.max(current.max, next.max));
            } else {
                merged.add(current);
                current = next;
            }
        }
        merged.add(current);
        return merged;
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
        return freshRanges.stream()
                .mapToLong(r -> r.max - r.min + 1)
                .sum();
    }
}
