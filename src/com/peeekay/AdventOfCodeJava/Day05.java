package com.peeekay.AdventOfCodeJava;

import java.util.HashSet;
import java.util.Set;

public class Day05 extends AOCPuzzle {
    private final Set<Integer> seatIds;

    public Day05() {
        super("05");
        this.seatIds = getSeatIds(dayStrings());
    }

    private Set<Integer> getSeatIds(String[] boardingPasses) {
        Set<Integer> l = new HashSet<>();
        for (String s : boardingPasses) {
            int rowLow = 0;
            int rowHigh = 127;
            int columnLow = 0;
            int columnHigh = 7;
            for (char c : s.toCharArray()) {
                if (c == 'F') {
                    rowHigh -= (rowHigh - rowLow + 1) / 2;
                } else if (c == 'B') {
                    rowLow += (rowHigh - rowLow + 1) / 2;
                } else if (c == 'L') {
                    columnHigh -= (columnHigh - columnLow + 1) / 2;
                } else if (c == 'R') {
                    columnLow += (columnHigh - columnLow + 1) / 2;
                }
            }
            l.add(rowHigh * 8 + columnHigh);
        }
        return l;
    }

    @Override
    public Object part1() {
        //return getSeatIds(new String[]{"FBFBBFFRLR"});
        return seatIds.stream()
                .mapToInt(e -> e)
                .max()
                .orElse(0);
    }

    @Override
    public Object part2() {
        return seatIds.stream()
                .mapToInt(e -> e)
                .filter(n -> seatIds.contains(n) && seatIds.contains(n + 2) && !seatIds.contains(n + 1))
                .min()
                .orElse(0) + 1;
    }
}
