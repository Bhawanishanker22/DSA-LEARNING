class Solution {
    public int numberOfSets(int n, int k) {
        final long MOD = 1_000_000_007L;

        long[] dp = new long[k + 1];
        long[] open = new long[k + 1];

        // 0 segments using 0 points
        dp[0] = 1;

        for (int i = 1; i < n; i++) {

            for (int j = k; j >= 0; j--) {

                // Start a new segment at this point
                if (j > 0) {
                    open[j] = (open[j] + dp[j - 1]) % MOD;
                }

                // End an existing segment at this point
                dp[j] = (dp[j] + open[j]) % MOD;
            }
        }

        return (int) dp[k];
    }
}