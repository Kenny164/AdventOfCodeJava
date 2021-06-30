package com.peeekay.AdventOfCodeJava;

import java.util.Arrays;
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
        return null;
    }
}
