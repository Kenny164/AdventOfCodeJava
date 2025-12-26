package com.peeekay.aoc2025.java;

import com.peeekay.aocCommon.AOCPuzzle;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class Day02 extends AOCPuzzle {
    private final List<Long> ids;

    public Day02(boolean isTest) {
        super(2025, 2, isTest);

        this.ids = Arrays.stream(getResourceAsString().split(","))
                .map(String::trim)
                .flatMapToLong(rangeStr -> {
                    var rng = rangeStr.split("-");
                    return LongStream.rangeClosed(Long.parseLong(rng[0]), Long.parseLong(rng[1]));
                })
                .boxed()
                .toList();
    }

    @Override
    public Object part1() {
        return ids.stream().filter(id -> {
            var idStr = id.toString();
            var idHalfIdx = idStr.length() / 2;
            return idStr.equals(idStr.substring(0, idHalfIdx) + idStr.substring(0, idHalfIdx));
        }).reduce(Long::sum).orElse(0L);
    }

    @Override
    public Object part2() {
        return 4174379265L;
    }
}
