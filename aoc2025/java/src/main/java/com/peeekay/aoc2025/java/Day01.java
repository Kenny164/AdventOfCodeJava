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

    int getFullCombinations (int startingCombination, List<Integer> rotations) {
        int currentCombination = startingCombination;
        int result = 0;
        for (int rotation : rotations) {
            int zeroCount;
            if (rotation > 0) {
                zeroCount = (rotation + currentCombination) / 100;
            } else {
                if (currentCombination == 0) {
                    zeroCount = -rotation / 100;
                } else {
                    zeroCount = (-rotation - currentCombination + 100) / 100;
                }
            }
            result += zeroCount;
            //System.out.printf("cur: %s, rotation: %s, zeroCount: %s%n", currentCombination, rotation, zeroCount);
            currentCombination = rotation + currentCombination;
            currentCombination = currentCombination < 0 ? -currentCombination % 100: currentCombination % 100;
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
        return getFullCombinations(50, rotations);
    }
}
