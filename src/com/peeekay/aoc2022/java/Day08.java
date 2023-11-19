package com.peeekay.aoc2022.java;

import com.peeekay.aoc2022.kotlin.AOCPuzzle;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day08 extends AOCPuzzle {
    private final List<String> inp = resourceAsList();
    private final List<List<Integer>> grid = parseInp(inp);
    private final int rowCount = grid.size();
    private final int colCount = grid.get(0).size();
    private final int[][] DIRECTIONS = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    private List<List<Integer>> parseInp(List<String> inp) {
        return inp.stream()
                .map(s -> s.chars()
                        .mapToObj(Character::getNumericValue)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private boolean isVisible(int r, int c) {
        int height = grid.get(r).get(c);
        for (int[] dir : DIRECTIONS) {
            int[] cur = {r, c};
            while (true) {
                cur = new int[]{cur[0] + dir[0], cur[1] + dir[1]};
                if (cur[0] >= 0 && cur[0] < colCount && cur[1] >= 0 && cur[1] < rowCount) {
                    int curHeight = grid.get(cur[0]).get(cur[1]);
                    if (curHeight >= height) break;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private List<Integer> scenicScore(int r, int c) {
        int height = grid.get(r).get(c);
        List<Integer> results = new ArrayList<>();
        for (int[] dir : DIRECTIONS) {
            int[] cur = {r, c};
            int dist = 0;
            while (true) {
                cur = new int[]{cur[0] + dir[0], cur[1] + dir[1]};
                if (cur[0] >= 0 && cur[0] < colCount && cur[1] >= 0 && cur[1] < rowCount) {
                    dist++;
                    int curHeight = grid.get(cur[0]).get(cur[1]);
                    if (curHeight >= height) {
                        results.add(dist);
                        break;
                    }
                } else {
                    results.add(dist);
                    break;
                }
            }
        }
        return results;
    }

    public Day08(boolean isTest) {
        super(8, isTest);
    }

    @Nullable
    @Override
    public Object partOne() {
        return IntStream
                .range(0, rowCount * colCount)
                .mapToObj(x -> new int[]{x / rowCount, x % rowCount})
                .filter(x -> isVisible(x[0], x[1]))
                .count();
    }

    @Nullable
    @Override
    public Object partTwo() {
        return IntStream
                .range(0, rowCount * colCount)
                .mapToObj(x -> scenicScore(x / rowCount, x % rowCount))
                .mapToInt(x -> x.stream().reduce((num1, num2) -> num1 * num2).orElse(0))
                .max().orElse(0);
    }
}
