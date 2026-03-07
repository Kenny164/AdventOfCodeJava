package com.peeekay.aoc2025.java;

import com.peeekay.aocCommon.AOCPuzzle;

import java.util.Arrays;
import java.util.List;

public class Day07 extends AOCPuzzle {
    List<String> inp = resourceAsList();
    long splitCount = 0L;
    long[] beams;

    public Day07(boolean isTest) {
        super(2025, 7, isTest);
        
        solve();
    }
    
    private void solve() {
        int start = inp.getFirst().indexOf('S');
        beams = new long[inp.getFirst().length()];
        beams[start] = 1;

        for (int row = 1; row < inp.size(); row++) {
            long[] nextBeams = new long[beams.length];
            
            for (int col = 0; col < inp.get(row).length(); col++) {
                long currentBeamCount = beams[col];
                if (currentBeamCount == 0) continue;

                if (inp.get(row).charAt(col) == '^') {
                    splitCount++;
                    if (col > 0) {
                        nextBeams[col - 1] += currentBeamCount;
                    }
                    if (col < inp.get(row).length() - 1) {
                        nextBeams[col + 1] += currentBeamCount;
                    }
                } else {
                    nextBeams[col] += currentBeamCount;
                }
            }
            beams = nextBeams;
        }  
    }

    @Override
    public Object part1() {
        return splitCount;
    }

    @Override
    public Object part2() {
        return Arrays.stream(beams)
                .sum();
    }
}
