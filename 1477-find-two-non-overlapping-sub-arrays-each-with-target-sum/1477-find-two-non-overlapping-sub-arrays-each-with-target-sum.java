class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = Integer.MAX_VALUE / 2;

        // best[i] = minimum length of a target-sum subarray
        // completely inside arr[0...i]
        int[] best = new int[n];
        int ans = INF;

        int left = 0;
        int sum = 0;
        int minLen = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            // Shrink window if sum becomes greater than target
            while (sum > target && left <= right) {
                sum -= arr[left++];
            }

            // We found a subarray [left...right]
            if (sum == target) {
                int len = right - left + 1;

                // Combine with the shortest previous subarray
                if (left > 0 && best[left - 1] != INF) {
                    ans = Math.min(ans, len + best[left - 1]);
                }

                minLen = Math.min(minLen, len);
            }

            // Best target-sum subarray found so far
            best[right] = minLen;
        }

        return ans == INF ? -1 : ans;
    }
}