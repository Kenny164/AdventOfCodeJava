package com.peeekay.aoc2022.java;

import com.peeekay.aoc2022.kotlin.AOCPuzzle;
import org.jetbrains.annotations.Nullable;

import java.util.*;

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
        return 0;
    }
}
