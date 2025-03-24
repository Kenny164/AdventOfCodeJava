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
    record Guard(Pos pos, Dir dir, int turns) { }

    public Day06(boolean isTest) {
        super(2024, 6, isTest);

        char[][] inp = this.resourceAsList().stream().map(String::toCharArray).toArray(char[][]::new);
        this.grid = Grid.from(inp);
    }

    @Override
    public Object part1() {
        List<Pos> path = getGuardPath();
        return path.stream()
                .distinct()
                .count();
    }

    private List<Pos> getGuardPath() {
        List<Pos> path = new ArrayList<>();
        Guard guard = new Guard(grid.findFirst('^'), DIRECTIONS.getFirst(), 0);
        for (;;) {
            path.add(guard.pos);
            Pos nextStep = guard.pos.next(guard.dir);
            if (!grid.in(nextStep)) {
                break;
            }
            if (grid.at(nextStep) == '#') {
                guard = new Guard(guard.pos, DIRECTIONS.get((guard.turns + 1) % 4), guard.turns + 1);
            } else {
                guard = new Guard(nextStep, DIRECTIONS.get(guard.turns % 4), guard.turns);
            }
        }
        return path;
    }

    @Override
    public Object part2() {
        return null;
    }
}
