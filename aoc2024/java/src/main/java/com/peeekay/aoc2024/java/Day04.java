package com.peeekay.aoc2024.java;

import com.peeekay.aocCommon.AOCPuzzle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day04 extends AOCPuzzle {
    Grid grid;

    private final List<Dir> ALL_DIRECTIONS = List.of(new Dir(0, 1), new Dir(0, -1), new Dir(1, 0), new Dir(-1, 0),
            new Dir(1, 1), new Dir(1, -1), new Dir(-1, 1), new Dir(-1, -1));
    private final List<Dir> DIAG_DIRECTIONS = List.of(new Dir(1, 1), new Dir(1, -1), new Dir(-1, 1), new Dir(-1, -1));

    record Pos(int x, int y) { }
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
    }
    record Dir(int dx, int dy) { }

    public Day04(boolean isTest) {
        super(2024, 4, isTest);

        char[][] inp = this.resourceAsList().stream().map(String::toCharArray).toArray(char[][]::new);
        this.grid = Grid.from(inp);
    }

    @Override
    public Object part1() {
        return getMatchingWordPositions(grid, "XMAS", ALL_DIRECTIONS).size();
    }

    @Override
    public Object part2() {
        var matches = getMatchingWordPositions(grid, "MAS", DIAG_DIRECTIONS);
        Map<Pos, Integer> crosses = new HashMap<>();
        for (Pos[] m : matches) {
            Integer count = crosses.getOrDefault(m[1], 0) + 1;
            crosses.put(m[1], count);
        }
        var totalCrosses = 0;
        for (Integer count : crosses.values()) {
            if (count > 1) {
                totalCrosses++;
            }
        }

        return totalCrosses;
    }

    private List<Pos[]> getMatchingWordPositions(Grid grid, String word, List<Dir> directions) {
        List<Pos[]> matches = new ArrayList<>();
        for (var r = 0; r < grid.height; r++) {
            for (var c = 0; c < grid.width; c++) {
                var cursor = new Pos(c, r);
                for (var dir : directions) {
                    Pos[] letters = new Pos[word.length()];
                    for (var i = 0; i < word.length(); i++) {
                        var pos = new Pos(cursor.x + dir.dx * i, cursor.y + dir.dy * i);
                        if (!grid.in(pos) || grid.at(pos) != word.charAt(i)) {
                            break;
                        }
                        letters[i] = pos;
                        if (i == word.length() - 1) {
                            matches.add(letters);
                        }
                    }
                }
            }
        }
        return matches;
    }
}
