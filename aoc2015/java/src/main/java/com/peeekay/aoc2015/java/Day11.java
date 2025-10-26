package com.peeekay.aoc2015.java;

import com.peeekay.aocCommon.AOCPuzzle;
import java.util.HashSet;
import java.util.Set;

public class Day11 extends AOCPuzzle {
    private static final Set<Character> CONFUSING_LETTERS = Set.of('i', 'o', 'l');
    String inp = this.getResourceAsString();

    public Day11(boolean isTest) {
        super(2015, 11, isTest);
    }

    public static boolean hasThreeStraight(String password) {
        for (int i = 0; i < password.length() - 3 ; i++) {
            if (password.charAt(i) == password.charAt(i + 1) - 1 &&
                    password.charAt(i) == password.charAt(i + 2) - 2) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasConfusingLetters(String password) {
        for (int i = 0; i < password.length() ; i++) {
            if (CONFUSING_LETTERS.contains(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static Integer matchingPairs(String password) {
        HashSet<Character> seen = new HashSet<>();
        for (int i = 0; i < password.length() - 1 ; i++) {
            if (password.charAt(i) == password.charAt(i + 1)) {
                seen.add(password.charAt(i));
            }
        }
        return seen.toArray().length;
    }

    public static String incrementString(String password) {
        char[] chars = password.toCharArray();
        int i = chars.length - 1;

        // Fast path: if last char isn't 'z', just increment it
        if (i >= 0 && chars[i] != 'z') {
            chars[i]++;
            return new String(chars);
        }

        // Handle carrying over ('z' -> 'a')
        while (i >= 0 && chars[i] == 'z') {
            chars[i] = 'a';
            i--;
        }

        if (i >= 0) {
            chars[i]++;
            return new String(chars);
        }

        // All chars were 'z', need to expand
        char[] newChars = new char[chars.length + 1];
        newChars[0] = 'a';
        System.arraycopy(chars, 0, newChars, 1, chars.length);
        return new String(newChars);
    }

    @Override
    public Object part1() {
        var result = inp.trim();
        do {
            result = incrementString(result);
        } while (!hasThreeStraight(result) || hasConfusingLetters(result) || matchingPairs(result) < 2);
        return result;
    }

    @Override
    public Object part2() {
        return 0;
    }
}
