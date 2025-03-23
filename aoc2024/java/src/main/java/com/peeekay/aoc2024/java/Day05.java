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
        return pages.stream()
                .filter(this::isValid)
                .map(this::getListMidPoint)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private Integer getListMidPoint(List<Integer> inList) {
        return inList.get(inList.size() / 2);
    }

    private boolean isValid(List<Integer> update) {
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
        return isValid;
    }

    @Override
    public Object part2() {
        return pages.stream()
                .filter(update -> !isValid(update))
                .map(update -> update.stream().sorted(comparedValid()).toList())
                .map(this::getListMidPoint)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private Comparator<Integer> comparedValid() {
        return (a, b) -> {
            if (rules.getOrDefault(a, Set.of()).contains(b)) {
                return -1;
            } else if (rules.getOrDefault(b, Set.of()).contains(a)) {
                return 1;
            }
            return 0;
        };
    }
}
