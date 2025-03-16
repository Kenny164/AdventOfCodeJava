package com.peeekay.aoc2024.java;

import com.peeekay.aocCommon.AOCPuzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return getMatchingWordPositions(grid, "MAS", DIAG_DIRECTIONS).stream()
                .map(match -> match[1])
                .collect(Collectors.groupingBy(pos -> pos, Collectors.counting()))
                .values().stream()
                .filter(count -> count > 1).count();
    }

    private List<Pos[]> getMatchingWordPositions(Grid grid, String word, List<Dir> directions) {
        char firstChar = word.charAt(0);
        int wordLength = word.length();
        List<Pos[]> matches = new ArrayList<>();
        for (var r = 0; r < grid.height; r++) {
            for (var c = 0; c < grid.width; c++) {
                var cursor = new Pos(c, r);
                if(grid.at(cursor) != firstChar){
                    continue;
                }
                for (var dir : directions) {
                    Pos[] letters = new Pos[wordLength];
                    letters[0] = cursor;
                    for (var i = 1; i < wordLength; i++) {
                        var pos = new Pos(cursor.x + dir.dx * i, cursor.y + dir.dy * i);
                        if (!grid.in(pos) || grid.at(pos) != word.charAt(i)) {
                            break;
                        }
                        letters[i] = pos;
                        if (i == wordLength - 1) {
                            matches.add(letters);
                        }
                    }
                }
            }
        }
        return matches;
    }
}
