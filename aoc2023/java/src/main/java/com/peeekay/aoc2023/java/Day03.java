package com.peeekay.aoc2023.java;

import java.util.*;

public class Day03 extends AOCPuzzle {
    Object _part1, _part2;

    public Day03(boolean isTest) {
        super(3, isTest);
    }

    @Override
    public Object part1() {
        return _part1;
    }

    @Override
    public Object part2() {
        return _part2;
    }

    record Point2D(int x, int y) {
        boolean isNeighbourOf(Point2D p) {
            return (Math.abs(x - p.x) <= 1 && Math.abs(y - p.y) <= 1);
        }
    }

    record Cell(List<Point2D> points, int cellValue) {
        boolean hasNeighboursOf(Point2D p) {
            return points.stream().anyMatch(p::isNeighbourOf);
        }

        boolean hasNeighboursOf(Set<Point2D> setPoints) {
            for (var p : setPoints) {
                if (this.hasNeighboursOf(p)) {
                    return true;
                }
            }
            return false;
        }
    }

    void solve() {
        solve(this.resourceAsList());
    }

    void solve(List<String> inp) {
        Map<Point2D, Cell> numbers = new HashMap<>();
        Map<Point2D, Character> symbols = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        List<Point2D> point2ds = new ArrayList<>();
        for (int r = 0; r < inp.size(); ++r) {
            if (!sb.isEmpty() && !point2ds.isEmpty()) {
                int fullCellValue = Integer.parseInt(sb.toString());
                Cell cell = new Cell(new ArrayList<>(point2ds), fullCellValue);
                for (var p : point2ds) {
                    numbers.put(p, cell);
                }
            }
            String row = inp.get(r);
            sb.setLength(0);
            point2ds.clear();
            for (int c = 0; c < row.length(); ++c) {
                char cellVal = row.charAt(c);
                if (Character.isDigit(cellVal)) {
                    sb.append(cellVal);
                    point2ds.add(new Point2D(c, r));
                } else {
                    if (!sb.isEmpty()) {
                        int fullCellValue = Integer.parseInt(sb.toString());
                        Cell cell = new Cell(new ArrayList<>(point2ds), fullCellValue);
                        for (var p : point2ds) {
                            numbers.put(p, cell);
                        }
                        sb.setLength(0);
                        point2ds.clear();
                    }
                    if (cellVal == '.') {
                        continue;
                    }
                    symbols.put(new Point2D(c, r), cellVal);
                }
            }
        }


        _part1 = numbers.values().stream()
                .filter(c -> c.hasNeighboursOf(symbols.keySet()))
                .distinct()
                .mapToInt(Cell::cellValue)
                .sum();

        var filteredSymbols = symbols.entrySet().stream()
                .filter(symEntry -> symEntry.getValue() == '*')
                .map(Map.Entry::getKey)
                .toList();

        Map<Point2D, Set<Integer>> groupedCells = new HashMap<>();
        for (var sym : filteredSymbols) {
            Set<Integer> neighbours = new HashSet<>();
            for (var p : numbers.values()) {
                if (p.hasNeighboursOf(sym)){
                    neighbours.add(p.cellValue());
                }
            }
            groupedCells.put(sym, neighbours);
        }

        _part2 = groupedCells.values().stream()
                .filter(set -> set.size() == 2)
                .mapToLong(set -> set.stream().reduce(1, Math::multiplyExact))
                .sum();

    }
}
