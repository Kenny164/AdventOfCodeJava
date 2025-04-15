package com.peeekay.aoc2024.java;

import com.peeekay.aocCommon.AOCPuzzle;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day09 extends AOCPuzzle {
    List<Integer> fileSystem;

    public Day09(boolean isTest) {
        super(2024, 9, isTest);
        String inp = this.getResourceAsString();
        expandFileSystem(inp);
    }

    void expandFileSystem(String inp) {
        fileSystem = new ArrayList<>();
        for (int i = 0, address = 0; i < inp.length(); i++) {
            int c = Character.getNumericValue(inp.charAt(i));
            if (i % 2 == 0) {
                fileSystem.addAll(getIndicies(address++, c));
            } else {
                fileSystem.addAll(getIndicies(null, c));
            }
        }
    }

    List<Integer> getIndicies(Integer index, int size) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            indices.add(index);
        }
        return indices;
    }

    @Override
    public Object part1() {
        var compressed = compressFS(fileSystem);
        return IntStream.range(0,compressed.size())
                .mapToLong(block -> compressed.get(block) * block)
                .sum();
    }

    List<Integer> compressFS(List<Integer> fileSystem) {
        var fs = new ArrayList<>(fileSystem);
        for (int i = 0; i < fs.size(); i++) {
            if (fs.get(i) != null) {
                continue;
            }
            while (fs.getLast() == null) {
                fs.removeLast();
            }
            if (i > fs.size()) {
                break;
            }
            fs.set(i, fs.removeLast());
        }
        return fs;
    }

    @Override
    public Object part2() {
        return 0;
    }
}
