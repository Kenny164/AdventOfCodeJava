package com.peeekay.aoc2025.java;

import com.peeekay.aocCommon.AOCPuzzle;
import java.util.ArrayList;
import java.util.List;

public class Day01 extends AOCPuzzle {
    List<Integer> rotations;

    public Day01(boolean isTest) {
        super(2025, 1, isTest);

        rotations = resourceAsList().stream().map(line -> {
            int value = Integer.parseInt(line.substring(1));
            return line.charAt(0) == 'L' ? -value : value;
        }).toList();
    }

    List<Integer> getCombinations (int startingCombination, int maxCombination, List<Integer> rotations) {
        int currentCombination = startingCombination;
        List<Integer> result = new ArrayList<>();
        for (Integer rotation : rotations) {
            currentCombination = rotation + currentCombination;
            currentCombination = currentCombination < 0 ? maxCombination + currentCombination : currentCombination;
            result.add(currentCombination % maxCombination);
        }
        return result;
    }

    @Override
    public Object part1() {
        return getCombinations(50, 100, rotations).stream()
                .filter(combination -> combination == 0)
                .count();
    }

    @Override
    public Object part2() {
        return 2;
    }
}
