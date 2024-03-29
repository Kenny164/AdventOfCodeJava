package com.peeekay.aoc2023.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Day02 extends AOCPuzzle {
    Object _part1, _part2;

    public Day02(boolean isTest) {
        super(2, isTest);
    }

    @Override
    public Object part1() {
        return _part1;
    }

    @Override
    public Object part2() {
        return _part2;
    }

    void solve() {
        solve(this.resourceAsList());
    }

    void solve(List<String> inp) {
        Map<Integer, List<int[]>> games = new HashMap<>();
        for (String line : inp) {
            String[] splitLine = line.split(": ");
            int gameNumber = Integer.parseInt(splitLine[0].split(" ")[1]);
            List<int[]> sets = new ArrayList<>();
            for (String setStr : splitLine[1].split("; ")) {
                int[] rgb = new int[3];
                for (String cubesStr : setStr.split(", ")) {
                    String[] cube = cubesStr.split(" ");
                    switch (cube[1]) {
                        case "red" -> rgb[0] = Integer.parseInt(cube[0]);
                        case "green" -> rgb[1] = Integer.parseInt(cube[0]);
                        case "blue" -> rgb[2] = Integer.parseInt(cube[0]);
                    }
                }
                sets.add(rgb);
            }
            games.put(gameNumber, sets);
        }

        _part1 = games.entrySet().stream()
                .filter(g -> {
                    var rgb = IntStream.range(0, 3)
                            .map(i -> g.getValue().stream()
                                    .mapToInt(j -> j[i])
                                    .max().orElse(0))
                            .toArray();
                    return (rgb[0] <= 12 && rgb[1] <= 13 && rgb[2] <= 14);})
                .mapToInt(Map.Entry::getKey)
                .sum();

        _part2 = games.values().stream()
                .map(game -> IntStream.range(0, 3)
                        .map(i -> game.stream()
                                .mapToInt(rgb -> rgb[i])
                                .max().orElse(0))
                        .toArray())
                .mapToInt(rgb -> rgb[0] * rgb[1] * rgb[2])
                .sum();
    }
}
