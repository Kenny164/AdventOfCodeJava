package com.peeekay.aoc2024.java;

import com.peeekay.aocCommon.AOCPuzzle;

import java.util.*;
import java.util.stream.Collectors;

public class Day05 extends AOCPuzzle {
    Map<Integer, Set<Integer>> rules;
    List<List<Integer>> pages;

    public Day05(boolean isTest) {
        super(2024, 5, isTest);
        var inp = this.resourceAsSplitText(System.lineSeparator() + System.lineSeparator());
        rules = inp.getFirst().lines()
                .map(line -> line.split("\\|"))
                .collect(Collectors.groupingBy(
                        p -> Integer.parseInt(p[0]),
                        Collectors.mapping(p -> Integer.parseInt(p[1]), Collectors.toSet())));
        pages = inp.getLast().lines()
                .map(line -> Arrays.stream(line.split(",")).map(Integer::parseInt).toList())
                .toList();
    }

    @Override
    public Object part1() {
        var total = 0;
        for (var update : getValidUpdates()) {
            total += update.get(update.size() / 2);
        }
        return total;
    }

    private List<List<Integer>> getValidUpdates() {
        List<List<Integer>> validUpdates = new ArrayList<>();
        for (var update : pages) {
            boolean isValid = true;
            for (var i = update.size() - 1; i >= 0 && isValid ; i-- ) {
                var page = update.get(i);
                var ruleSet = rules.getOrDefault(page, Set.of());
                for (var j = 0; j < i; j++) {
                    if (ruleSet.contains(update.get(j))) {
                        isValid = false;
                        break;
                    }
                }
            }
            if (isValid) {
                validUpdates.add(update);
            }
        }
        return validUpdates;
    }

    @Override
    public Object part2() {
        return pages;
    }
}
