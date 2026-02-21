package com.peeekay.aoc2025.java;

import com.peeekay.aocCommon.AOCPuzzle;

import java.util.ArrayList;
import java.util.List;

public class Day06 extends AOCPuzzle {
    private final List<String> inp = resourceAsList();

    public Day06(boolean isTest) {
        super(2025, 6, isTest);
    }
    
    private List<Problem> getProblems(List<String> inp) {
        return getProblems(inp, false);
    }

    private List<Problem> getProblems(List<String> inp, boolean isPart2) {
        List<Problem> problems = new ArrayList<>();
        String operationsLine = inp.getLast();
        Character currentOp = null;
        int lastOpIndex = 0;
        for (int i = 0; i < operationsLine.length(); i++) {
            char cur = operationsLine.charAt(i);
            if (cur != ' ') {
                if (currentOp != null) {
                    if (isPart2) {
                        problems.add(new Problem(currentOp, getProbData2(lastOpIndex, i - 1 - lastOpIndex, inp)));
                    } else {
                        problems.add(new Problem(currentOp, getProbData(lastOpIndex, i - 1 - lastOpIndex, inp)));
                    }
                }
                currentOp = cur;
                lastOpIndex = i;
            }
        }
        if (currentOp != null) {
            if (isPart2) {
                problems.add(new Problem(currentOp, getProbData2(lastOpIndex, operationsLine.length() - lastOpIndex, inp)));                
            } else {
                problems.add(new Problem(currentOp, getProbData(lastOpIndex, operationsLine.length() - lastOpIndex, inp)));
            }
        }
        return problems;
    }

    record Problem(char operation, List<Long> data) {
    }

    private List<Long> getProbData(int startIdx, int width, List<String> inp) {
        StringBuilder sb = new StringBuilder();
        List<Long> result = new ArrayList<>();
        for (int i = 0; i < inp.size() - 1; i++) {
            sb.setLength(0);
            for (int j = 0; j < width; j++) {
                char cur = inp.get(i).charAt(startIdx + j);
                if (cur == ' ') {
                    continue;
                }
                sb.append(cur);
            }
            if (!sb.isEmpty()) {
                result.add(Long.parseLong(sb.toString()));
            }
        }
        return result;
    }    
    
    private List<Long> getProbData2(int startIdx, int width, List<String> inp) {
        StringBuilder sb = new StringBuilder();
        List<Long> result = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            sb.setLength(0);
            for (int j = 0; j < inp.size() - 1; j++) {
                char cur = inp.get(j).charAt(startIdx + i);
                if (cur == ' ') {
                    continue;
                }
                sb.append(cur);
            }
            if (!sb.isEmpty()) {
                result.add(Long.parseLong(sb.toString()));
            }
        }
        return result;
    }

    private Long applyOperation(Problem p) {
        if (p.operation == '+') {
            return p.data.stream().reduce(0L, Long::sum);
        } else {
            return p.data.stream().reduce(1L, (a, b) -> a * b);
        }
    }

    @Override
    public Object part1() {
        return getProblems(inp).stream()
                .map(this::applyOperation)
                .reduce(0L, Long::sum);
    }

    @Override
    public Object part2() {
        return getProblems(inp, true).stream()
                .map(this::applyOperation)
                .reduce(0L, Long::sum);
    }
}
