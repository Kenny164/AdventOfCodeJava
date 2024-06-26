package com.peeekay.aoc2023.java;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day09 extends AOCPuzzle {
    Object _part1, _part2;

    public Day09(boolean isTest) {
        super(9, isTest);
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
        var histories = inp.stream()
                .map(line -> Arrays.stream(line.split(" "))
                        .map(Integer::parseInt).toList())
                .toList();

        _part1 = histories.stream()
                .mapToInt(hist -> Stream.iterate(hist,
                                prediction -> !prediction.stream().allMatch(v -> v == 0),
                                prediction -> IntStream.range(1, prediction.size())
                                        .mapToObj(i -> prediction.get(i) - prediction.get(i - 1))
                                        .toList())
                        .mapToInt(prediction -> prediction.get(prediction.size() - 1))
                        .sum())
                .sum();

        _part2 = histories.stream()
                .mapToInt(hist -> Stream.iterate(hist,
                                prediction -> !prediction.stream().allMatch(v -> v == 0),
                                prediction -> IntStream.range(1, prediction.size())
                                        .mapToObj(i -> prediction.get(i - 1) - prediction.get(i))
                                        .toList())
                        .mapToInt(prediction -> prediction.get(0))
                        .sum())
                .sum();
    }
}
