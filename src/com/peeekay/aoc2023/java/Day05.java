package com.peeekay.aoc2023.java;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    record SegMap(long start, long offset, long end) {
        long lookup(long lookupVal) {
            if (inRange(lookupVal)) {
                return lookupVal + offset;
            } else {
                return lookupVal;
            }
        }

        boolean inRange(long val) {
            return (start <= val && val < end);
        }

        static SegMap from(String segmentStr) {
            var parts = Arrays.stream(segmentStr.split(" ")).mapToLong(Long::parseLong).toArray();
            return new SegMap(parts[1], parts[0] - parts[1], parts[1] + parts[2]);
        }
    }

    record SeedRange(long start, long end) { }

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
                    if (mapping.inRange(p1[si])) {
                        p1[si] = mapping.lookup(p1[si]);
                        break;
                    }
                }
            }
        }

        List<SeedRange> p2Seeds = new ArrayList<>(IntStream.range(0, seeds.length / 2)
                .mapToObj(i -> new SeedRange(seeds[i * 2], seeds[i * 2] + seeds[i * 2 + 1]))
                .toList());

        _part1 = Arrays.stream(p1).min().orElse(0L);
        _part2 = transformSeedRanges(p2Seeds, mappingSets).stream().mapToLong(o -> o.start).min().orElse(0L);
        //_part2 = transformSeedRange(new SeedRange(10, 30), Collections.singleton(new SegMap(15, 100, 25))).toList();
    }

    public static List<SeedRange> transformSeedRanges(List<SeedRange> seeds, List<Set<SegMap>> mappingSets) {
        for (Set<SegMap> maps : mappingSets) {
            List<SeedRange> newSeeds = new ArrayList<>();
            for (SeedRange seed : seeds) {
                newSeeds.addAll(transformSeedRange(seed, maps).toList());
            }
            seeds = newSeeds;
        }
        return seeds;
    }

    private static Stream<SeedRange> transformSeedRange(SeedRange seed, Set<SegMap> maps) {
        return maps.stream()
                .filter(map -> isOverlap(seed, map))
                .findFirst()
                .map(map -> {
                    List<SeedRange> newSeeds = new ArrayList<>();
                    System.out.printf("  - overlap found for seed range: %s and map: %s%n", seed, map);
                    System.out.printf("    - overlapStart: %s, overlapEnd: %s%n", overlapStart(seed, map), overlapEnd(seed, map));
                    // add the left non-overlap
                    if (seed.start < overlapStart(seed, map)) {
                        System.out.printf("    - adding new seed range (LEFT): (%s, %s)%n", seed.start, overlapStart(seed, map)-1);
                        newSeeds.add(new SeedRange(seed.start, overlapStart(seed, map)-1));
                    }
                    // add the overlap (with transformation applied)
                    System.out.printf("    - adding new seed range (OVERLAP): (%s, %s)%n", overlapStart(seed, map) + map.offset, overlapEnd(seed, map) + map.offset);
                    newSeeds.add(new SeedRange(overlapStart(seed, map) + map.offset, overlapEnd(seed, map) + map.offset));
                    // add the right non-overlap
                    if (overlapEnd(seed, map) < seed.end) {
                        System.out.printf("    - adding new seed range (RIGHT: (%s, %s)%n", overlapEnd(seed, map)+1, seed.end);
                        newSeeds.add(new SeedRange(overlapEnd(seed, map)+1, seed.end));
                    }
                    return newSeeds.stream();
                })
                .orElseGet(() -> Stream.of(seed)); // Or return the original seed if no overlap
    }

    private static boolean isOverlap(SeedRange seed, SegMap map) {
        return Math.max(seed.start, map.start) < Math.min(seed.end, map.end);
    }

    private static long overlapStart(SeedRange seed, SegMap map) {
        return Math.max(seed.start, map.start);
    }

    private static long overlapEnd(SeedRange seed, SegMap map) {
        return Math.min(seed.end, map.end);
    }

}
