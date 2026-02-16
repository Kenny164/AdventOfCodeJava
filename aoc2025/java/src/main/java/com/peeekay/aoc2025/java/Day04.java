package com.peeekay.aoc2025.java;

import com.peeekay.aocCommon.AOCPuzzle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day04 extends AOCPuzzle {
    Set<Point2D> rollsOfPaper = parseInp(resourceAsList());

    public Day04(boolean isTest) {
        super(2025, 4, isTest);
    }

    record Point2D(int x, int y) {
        static Point2D[] NEIGHBOURS = {
                new Point2D(-1, -1), new Point2D(0, -1), new Point2D(1, -1),
                new Point2D(-1, 0), new Point2D(1, 0),
                new Point2D(-1, 1), new Point2D(0, 1), new Point2D(1, 1)
        };

        Point2D[] getNeighbours(Set<Point2D> others) {
            return Arrays.stream(NEIGHBOURS)
                    .filter(p -> others.contains(new Point2D(x + p.x, y + p.y)))
                    .toArray(Point2D[]::new);
        }
    }

    static Set<Point2D> parseInp(List<String> inp) {
        Set<Point2D> result = new HashSet<>();
        for (var y = 0; y < inp.size(); ++y) {
            for (var x = 0; x < inp.get(y).length(); ++x) {
                if (inp.get(y).charAt(x) == '@') {
                    result.add(new Point2D(x, y));
                }
            }
        }
        return result;
    }

    @Override
    public Object part1() {
        return getRemovableRolls(rollsOfPaper).size();
    }

    private List<Point2D> getRemovableRolls(Set<Point2D> rolls) {
        return rolls.stream()
                .filter(p -> p.getNeighbours(rolls).length < 4)
                .toList();
    }

    @Override
    public Object part2() {
        long result = 0L;
        var rollsOfPaperCopy = new HashSet<>(rollsOfPaper);
        while (true) {
            var rollsToRemove = getRemovableRolls(rollsOfPaperCopy);
            result += rollsToRemove.size();
            rollsToRemove.forEach(rollsOfPaperCopy::remove);
            if (rollsToRemove.isEmpty()) {
                return result;
            }
        }
    }
}
