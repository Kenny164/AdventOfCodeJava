package com.peeekay.aoc2024.java;

import com.peeekay.aocCommon.AOCPuzzle;

import java.util.Arrays;
import java.util.List;
import java.util.function.LongBinaryOperator;

public class Day07 extends AOCPuzzle {
    List<SingleEquation> inp;

    private static final LongBinaryOperator ADD = Long::sum;
    private static final LongBinaryOperator MULTIPLY = (a, b) -> a * b;
    private static final LongBinaryOperator CONCAT = Day07::concatOp;

    private static final List<LongBinaryOperator> PART1_OPS = List.of(ADD, MULTIPLY);
    private static final List<LongBinaryOperator> PART2_OPS = List.of(ADD, MULTIPLY, CONCAT);

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

    static long concatOp(long a, long b) {
        SB_INSTANCE.setLength(0);
        SB_INSTANCE.append(a).append(b);
        return Long.parseLong(SB_INSTANCE.toString());
    }

    boolean canCalibrate(SingleEquation singleEquation, long acc, List<Long> remaining, List<LongBinaryOperator> allowedOps) {
        if (remaining.isEmpty()) {
            return acc == singleEquation.testVal;
        }
        if (acc > singleEquation.testVal) {
            return false;
        }
        long number = remaining.getFirst();
        return allowedOps.stream()
                .anyMatch(op ->
                        canCalibrate(singleEquation,
                                op.applyAsLong(acc, number),
                                remaining.subList(1, remaining.size()),
                                allowedOps));
    }

    @Override
    public Object part1() {
        return inp.stream()
                .filter(se -> canCalibrate(se, 0L, se.numbers, PART1_OPS))
                .map(e -> e.testVal)
                .reduce(Long::sum)
                .orElse(0L);
    }

    @Override
    public Object part2() {
        return inp.stream()
                .filter(se -> canCalibrate(se, 0L, se.numbers, PART2_OPS))
                .map(e -> e.testVal)
                .reduce(Long::sum)
                .orElse(0L);
    }
}
