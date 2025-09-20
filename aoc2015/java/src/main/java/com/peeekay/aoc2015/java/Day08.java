package com.peeekay.aoc2015.java;

import com.peeekay.aocCommon.AOCPuzzle;
import java.util.List;

public class Day08 extends AOCPuzzle {
    List<String> inp;

    public Day08(boolean isTest) {
        super(2015, 8, isTest);
        inp = this.resourceAsList();
    }

    @Override
    public Object part1() {
        return inp.stream()
                .map(s -> {
                    int encodedChars = 0;
                    for (int i = 1; i < s.length() - 1; i++) {
                        if (s.charAt(i) == '\\') {
                            if (s.charAt(i + 1) == 'x') {
                                encodedChars++;
                                i += 3;
                            } else {
                                encodedChars++;
                                i++;
                            }
                        } else {
                            encodedChars++;
                        }
                    }
                    return s.length() - encodedChars;
                })
                .reduce(0, Integer::sum);
    }

    @Override
    public Object part2() {
        return 0;
    }
}
