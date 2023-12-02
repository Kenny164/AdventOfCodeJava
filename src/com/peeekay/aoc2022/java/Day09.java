package com.peeekay.aoc2022.java;

import com.peeekay.aoc2022.kotlin.AOCPuzzle;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.IntStream;

public class Day09 extends AOCPuzzle {
    private final List<String> inp = resourceAsList();

    private record Point(int x, int y) {
        Point move (Point p) {
            return new Point(x + p.x(),y + p.y());
        }

        boolean touches (Point p) {
            return Math.abs(x - p.x()) <= 1 && Math.abs(y - p.y()) <= 1;
        }

        Point follow (Point p) {
            return new Point (
            (int) (Math.signum(p.x - x) + x),
            (int) (Math.signum(p.y - y) + y)
            );
        }

    }

    private List<Point> parseInp(List<String> inp) {
        List<Point> moves = new ArrayList<>();
        for (String line: inp) {
            moves.addAll(parseMoveToPoints(line));
        }
        return moves;

    }

    private List<Point> parseMoveToPoints(String move) {
        String[] m = move.split(" ");
        Point pointDir = switch (m[0]) {
            case "L" -> new Point(-1, 0);
            case "U" -> new Point(0, 1);
            case "R" -> new Point(1, 0);
            case "D" -> new Point(0, -1);
            default -> new Point(0, 0);
        };
        List<Point> moves = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(m[1]); i++) {
            moves.add(new Point(pointDir.x, pointDir.y));
        }
        return moves;
    }

    public Day09(boolean isTest) {
        super(9, isTest);
    }

    @Nullable
    @Override
    public Object partOne() {
        return partOne(inp);
    }

    public int partOne(List<String> input) {
        Point head = new Point(0, 0);
        Point tail = new Point(0, 0);
        Set<Point> tailVisits = new HashSet<>();

        for (Point instruction : parseInp(inp)){
            head = head.move(instruction);
            if (!head.touches(tail)) {
                tail = tail.follow(head);
            }
            tailVisits.add(tail);
        }

        return tailVisits.size();
    }

    @Nullable
    @Override
    public Object partTwo() {
        return partTwo(inp);
    }

    public int partTwo(List<String> input) {
        Point head = new Point(0, 0);
        List<Point> tails = new ArrayList<>(IntStream.range(0, 10).mapToObj(i -> new Point(0, 0)).toList());
        Set<Point> tailVisits = new HashSet<>();
        List<Point> instructions = parseInp(input);
        for (Point instruction : instructions){
            head = head.move(instruction);
            tails.set(0, head);
            for (int j = 1; j < tails.size(); j++) {
                Point prev = tails.get(j - 1);
                Point cur = tails.get(j);
                if (!prev.touches(cur)) {
                    tails.set(j, cur.follow(prev));
                }
            }
            tailVisits.add(tails.get(9));
        }
        return tailVisits.size();
    }
}
