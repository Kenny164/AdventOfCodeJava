package com.peeekay.aoc2023.java;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day08 extends AOCPuzzle {
    Object _part1, _part2;

    public Day08(boolean isTest) {
        super(8, isTest);
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
        char[] instructions = inp.get(0).toCharArray();
        Map<String, Node> nodes = inp.stream()
                .skip(2)
                .map(Node::from)
                .collect(Collectors.toMap(n -> n.name, n -> n));

        _part1 = stepCount(nodes, instructions, "AAA", "ZZZ");

        _part2 = inp.stream();



    }

    private long stepCount(Map<String, Node> nodes, char[] instructions, String start, String end) {
        int count = 0;
        String current = start;
        while (!current.equals(end)) {
            var instruction = instructions[count++ % instructions.length];
            current = (instruction == 'L') ? nodes.get(current).left : nodes.get(current).right;
        }
        return count;
    }

    record Node(String name, String left, String right) {
        private static final Pattern LINE_PATTERN = Pattern.compile("(\\w+) = \\((\\w+), (\\w+)\\)");

        static Node from(String line) {
            var matches = LINE_PATTERN.matcher(line);
            if (!matches.matches()) {
                throw new RuntimeException(String.format("Unable to parse line to Node: %s", line));
            }
            return new Node(matches.group(1), matches.group(2), matches.group(3));
        }
    }
}
