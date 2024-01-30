package com.peeekay.aoc2023.java;

import java.util.*;
import java.util.stream.Collectors;

public class Day04 extends AOCPuzzle {
    Object _part1, _part2;

    public Day04(boolean isTest) {
        super(4, isTest);
    }

    @Override
    public Object part1() {
        return _part1;
    }

    @Override
    public Object part2() {
        return _part2;
    }

    record Card (Integer id, Set<Integer> winningNums, Set<Integer> ourNums) {
        public static Card from (String line) {
            String[] firstSplit = line.split(":\\s+");
            String[] secondSplit = firstSplit[1].split("\\|\\s+");
            Integer id = Integer.parseInt(firstSplit[0].replaceFirst("Card\\s+", ""));
            Set<Integer> winningNs = Arrays.stream(secondSplit[0].split("\\s+")).map(Integer::parseInt).collect(Collectors.toSet());
            Set<Integer> ourNs = Arrays.stream(secondSplit[1].split("\\s+")).map(Integer::parseInt).collect(Collectors.toSet());
            return new Card(id, winningNs, ourNs);
        }

        Long countOfMatches () {
            return winningNums().stream().filter(ourNums()::contains).count();
        }

        Long score () {
            long rawScore = countOfMatches();
            if (rawScore < 1L)
                return 0L;
            return (long) Math.pow(2, rawScore - 1);
        }

    }

    void solve() {
        solve(this.resourceAsList());
    }

    void solve(List<String> inp) {
        List<Card> cards = new ArrayList<>();
        for (String line : inp) {
            cards.add(Card.from(line));
        }

        _part1 = cards.stream().mapToLong(Card::score).sum();
    }


}
