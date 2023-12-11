package com.peeekay.aoc2023.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        List<Integer> result = new ArrayList<>();
        for (var game : games.entrySet()) {
            var sets = game.getValue();
            boolean isPossible = true;
            for (var set : sets) {
                if (set[0] > 12 || set[1] > 13 || set[2] > 14) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) result.add(game.getKey());
        }

        _part1 = result.stream().reduce(Integer::sum).orElse(0);

//        _part1 = games.entrySet().stream()
//                .flatMap(game -> {
//                    var sets = game.getValue();
//                    return IntStream.range(0, sets.get(0).length)
//                            .mapToObj(i-> sets.stream().mapToInt(row -> row[i]))
//                            .mapToInt(col -> col.max().orElse(0))
//                            .mapToObj(v -> Map.entry(game.getKey(), v));
//                });
        _part2 = null;
    }
}
