package com.peeekay.aoc2023.java;

import java.util.*;
import java.util.stream.IntStream;

public class Day11 extends AOCPuzzle {
    Object _part1, _part2;

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
        var galRows = new HashSet<Integer>();
        var galCols = new HashSet<Integer>();

        for (int r = 0; r < inp.size(); ++r) {
            for (int c = 0; c < inp.get(r).length(); ++c) {
                if (inp.get(r).charAt(c) == '#') {
                    galRows.add(r);
                    galCols.add(c);
                }
            }
        }

        var galaxies = new ArrayList<galaxyPos>();
        var rBumps = 0;
        for (int r = 0; r < inp.size(); ++r) {
            var cBumps = 0;
            if (!galRows.contains(r)) rBumps++;
            for (int c = 0; c < inp.get(r).length(); ++c) {
                if (!galCols.contains(c)) cBumps++;
                if (inp.get(r).charAt(c) == '#') {
                    galaxies.add(new galaxyPos(r + rBumps, c + cBumps));
                }
            }
        }

        var minDistances = new ArrayList<Long>();
        for (int i = 0; i < galaxies.size(); ++i) {
            for (int j = i + 1; j < galaxies.size(); ++j) {
                minDistances.add(taxiCabDist(galaxies.get(i), galaxies.get(j)));
            }
        }

        _part1 = minDistances.stream().mapToLong(Long::longValue).sum();
        _part2 = 374L;
    }

    record galaxyPos(int r, int c) {}

    long taxiCabDist(galaxyPos a, galaxyPos b){
        return Math.abs(a.r - b.r) + Math.abs(a.c - b.c);
    }
}
