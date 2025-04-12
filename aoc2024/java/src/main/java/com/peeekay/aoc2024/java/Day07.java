package com.peeekay.aoc2024.java;

import com.peeekay.aocCommon.AOCPuzzle;

import java.util.Arrays;
import java.util.List;

public class Day07 extends AOCPuzzle {
    List<SingleEquation> inp;

    record SingleEquation(long testVal, List<Long> numbers) {
        static SingleEquation from(String line) {
            var sl = line.split(": ?");
            return new SingleEquation(
                    Long.parseLong(sl[0]),
                    Arrays.stream(sl[1].split(" ")).map(Long::parseLong).toList());
        }
    }

    public Day07(boolean isTest) {
        super(2024, 7, isTest);

        inp = this.resourceAsList().stream().map(SingleEquation::from).toList();
    }

    @Override
    public Object part1() {
        return inp.stream()
                .filter(se -> canCalibrate(se, 0L, se.numbers))
                .map(e -> e.testVal)
                .reduce(Long::sum)
                .orElse(0L);
    }

    boolean canCalibrate(SingleEquation singleEquation, long acc, List<Long> remaining) {
        if (remaining.isEmpty()) {
            return acc == singleEquation.testVal;
        }
        else if (acc > singleEquation.testVal) {
            return false;
        }
        else {
            long number = remaining.getFirst();
            if (canCalibrate(singleEquation, acc + number, remaining.subList(1, remaining.size()))) {
                return true;
            }
            else if (canCalibrate(singleEquation, acc * number, remaining.subList(1, remaining.size()))) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    @Override
    public Object part2() {
        return 0;
    }
}
