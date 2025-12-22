package com.peeekay.aoc2024.java;

import com.peeekay.aocCommon.AOCPuzzle;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Day10 extends AOCPuzzle {
    Grid grid;

    private final List<Dir> DIRECTIONS = List.of(new Dir(0, 1), new Dir(0, -1), new Dir(1, 0), new Dir(-1, 0));

    record Pos(int x, int y) {
    }

    record Grid(char[][] data, int width, int height) {
        static Grid from(char[][] inp) {
            return new Grid(inp, inp[0].length, inp.length);
        }

        char at(Pos p) {
            return data[p.y][p.x];
        }

        boolean in(Pos p) {
            return p.x >= 0 && p.x < width && p.y >= 0 && p.y < height;
        }
    }

    record Dir(int dx, int dy) {
    }

    public Day10(boolean isTest) {
        super(2024, 10, isTest);
        char[][] inp = this.resourceAsList().stream().map(String::toCharArray).toArray(char[][]::new);
        this.grid = Grid.from(inp);
    }

    @Override
    public Object part1() {
        return getEndings().stream().distinct().count();
    }

    private List<Pos> getEndings() {
        List<Pos> endings = new ArrayList<>();
        for (var rowN = 0; rowN < grid.width; rowN++) {
            for (var colN = 0; colN < grid.height; colN++) {
                var startingPos = new Pos(colN, rowN);
                if (grid.at(startingPos) != '0') {
                    continue;
                }

                ArrayDeque<Pos> queue = new ArrayDeque<>();
                queue.add(startingPos);

                while (!queue.isEmpty()) {
                    Pos pos = queue.pop();
                    if (grid.at(pos) == '9') {
                        endings.add(pos);
                        continue;
                    }
                    for (Dir direction : DIRECTIONS) {
                        Pos nextStep = new Pos(pos.x + direction.dx, pos.y + direction.dy);
                        if (grid.in(nextStep) && Integer.parseInt(String.valueOf(grid.at(pos))) + 1 == Integer.parseInt(String.valueOf(grid.at(nextStep)))) {
                            queue.add(nextStep);
                        }
                    }
                }
            }
        }
        return endings;
    }

    @Override
    public Object part2() {
        return 0;
    }
}
