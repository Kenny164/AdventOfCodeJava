package com.peeekay.aoc2023.java;

import java.util.ArrayList;
import java.util.List;

public class Day01 extends AOCPuzzle {
    Object _part1, _part2;

    public Day01(boolean isTest) {
        super(1, isTest);
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
        List<String> inp = resourceAsList();
        List<String> extractedNums = new ArrayList<>();
        for (String line : inp) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length(); ++i) {
                char c = line.charAt(i);
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }
            extractedNums.add(sb.toString());
        }
        _part1 = extractedNums
                .stream()
                .mapToInt(line -> Integer.parseInt(String.valueOf(line.charAt(0)) + line.charAt(line.length() - 1)))
                .sum();
    }
}
