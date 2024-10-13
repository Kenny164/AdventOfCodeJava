package com.peeekay.aoc2023.java;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Day11 extends AOCPuzzle {
    Object _part1, _part2;
    Set<Integer> galRows = new HashSet<>();
    Set<Integer> galCols = new HashSet<>();

    public Day11(boolean isTest) {
        super(11, isTest);
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
        _part1 = getTaxiCabDistances(expandGalaxies(inp, 1)).stream().mapToLong(Long::longValue).sum();
        _part2 = getTaxiCabDistances(expandGalaxies(inp, 999_999)).stream().mapToLong(Long::longValue).sum();
    }

    private @NotNull ArrayList<Long> getTaxiCabDistances(List<galaxyPos> galaxies) {
        var minDistances = new ArrayList<Long>();
        for (int i = 0; i < galaxies.size(); ++i) {
            for (int j = i + 1; j < galaxies.size(); ++j) {
                minDistances.add(taxiCabDist(galaxies.get(i), galaxies.get(j)));
            }
        }
        return minDistances;
    }

    private List<galaxyPos> expandGalaxies(List<String> inp, int expansions) {
        for (int r = 0; r < inp.size(); ++r) {
            for (int c = 0; c < inp.get(r).length(); ++c) {
                if (inp.get(r).charAt(c) == '#') {
                    galRows.add(r);
                    galCols.add(c);
                }
            }
        }

        List<galaxyPos> expandGalaxies = new ArrayList<>();
        var rBumps = 0;
        for (int r = 0; r < inp.size(); ++r) {
            var cBumps = 0;
            if (!galRows.contains(r)) rBumps += expansions;
            for (int c = 0; c < inp.get(r).length(); ++c) {
                if (!galCols.contains(c)) cBumps += expansions;
                if (inp.get(r).charAt(c) == '#') {
                    expandGalaxies.add(new galaxyPos(r + rBumps, c + cBumps));
                }
            }
        }
        return expandGalaxies;
    }

    record galaxyPos(int r, int c) {}

    long taxiCabDist(galaxyPos a, galaxyPos b){
        return Math.abs(a.r - b.r) + Math.abs(a.c - b.c);
    }
}
