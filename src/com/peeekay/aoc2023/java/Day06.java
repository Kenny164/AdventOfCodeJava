package com.peeekay.aoc2023.java;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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

    record Race(int time, int distance) {
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
                .mapToInt(race -> {
                    var first = IntStream.range(1, race.time)
                            .filter(hold -> (race.time - hold) * hold > race.distance)
                            .findFirst().orElse(1) - 1;
                    var last = IntStream.range(0, race.time)
                            .map(i -> race.time - i) // reverse
                            .filter(hold -> (race.time - hold) * hold > race.distance)
                            .findFirst().orElse(0);
                    //System.out.printf("Race: %s%n - first: %s, last: %s, total: %s%n", race, first, last, last-first);
                    return last - first;
                })
                .reduce(1, (acc, race) -> acc * race);
    }
}
