package com.peeekay.aoc2020.AdventOfCodeJava;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.io.FileUtils.readFileToString;


public abstract class AOCPuzzle
{
    private final String day;
    private int part = 1;
    private long timerStart;

    public AOCPuzzle(String day)
    {
        this.day = day;
        timerStart = System.nanoTime();
        //solve(inputLines);
        //printParts();
    }

    public abstract Object part1() ;
    public abstract Object part2() ;

    public void printParts()  {
        System.out.println("Part 1: "+part1());
        System.out.println("Part 2: "+part2());
    }

    protected String getResourceAsString(String resource) {
        try {
            return readFileToString(new File(AOCPuzzle.class.getClassLoader().getResource(resource).getFile()), "UTF-8");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    protected String day() {
        return getResourceAsString("day"+day+".txt");
    }

    protected String[] dayStrings() {
        return dayStrings(System.lineSeparator());
    }

    protected String[] dayStrings(String delimiter) {
        return Arrays.stream(day().split(delimiter)).toArray(String[]::new);
    }

    //abstract void solve(List<String> input);

    public void lap(long answer)
    {
        lap(String.valueOf(answer));
    }

    public void lap(String answer)
    {
        long timeSpent = (System.nanoTime() - timerStart) / 1000;
        if(timeSpent < 1000)
            System.out.println("Part " + part + ": " + answer + ", Duration: " + timeSpent + "µs");
        else if(timeSpent < 1000000)
            System.out.println("Part " + part + ": " + answer + ", Duration: " + (timeSpent / 1000.0) + "ms");
        else
            System.out.println("Part " + part + ": " + answer + ", Duration: " + (timeSpent / 1000000.0) + "s");
        timerStart = System.nanoTime();
        part++;
    }

    public List<Integer> convertToInts(List<String> input)
    {
        List<Integer> ints = new ArrayList<>();
        for(String s : input)
            ints.add(Integer.parseInt(s));
        return ints;
    }

    public List<Long> convertToLongs(List<String> input)
    {
        List<Long> ints = new ArrayList<>();
        for(String s : input)
            ints.add(Long.parseLong(s));
        return ints;
    }

}
