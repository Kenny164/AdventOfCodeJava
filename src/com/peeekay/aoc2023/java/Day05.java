package com.peeekay.aoc2023.java;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day05 extends AOCPuzzle {
    Object _part1, _part2;

    public Day05(boolean isTest) {
        super(5, isTest);
    }

    @Override
    public Object part1() {
        return _part1;
    }

    @Override
    public Object part2() {
        return _part2;
    }

    record SegMap (long dest, long src, long length) {
        long lookup (long lookupVal) {
            var diff = (src() - lookupVal);
            return (isInRange(lookupVal)) ? dest() + diff : lookupVal;
        }

        private boolean isInRange (long val) {
            return ( val < src() && val > src() + length() );
        }

        public static SegMap from (String segmentStr) {
            var parts = Arrays.stream(segmentStr.split(" ")).mapToLong(Long::parseLong).toArray();
            return new SegMap(parts[0], parts[1], parts[2]);
        }
    }

    void solve() {
        solve(this.resourceAsSplitText(System.lineSeparator() + System.lineSeparator()));
    }

    void solve(List<String> inp) {
        long[] seeds = Arrays.stream(inp.get(0).split(": ")[1].split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        List<Set<SegMap>> mappingSets = inp.stream().skip(1).map(segment ->
                Arrays.stream(segment.split(System.lineSeparator())).skip(1)
                    .map(SegMap::from).collect(Collectors.toSet()))
                .toList();

        long[] p1 = seeds.clone();
        for (var r : mappingSets) {
            for (int si = 0; si < p1.length; si++) {
                for (var mapping : r) {
                    if (mapping.src() <= p1[si] && p1[si] < mapping.src() + mapping.length()) {
                        p1[si] = p1[si] - mapping.src() + mapping.dest();
                        break;
                    }
                }
            }
        }



        _part1 = Arrays.stream(p1).min().orElse(0L);
        _part2 = seeds == p1;
    }
}
