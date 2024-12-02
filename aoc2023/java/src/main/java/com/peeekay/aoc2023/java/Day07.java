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

        _part1 = inp.stream()
                .map(line -> Hand.from(line, false))
                .sorted(Comparator.comparingLong(Hand::hashRank))
                //.peek(h -> System.out.printf("Hand: %s; Encoded Hand: %08X\n", h, h.hashRank))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        hands -> IntStream.range(0, hands.size())
                                .mapToLong(idx -> (long) (idx + 1) * hands.get(idx).bid())
                                .sum()
                ));

        _part2 = inp.stream()
                .map(line -> Hand.from(line, true))
                .sorted(Comparator.comparingLong(Hand::hashRank))
                .peek(h -> System.out.printf("Hand: %s; Encoded Hand: %08X\n", h, h.hashRank))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        hands -> IntStream.range(0, hands.size())
                                .mapToLong(idx -> (long) (idx + 1) * hands.get(idx).bid())
                                .sum()
                ));

    }

    record Hand(CharSequence hand, int bid, long hashRank) {
        private static long getRankHash(CharSequence hand, boolean jokers) {
            var strengths = (jokers) ? CARD_STRENGTH_JOKERS : CARD_STRENGTH;
            var translatedHand = hand.chars().mapToObj(strengths::get).toList();
            var counts = (jokers) ? getCountsForJokers(translatedHand) : getCounts(translatedHand);
            int handType = determineHandType(counts);

            return encodeHand(handType, translatedHand);
        }

        private static Map<Integer, Long> getCounts(List<Integer> hand) {
            return hand.stream()
                    .collect(Collectors.groupingBy(v -> v, Collectors.counting()));
        }

        private static Map<Integer, Long> getCountsForJokers(List<Integer> hand) {
            List<Integer> nonJokers = hand.stream().filter(c -> !c.equals(0)).toList();
            int jokerCount = hand.size() - nonJokers.size();

            if (jokerCount == 5) return Map.of((int) 'J', 5L);

            var counts = getCounts(nonJokers);
            Integer maxKey = counts.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(0);

            return counts.entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> entry.getKey().equals(maxKey) ? entry.getValue() + jokerCount : entry.getValue()
                    ));
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

        static Hand from(String line, boolean jokers) {
            var inputTokens = line.split("\\s+");
            return new Hand(inputTokens[0], Integer.parseInt(inputTokens[1]), getRankHash(inputTokens[0], jokers));
        }

        static final Map<Integer, Integer> CARD_STRENGTH = IntStream.range(0, 13).boxed()
                .collect(Collectors.toMap(i -> (int) "23456789TJQKA".charAt(i), i -> i));

        static final Map<Integer, Integer> CARD_STRENGTH_JOKERS = IntStream.range(0, 13).boxed()
                .collect(Collectors.toMap(i -> (int) "J23456789TQKA".charAt(i), i -> i));
    }
}
