package com.peeekay.aoc2024.java;

import com.peeekay.aocCommon.AOCPuzzle;

import java.util.ArrayList;
import java.util.List;

public class Day04 extends AOCPuzzle {
    Object _part1, _part2;

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
    private final List<Dir> DIRECTIONS = List.of(new Dir(0, 1), new Dir(0, -1), new Dir(1, 0), new Dir(-1, 0),
      new Dir(1, 1), new Dir(1, -1), new Dir(-1, 1), new Dir(-1, -1));
    private final String word = "XMAS";

    public Day04(boolean isTest) {
        super(2024, 4, isTest);
    }

    @Override
    public Object part1() {
        return _part1;
    }

    @Override
    public Object part2() {
        return _part2;
    }

    @Override
    public void solve() {
        char[][] inp = this.resourceAsList().stream().map(String::toCharArray).toArray(char[][]::new);
        var grid = Grid.from(inp);

        List<Pos[]> matches = new ArrayList<>();
        for (var r = 0; r < grid.height; r++) {
            for (var c = 0; c < grid.width; c++) {
                var cursor = new Pos(c, r);
                for (var dir : DIRECTIONS) {
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

        _part1 = matches.size();



    }
}
