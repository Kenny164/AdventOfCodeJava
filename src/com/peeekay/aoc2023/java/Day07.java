package com.peeekay.aoc2023.java;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day07 extends AOCPuzzle {
    Object _part1, _part2;

    public Day07(boolean isTest) {
        super(7, isTest);
    }

    @Override
    public Object part1() {
        return _part1;
    }

    @Override
    public Object part2() {
        return _part2;
    }

    void solve() {
        solve(this.resourceAsList());
    }

    void solve(List<String> inp) {
        List<Hand> hands = inp.stream()
                .map(Hand::from)
                .sorted(Comparator.comparingLong(Hand::hashRank))
                .toList();

        var sortedHands = hands.stream()
                .map(Hand::bid)
                .toList();

        for (Hand hand : hands) {
            System.out.printf("Hand: %s; Encoded Hand: %08X\n", hand, hand.hashRank);
        }

        _part1 = IntStream.range(0, sortedHands.size())
                .mapToLong(idx -> (long) (idx + 1) * sortedHands.get(idx))
                .sum();

        _part2 = hands;
    }

    record Hand(CharSequence hand, int bid, long hashRank) {
        private static long getRankHash(CharSequence hand) {
            var translatedHand = hand.chars().mapToObj(Hand.CARD_STRENGTH::get).toList();
            var counts = translatedHand.stream()
                    .collect(Collectors.groupingBy(v -> v, Collectors.counting()));
            int handType = determineHandType(counts);

            return encodeHand(handType, translatedHand);
        }

        private static int encodeHand(int handType, List<Integer> hand) {
            int encodedHand = handType << 24;
            int index = 0;
            for (Integer card : hand) {
                encodedHand |= (card & 0xF) << (20 - index++ * 4);
            }
            return encodedHand;
        }

        private static int determineHandType(Map<Integer, Long> counts) {
            var pairs = counts.values().stream().filter(c -> c == 2L).count();
            if (counts.containsValue(5L)) {
                return 6; // Five of a kind
            } else if (counts.containsValue(4L)) {
                return 5; // Four of a kind
            } else if (counts.containsValue(3L) && counts.containsValue(2L)) {
                return 4; // Full house
            } else if (counts.containsValue(3L)) {
                return 3; // Three of a kind
            } else if (pairs == 2) {
                return 2; // Two pair
            } else if (pairs == 1) {
                return 1; // Pair
            } else {
                return 0; // High card
            }
        }

        static Hand from(String line) {
            var inputTokens = line.split("\\s+");
            return new Hand(inputTokens[0], Integer.parseInt(inputTokens[1]), getRankHash(inputTokens[0]));
        }

        static final Map<Integer, Integer> CARD_STRENGTH = IntStream.range(0, 13).boxed()
                .collect(Collectors.toMap(i -> (int) "23456789TJQKA".charAt(i), i -> i));
    }
}
