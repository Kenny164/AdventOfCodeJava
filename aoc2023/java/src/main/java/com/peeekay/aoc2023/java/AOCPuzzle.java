package com.peeekay.aoc2023.java;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


public abstract class AOCPuzzle {
    private final int dayNum;
    private int part = 1;
    private long timerStart;
    private final boolean isTest;
    private List<String> inp;

    public AOCPuzzle(int day, boolean isTest) {
        this.dayNum = day;
        this.isTest = isTest;
        timerStart = System.nanoTime();
        //solve();
        //printParts();
    }

    public abstract Object part1();

    public abstract Object part2();

    abstract void solve();

    public void printParts() {
        System.out.println("Part 1: " + part1());
        System.out.println("Part 2: " + part2());
    }

    private String resourceURI() {
        String path = (isTest) ? "inputs/day%02d_test.txt" : "inputs/day%02d.txt";
        path = String.format(path, dayNum);
        return Objects.requireNonNull(AOCPuzzle.class.getClassLoader().getResource(path)).getPath();
    }

    String getResourceAsString() {
        try {
            return FileUtils.readFileToString(new File(resourceURI()), "UTF-8");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    List<String> resourceAsSplitText (String splitOn) {
        return List.of(getResourceAsString().split(splitOn));
    }

    List<String> resourceAsList() {
        return resourceAsSplitText(System.lineSeparator());
    }

    public void lap(long answer) {
        lap(String.valueOf(answer));
    }

    public void lap(String answer) {
        long timeSpent = (System.nanoTime() - timerStart) / 1000;
        if (timeSpent < 1000) System.out.println("Part " + part + ": " + answer + ", Duration: " + timeSpent + "µs");
        else if (timeSpent < 1000000)
            System.out.println("Part " + part + ": " + answer + ", Duration: " + (timeSpent / 1000.0) + "ms");
        else System.out.println("Part " + part + ": " + answer + ", Duration: " + (timeSpent / 1000000.0) + "s");
        timerStart = System.nanoTime();
        part++;
    }

    public void setInp(List<String> puzzleInput) {
        this.inp = puzzleInput;
    }

    public List<String> getInp() {
        return inp;
    }
}
