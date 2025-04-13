package com.peeekay.aoc2024.java;

import com.peeekay.aocCommon.AOCPuzzle;

import java.util.*;

public class Day08 extends AOCPuzzle {
    Map<Character, List<Pos>> antennas = new HashMap<>();
    Set<Pos> antiNodes = new HashSet<>();
    Set<Pos> antiNodesPart2 = new HashSet<>();
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

    private void populateAntiNodesPart2() {
        for (char c : antennas.keySet()) {
            List<Pos> antList = antennas.get(c);
            for (Pos a : antList) {
                for (Pos b : antList) {
                    if (a.equals(b)) {continue;}
                    Pos diff = new Pos(-(a.x - b.x), -(a.y - b.y));
                    Pos line = new Pos(b.x, b.y);
                    while (grid.in(line)) {
                        antiNodesPart2.add(line);
                        line = new Pos(line.x + diff.x, line.y + diff.y);
                    }
                }
            }
        }
    }

    @Override
    public Object part1() {
        populateAntiNodes();
        return antiNodes.size();
    }

    @Override
    public Object part2() {
        populateAntiNodesPart2();
        return antiNodesPart2.size();
    }
}
