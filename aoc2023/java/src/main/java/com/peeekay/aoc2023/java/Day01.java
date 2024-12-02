package com.peeekay.aoc2023.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day01 extends AOCPuzzle {
    Object _part1, _part2;

    public Day01(boolean isTest) {
        super(1, isTest);
        
        numberMap.put("one", 1);
        numberMap.put("two", 2);
        numberMap.put("three", 3);
        numberMap.put("four", 4);
        numberMap.put("five", 5);
        numberMap.put("six", 6);
        numberMap.put("seven", 7);
        numberMap.put("eight", 8);
        numberMap.put("nine", 9);
    }

    @Override
    public Object part1() {
        return _part1;
    }

    @Override
    public Object part2() {
        return _part2;
    }

    Map<String, Integer> numberMap = new HashMap<>();

    void solve() {
        solve(this.resourceAsList());
    }

    void solve(List<String> inp) {
        List<String> extractedNumsP1 = new ArrayList<>();
        List<String> extractedNumsP2 = new ArrayList<>();
        for (String line : inp) {
            StringBuilder sbPart1 = new StringBuilder();
            StringBuilder sbPart2 = new StringBuilder();
            for (int i = 0; i < line.length(); ++i) {
                char c = line.charAt(i);
                if (Character.isDigit(c)) {
                    sbPart1.append(c);
                    sbPart2.append(c);
                }
                for (String key : numberMap.keySet()) {
                    if (line.startsWith(key, i)) {
                        sbPart2.append(numberMap.get(key));
                    }
                }
            }
            extractedNumsP1.add(sbPart1.toString());
            extractedNumsP2.add(sbPart2.toString());
        }
        _part1 = extractedNumsP1
                .stream()
                .filter(line -> !line.isBlank())
                .mapToInt(line -> Integer.parseInt(String.valueOf(line.charAt(0)) + line.charAt(line.length() - 1)))
                .sum();

        _part2 = extractedNumsP2
                .stream()
                .filter(line -> !line.isBlank())
                .mapToInt(line -> Integer.parseInt(String.valueOf(line.charAt(0)) + line.charAt(line.length() - 1)))
                .sum();
    }
}
