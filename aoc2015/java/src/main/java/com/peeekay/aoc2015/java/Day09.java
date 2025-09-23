package com.peeekay.aoc2015.java;

import com.peeekay.aocCommon.AOCPuzzle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day09 extends AOCPuzzle {
    Map<String, Integer> inp = new HashMap<>();
    Set<String> cities = new HashSet<>();

    public Day09(boolean isTest) {
        super(2015, 9, isTest);
        parseInput(this.resourceAsList());
    }

    @Override
    public Object part1() {
        List<List<String>> paths = getPermutations(cities.stream().toList());
        int shortestDistance = Integer.MAX_VALUE;
        for (List<String> path : paths) {
            int distance = 0;
            for (var i = 0; i < path.size() - 1; i++) {
                distance += inp.getOrDefault(String.format("%s_%s", path.get(i), path.get(i + 1)), 0);
            }
            shortestDistance = Math.min(shortestDistance, distance);
        }
        return shortestDistance;
    }

    @Override
    public Object part2() {
        return 0;
    }

    void parseInput(List<String> lines) {
        for (String line : lines) {
            String[] parts = line.split(" ");
            inp.put(String.format("%s_%s", parts[0], parts[2]), Integer.valueOf(parts[4]));
            inp.put(String.format("%s_%s", parts[2], parts[0]), Integer.valueOf(parts[4]));
            cities.add(parts[0]);
            cities.add(parts[2]);
        }
    }

    <T> List<List<T>> getPermutations(List<T> original) {
        if (original == null || original.isEmpty()) {
            return new ArrayList<>();
        }

        List<List<T>> permutations = new ArrayList<>();
        permutations.add(new ArrayList<>());

        for (T element : original) {
            List<List<T>> nextPermutations = new ArrayList<>();
            for (List<T> currentPermutation : permutations) {
                for (int i = 0; i <= currentPermutation.size(); i++) {
                    List<T> newPermutation = new ArrayList<>(currentPermutation);
                    newPermutation.add(i, element);
                    nextPermutations.add(newPermutation);
                }
            }
            permutations = nextPermutations;
        }

        return permutations;
    }
}
