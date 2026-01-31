package com.peeekay.aoc2025.java;

import com.peeekay.aocCommon.AOCPuzzle;

import java.util.List;

public class Day03 extends AOCPuzzle {
    List<byte[]> inp;

    public Day03(boolean isTest) {
        super(2025, 3, isTest);
        inp = resourceAsList().stream().map(String::getBytes).toList();
    }

    long getJoltage(byte[] batteries, int count) {
        byte[] stack = new byte[count];
        int top = -1;

        for (int i = 0; i < batteries.length; i++) {
            byte current = batteries[i];

            while (top >= 0 &&
                    stack[top] < current &&
                    top + batteries.length - i >= count) {
                top--;
            }

            if (top < count - 1) {
                stack[++top] = current;
            }
        }

        return Long.parseLong(new String(stack, 0, count));
    }

    @Override
    public Object part1() {
        return inp.stream().mapToLong(batteries -> getJoltage(batteries, 2)).sum();
    }

    @Override
    public Object part2() {
        return 0;
    }
}
