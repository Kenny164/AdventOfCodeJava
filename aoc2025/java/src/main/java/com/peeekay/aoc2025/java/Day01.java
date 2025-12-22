package com.peeekay.aoc2025.java;

import com.peeekay.aocCommon.AOCPuzzle;
import java.util.ArrayList;
import java.util.List;

public class Day01 extends AOCPuzzle {
    private final static int MAX_COMBINATION = 100;
    List<Integer> rotations;

    public Day01(boolean isTest) {
        super(2025, 1, isTest);

        rotations = resourceAsList().stream().map(line -> {
            int value = Integer.parseInt(line.substring(1));
            return line.charAt(0) == 'L' ? -value : value;
        }).toList();
    }

    static List<Integer> getCombinations (int combination, List<Integer> rotations) {
        List<Integer> result = new ArrayList<>();
        for (Integer rotation : rotations) {
            combination = ((rotation + combination) % MAX_COMBINATION + MAX_COMBINATION) % MAX_COMBINATION;
            result.add(combination);
        }
        return result;
    }

    static int getFullCombinations (int combination, List<Integer> rotations) {
        int result = 0;
        for (int rotation : rotations) {
            int zeroCount;
            if (rotation > 0) {
                zeroCount = (rotation + combination) / MAX_COMBINATION;
            } else {
                if (combination == 0) {
                    zeroCount = -rotation / MAX_COMBINATION;
                } else {
                    zeroCount = (-rotation - combination + MAX_COMBINATION) / MAX_COMBINATION;
                }
            }
            result += zeroCount;
            combination = ((rotation + combination) % MAX_COMBINATION + MAX_COMBINATION) % MAX_COMBINATION;
        }
        return result;
    }

    @Override
    public Object part1() {
        return getCombinations(50, rotations).stream()
                .filter(combination -> combination == 0)
                .count();
    }

    @Override
    public Object part2() {
        return getFullCombinations(50, rotations);
    }
}
