package com.peeekay.aoc2015.java;

import com.peeekay.aocCommon.AOCPuzzle;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day10 extends AOCPuzzle {
    String inp = this.getResourceAsString().trim();

    public Day10(boolean isTest) {
        super(2015, 10, isTest);
    }

    List<String> groupStringByCharCount(String inputString) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (var i = 0; i < inputString.length(); i++) {
            if (i > 0 && inputString.charAt(i) != inputString.charAt(i - 1)) {
                result.add(sb.toString());
                sb.setLength(0);
            }
            sb.append(inputString.charAt(i));
            if ( i == inputString.length() - 1) {
                result.add(sb.toString());
            }
        }
        return result;
    }

    @Override
    public Object part1() {
        String result = inp;
        StringBuilder sb = new StringBuilder();
        for (var i = 0; i < 40; i++) {
            var groupings = groupStringByCharCount(result);
            sb.setLength(0);
            for (String grouping : groupings) {
                sb.append(grouping.length()).append(grouping.charAt(0));
            }
            result = sb.toString();
        }
        return result.length();
    }

    @Override
    public Object part2() {
        String result = inp;
        StringBuilder sb = new StringBuilder();
        for (var i = 0; i < 50; i++) {
            var groupings = groupStringByCharCount(result);
            sb.setLength(0);
            for (String grouping : groupings) {
                sb.append(grouping.length()).append(grouping.charAt(0));
            }
            result = sb.toString();
        }
        return result.length();
    }
}
