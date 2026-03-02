package com.peeekay.aoc2025.java;

import com.peeekay.aocCommon.AOCPuzzle;

import java.util.List;

public class Day07 extends AOCPuzzle {
    List<String> inp = resourceAsList();

    public Day07(boolean isTest) {
        super(2025, 7, isTest);
    }

    @Override
    public Object part1() {
        int start = inp.getFirst().indexOf('S');
        boolean[] acc = new boolean[inp.getFirst().length()];
        acc[start] = true;
        long splitCount = 0L;
        
        for (int row = 1; row < inp.size(); row++) {
            for (int col = 0; col < inp.get(row).length(); col++) {                
                if (inp.get(row).charAt(col) == '^' && acc[col]) {
                    splitCount++;
                    //System.out.println("Split: " + splitCount + ", " +  row + ", " + col + " int[]: " + Arrays.toString(acc));
                    acc[col] = false;
                    if (col > 0) {
                        acc[col - 1] = true;
                    }
                    if (col < inp.get(row).length() - 1) {
                        acc[col + 1] = true;
                    }
                }
            }
        }
        return splitCount;
    }

    @Override
    public Object part2() {
        return 0;
    }
}
