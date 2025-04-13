package com.peeekay.aoc2024.java;

import com.peeekay.aocCommon.AOCPuzzle;

import java.util.*;

public class Day08 extends AOCPuzzle {
    Map<Character, List<Pos>> antennas = new HashMap<>();
    Set<Pos> antiNodes = new HashSet<>();
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
        populateAntiNodes();
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

    private void populateAntiNodes() {
        for (char c : antennas.keySet()) {
            List<Pos> antList = antennas.get(c);
            for (Pos a : antList) {
                for (Pos b : antList) {
                    if (a.equals(b)) {continue;}
                    Pos antiNode = new Pos(-(a.x - b.x) + b.x, -(a.y - b.y) + b.y);
                    if (grid.in(antiNode)) {
                        antiNodes.add(antiNode);
                    }
                }
            }
        }
    }

    @Override
    public Object part1() {
        return antiNodes.size();
    }

    @Override
    public Object part2() {
        return 0;
    }
}
