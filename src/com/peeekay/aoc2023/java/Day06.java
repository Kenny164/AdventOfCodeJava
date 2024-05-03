package com.peeekay.aoc2023.java;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Day06 extends AOCPuzzle {
    Object _part1, _part2;

    public Day06(boolean isTest) {
        super(6, isTest);
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
        var times = Arrays.stream(inp.get(0).split(": +")[1].split("\\s+"))
                .mapToInt(Integer::parseInt)
                .boxed().toList();
        var distances = Arrays.stream(inp.get(1).split(": +")[1].split("\\s+"))
                .mapToInt(Integer::parseInt)
                .boxed().toList();

        var races = IntStream.range(0, Math.min(times.size(), distances.size()))
                .mapToObj(idx -> new Race(times.get(idx), distances.get(idx)))
                .toList();

        _part1 = races.stream()
                .mapToLong(this::applyAsLong)
                .reduce(1, (acc, race) -> acc * race);

        var correctedRace = new Race(concatFieldsToLong(times), concatFieldsToLong(distances));
        _part2 = applyAsLong(correctedRace);
    }

    long concatFieldsToLong(List<Integer> times) {
        return Long.parseLong(times.stream()
                .map(String::valueOf)
                .collect(Collectors.joining()));
    }

    long applyAsLong(Race race) {
        var first = LongStream.range(1, race.time)
                .filter(hold -> (race.time - hold) * hold > race.distance)
                .findFirst().orElse(1L) - 1;
        var last = LongStream.range(0, race.time)
                .map(i -> race.time - i) // reverse
                .filter(hold -> (race.time - hold) * hold > race.distance)
                .findFirst().orElse(0L);
        return last - first;
    }

    record Race(long time, long distance) {
    }


}
