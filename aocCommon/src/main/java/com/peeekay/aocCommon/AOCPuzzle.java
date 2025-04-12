package com.peeekay.aocCommon;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;


public abstract class AOCPuzzle {
    private final int dayNum;
    private final int year;
    private int part = 1;
    private long timerStart;
    private final boolean isTest;

    protected static final StringBuilder SB_INSTANCE = new StringBuilder();

    public AOCPuzzle(int year, int day, boolean isTest) {
        this.year = year;
        this.dayNum = day;
        this.isTest = isTest;
        timerStart = System.nanoTime();
    }

    public abstract Object part1();

    public abstract Object part2();

    public void printParts() {
        System.out.println("Part 1: " + part1());
        System.out.println("Part 2: " + part2());
    }

    protected String getResourceAsString() {
        String path = (isTest) ? "/input/%04d/day%02d_test.txt" : "/input/%04d/day%02d.txt";
        path = String.format(path, year, dayNum);

        try (InputStream inputStream = getClass().getResourceAsStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8))) {

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
            return stringBuilder.toString();

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    protected List<String> resourceAsSplitText (String splitOn) {
        return List.of(getResourceAsString().split(splitOn));
    }

    protected List<String> resourceAsList() {
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

    public void pullInputFromAOC() {
        String adventSession = System.getenv("ADVENT_SESSION");
        File path = new File(Paths.get(System.getProperty("users.dir"))
                .getParent()
                .resolve(String.format("aocInputs/aoc2024/day%02d.txt", dayNum))
                .toUri());

        if (!path.exists()){
            path.getParentFile().mkdirs();

            try {
                URL url = new URI(String.format("https://adventofcode.com/2024/day/%d/input", dayNum)).toURL();
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Cookie", String.format("session=%s", adventSession));
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                BufferedWriter out = new BufferedWriter(new FileWriter(path));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    out.write(inputLine);
                }

                in.close();
                out.close();
                connection.disconnect();
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
