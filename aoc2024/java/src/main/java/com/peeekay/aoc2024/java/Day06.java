package com.peeekay.aoc2024.java;

import com.peeekay.aocCommon.AOCPuzzle;

import java.util.*;
import java.util.stream.Collectors;

public class Day06 extends AOCPuzzle {
    Grid grid;

    private final List<Dir> DIRECTIONS = List.of(new Dir(0, -1), new Dir(1, 0), new Dir(0, 1), new Dir(-1, 0));

    record Pos(int x, int y) {
        Pos next(Dir dir) {
            return new Pos(this.x + dir.dx, this.y + dir.dy);
        }
    }
    record Grid(char[][] data, int width, int height) {
        static Grid from(char[][] inp){
            return new Grid(inp, inp[0].length, inp.length);
        }
        char at(Pos p) {
            return data[p.y][p.x];
        }
        boolean in(Pos p) {
            return p.x >= 0 && p.x < width && p.y >= 0 && p.y < height;
        }

        Pos findFirst(char c) {
            for (int row = 0; row < this.height; row++) {
                for (int col = 0; col < this.width; col++) {
                    if (this.data[row][col] == c) {
                        return new Pos(col, row);
                    }
                }
            }
            return null;
        }
    }
    record Dir(int dx, int dy) { }
    record Guard(Pos pos, int dir) { }

    public Day06(boolean isTest) {
        super(2024, 6, isTest);

        char[][] inp = this.resourceAsList().stream().map(String::toCharArray).toArray(char[][]::new);
        this.grid = Grid.from(inp);
    }

    @Override
    public Object part1() {
        return getGuardPath().stream()
                .mapToInt(g -> g.pos.x * grid.width + g.pos.y)
                .distinct()
                .count();
    }

    private List<Guard> getGuardPath() {
        List<Guard> path = new ArrayList<>();
        Guard guard = new Guard(grid.findFirst('^'), 0);
        for (;;) {
            path.add(guard);
            Pos nextStep = guard.pos.next(DIRECTIONS.get(guard.dir));
            if (!grid.in(nextStep)) {
                break;
            }
            if (grid.at(nextStep) == '#') {
                guard = new Guard(guard.pos, (guard.dir + 1) % 4);
            } else {
                guard = new Guard(nextStep, guard.dir);
            }
        }
        return path;
    }

    private boolean willLoop(Guard guard, Pos obstacle) {
        Set<Guard> history = new HashSet<>();
        for (;;) {
            Pos nextStep = guard.pos.next(DIRECTIONS.get(guard.dir));
            if (history.contains(guard)) {
                return true;
            }
            history.add(guard);
            if (!grid.in(nextStep)) {
                return false;
            }
            if (grid.at(nextStep) == '#' || nextStep.equals(obstacle)) {
                guard = new Guard(guard.pos, (guard.dir + 1) % 4);
            } else {
                guard = new Guard(nextStep, guard.dir);
            }
        }
    }

    @Override
    public Object part2() {
        List<Guard> guardPath = getGuardPath();
        Guard guardInit = guardPath.removeFirst();

        return guardPath.stream()
                .map(guard -> guard.pos)
                .distinct()
                .filter(obs -> willLoop(new Guard(guardInit.pos, guardInit.dir), obs))
                .count();
    }
}
