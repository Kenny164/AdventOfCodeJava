package com.peeekay.aoc2020.AdventOfCodeJava;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day06 extends AOCPuzzle{
    private final List<String> groups;

    public Day06() {
        super("06");
        this.groups = Arrays.asList(day().split("\r\n\r\n"));
    }

    @Override
    public Object part1() {
        return groups.stream()
                .map(x -> x.replace("\r\n",""))
                .mapToLong(x -> x.chars().distinct().count())
                .sum();
    }

    @Override
    public Object part2() {
        int total = 0;
        for(String group : groups) {
            final String[] persons = group.split("\r\n");
            final HashMap<Character, Integer> charCount = new HashMap<>();
            for (char c : group.toCharArray()) charCount.put(c, charCount.containsKey(c) ? charCount.get(c) + 1 : 1);

            total += charCount.values().stream().mapToInt(count -> count).map(count -> count == persons.length ? 1 : 0).sum();
        }
        return total;
    }
}
