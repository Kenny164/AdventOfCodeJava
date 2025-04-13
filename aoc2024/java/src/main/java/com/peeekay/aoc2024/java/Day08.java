package com.peeekay.aoc2024.java;

import com.peeekay.aocCommon.AOCPuzzle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Day08 extends AOCPuzzle {
    Map<Character, List<Pos>> antennas = new HashMap<>();
    Grid grid;

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

    public Day08(boolean isTest) {
        super(2024, 8, isTest);

        char[][] inp = this.resourceAsList().stream().map(String::toCharArray).toArray(char[][]::new);
        this.grid = Grid.from(inp);
        populateAntennas();
    }

    private void populateAntennas() {
        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {
                Pos pos = new Pos(x, y);
                char c = grid.at(pos);
                if (c == '.') {
                    continue;
                }
                antennas.computeIfAbsent(c, k -> new ArrayList<>()).add(pos);
            }
        }
    }

    private Set<Pos> getAntiNodes(boolean isPart2) {
        Set<Pos> antiNodes = new HashSet<>();
        for (char c : antennas.keySet()) {
            List<Pos> antList = antennas.get(c);
            for (Pos a : antList) {
                for (Pos b : antList) {
                    if (a.equals(b)) {
                        continue;
                    }
                    Pos diff = new Pos(-(a.x - b.x), -(a.y - b.y));
                    Pos line = new Pos(b.x, b.y);
                    if (!isPart2) {
                        line = new Pos(line.x + diff.x, line.y + diff.y);
                        if(grid.in(line)) {
                            antiNodes.add(line);
                        }
                    }
                    else {
                        while (grid.in(line)) {
                            antiNodes.add(line);
                            line = new Pos(line.x + diff.x, line.y + diff.y);
                        }
                    }
                }
            }
        }
        return antiNodes;
    }

    @Override
    public Object part1() {
        return getAntiNodes(false).size();
    }

    @Override
    public Object part2() {
        return getAntiNodes(true).size();
    }
}
