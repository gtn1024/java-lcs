package io.github.gtn1024.lcs;

/**
 * 最长公共子序列
 */
public final class LCS {
    private LCS() {
    }

    /**
     * 最长公共子序列
     *
     * @param a 第一个字符串
     * @param b 第二个字符串
     * @return 最长公共子序列的长度
     */
    public static long lcs(String a, String b) {
        int m = a.length();
        int n = b.length();
        long[][] dp = new long[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i <= m; i++) {
            char c1 = a.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = b.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
