class Solution {

    static class Interval {
        int l, r, weight, index;

        Interval(int l, int r, int weight, int index) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.index = index;
        }
    }

    static class State {
        long weight;
        List<Integer> indices;

        State(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        List<Interval> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Integer> x = intervals.get(i);

            arr.add(new Interval(
                x.get(0),
                x.get(1),
                x.get(2),
                i
            ));
        }

        // Sort by starting point
        arr.sort(Comparator.comparingInt(a -> a.l));

        State[][] dp = new State[n][5];

        State ans = solve(arr, dp, 0, 4);

        return ans.indices.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private State solve(
        List<Interval> arr,
        State[][] dp,
        int i,
        int k
    ) {

        if (i == arr.size() || k == 0) {
            return new State(0, new ArrayList<>());
        }

        if (dp[i][k] != null) {
            return dp[i][k];
        }

        // Option 1: Skip current interval
        State skip = solve(arr, dp, i + 1, k);

        // Option 2: Take current interval
        Interval curr = arr.get(i);

        // First interval whose start > curr.r
        int next = findNext(arr, i + 1, curr.r);

        State nextState = solve(arr, dp, next, k - 1);

        List<Integer> selected = new ArrayList<>(nextState.indices);
        selected.add(curr.index);

        Collections.sort(selected);

        State take = new State(
            curr.weight + nextState.weight,
            selected
        );

        // Choose better weight
        if (take.weight > skip.weight) {
            return dp[i][k] = take;
        }

        if (take.weight < skip.weight) {
            return dp[i][k] = skip;
        }

        // Same weight -> lexicographically smaller indices
        if (compare(take.indices, skip.indices) < 0) {
            return dp[i][k] = take;
        }

        return dp[i][k] = skip;
    }

    private int findNext(
        List<Interval> arr,
        int start,
        int right
    ) {

        int low = start;
        int high = arr.size();

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (arr.get(mid).l > right) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private int compare(
        List<Integer> a,
        List<Integer> b
    ) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}