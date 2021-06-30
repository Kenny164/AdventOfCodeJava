package com.peeekay.AdventOfCodeJava;

public class Day01 extends AOCPuzzle {
    final static Integer GOAL = 2020;
    Long _part1, _part2;

    public Day01() {
        super("01");
        solve(dayStrings());
    }

    @Override
    public Object part1() {
        return _part1;
    }

    @Override
    public Object part2() {
        return _part2;
    }

    void solve(String[] input) {
        int inputLen = input.length;
        for(int x = 0; x < inputLen; x++){
            for (int y = x + 1; y < inputLen; y++){
                if (_part1 != null && _part2 != null) return;
                Integer numA = Integer.parseInt(input[x]);
                Integer numB = Integer.parseInt(input[y]);
                if (numA + numB == GOAL){
                    _part1 = (long) numA * numB;
                }

                for(int z = y + 1; z <inputLen; z++){
                    if (_part2 != null) break;
                    Integer numC = Integer.parseInt(input[z]);
                    if (numA + numB + numC == GOAL){
                        _part2 = (long) numA * numB * numC;
                    }
                }
            }

        }
    }
}
