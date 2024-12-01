package com.peeekay.aoc2023.java;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day12 extends AOCPuzzle {
    Object _part1, _part2;

    public Day12(boolean isTest) {
        super(12, isTest);
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
        var example = inp.get(1).split(" ");
        _part1 = countArrangements(".??..??...?##.", new int[] {1, 1, 3});
        _part2 = inp.stream().mapToLong(line -> {
            var parts = line.split(" ");
            if (parts.length != 2) return 0;
            var condition = parts[0];
            var groups = Arrays.stream(parts[1].split(",")).mapToInt(Integer::parseInt).toArray();
            System.out.println(condition + " " + Arrays.toString(groups));
            return countArrangements(condition, groups);
        }).peek(System.out::println).sum();
    }

    long countArrangements(String conditions, int[] groups) {
        int n = conditions.length();
        int m = groups.length;

        // DP table: [position in string][current group]
        // The group index also implicitly tells us if we're:
        // - between groups (expecting '.')
        // - building a group (counting '#')
        long[][] dp = new long[n + 1][m + 1];

        // Base case: one way to match empty string with no groups
        dp[0][0] = 1;

        for (int pos = 0; pos < n; pos++) {
            char current = conditions.charAt(pos);

            for (int groupIdx = 0; groupIdx <= m; groupIdx++) {
                if (dp[pos][groupIdx] == 0) continue;

                // Case 1: Using current position as operational
                if (current == '.' || current == '?') {
                    // If we're between groups, we can just move to next position
                    dp[pos + 1][groupIdx] += dp[pos][groupIdx];
                }

                // Case 2: Using current position as damaged
                if ((current == '#' || current == '?') && groupIdx < m) {
                    // Check if we can start/continue a group
                    int remainingSpace = n - pos;
                    if (remainingSpace >= groups[groupIdx]) {
                        // Check if this would create a valid group
                        boolean canPlaceGroup = true;
                        for (int i = 0; i < groups[groupIdx]; i++) {
                            if (pos + i >= n ||
                                    (conditions.charAt(pos + i) != '#' &&
                                            conditions.charAt(pos + i) != '?')) {
                                canPlaceGroup = false;
                                break;
                            }
                        }
                        // Check that group can be terminated
                        if (canPlaceGroup &&
                                (pos + groups[groupIdx] == n ||
                                        conditions.charAt(pos + groups[groupIdx]) != '#')) {
                            dp[pos + groups[groupIdx]][groupIdx + 1] += dp[pos][groupIdx];
                        }
                    }
                }
            }
        }

        return dp[n][m];
    }
}
