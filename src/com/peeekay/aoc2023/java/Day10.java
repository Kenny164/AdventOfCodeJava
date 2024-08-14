package com.peeekay.aoc2023.java;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class Day10 extends AOCPuzzle {
    Object _part1, _part2;

    public Day10(boolean isTest) {
        super(10, isTest);
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
        var g = new Grid(String.join("", inp), inp.get(0).length(), inp.size());
        var pos = g.findFirst('S');

        record State(Pos pos, Dir dir, int steps) {}

        _part1 = Stream.iterate(
                        new State(pos, Arrays.stream(Dir.values())
                                .filter(d -> g.in(pos.next(d)))
                                .filter(d -> PIPE_MAP.getOrDefault(g.at(pos.next(d)), null).apply(d) != null)
                                .findFirst()
                                .orElseThrow(), 0),
                        state -> g.at(state.pos) != 'S' || state.steps == 0,
                        state -> {
                            var newPos = state.pos.next(state.dir);
                            var newSymbol = g.at(newPos);
                            var newDir = PIPE_MAP.getOrDefault(newSymbol, d -> null).apply(state.dir);
                            return new State(newPos, newDir, state.steps + 1);
                        }
                )
                .reduce((a, b) -> b)
                .map(state -> (state.steps / 2) + (state.steps % 2))
                .orElseThrow();
        _part2 = 0L;
    }

    record Pos(int x, int y) {
        Pos next(Dir dir) {
            return new Pos(x + dir.dx, y + dir.dy);
        }
    }

    enum Dir {
        NORTH(0, -1), EAST(1, 0), SOUTH(0, 1), WEST(-1, 0);
        private final int dx, dy;

        Dir(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }

    record Grid(String data, int width, int height) {
        boolean in(Pos pos) {
            return pos.x >= 0 && pos.x < width && pos.y >= 0 && pos.y < height;
        }

        char at(Pos pos) {
            return data.charAt(pos.x + pos.y * width);
        }

        Pos findFirst(char c) {
            int d = data.indexOf(c);
            return new Pos(d % width, d / width);
        }
    }

    private static final Map<Character, UnaryOperator<Dir>> PIPE_MAP = Map.of(
            '|', d -> d == Dir.NORTH || d == Dir.SOUTH ? d : null,
            '-', d -> d == Dir.EAST || d == Dir.WEST ? d : null,
            'L', d -> d == Dir.SOUTH ? Dir.EAST : (d == Dir.WEST ? Dir.NORTH : null),
            'J', d -> d == Dir.SOUTH ? Dir.WEST : (d == Dir.EAST ? Dir.NORTH : null),
            '7', d -> d == Dir.NORTH ? Dir.WEST : (d == Dir.EAST ? Dir.SOUTH : null),
            'F', d -> d == Dir.NORTH ? Dir.EAST : (d == Dir.WEST ? Dir.SOUTH : null)
    );
}
