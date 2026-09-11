class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];

        // Count frequency of each digit
        for (int d : digits) {
            freq[d]++;
        }

        int count = 0;

        for (int num = 100; num <= 999; num++) {
            // Number must be even
            if (num % 2 != 0) {
                continue;
            }

            int a = num / 100;
            int b = (num / 10) % 10;
            int c = num % 10;

            // Check whether digits are available
            if (a == b && b == c) {
                if (freq[a] >= 3) {
                    count++;
                }
            } 
            else if (a == b) {
                if (freq[a] >= 2 && freq[c] >= 1) {
                    count++;
                }
            } 
            else if (a == c) {
                if (freq[a] >= 2 && freq[b] >= 1) {
                    count++;
                }
            } 
            else if (b == c) {
                if (freq[b] >= 2 && freq[a] >= 1) {
                    count++;
                }
            } 
            else {
                if (freq[a] >= 1 && freq[b] >= 1 && freq[c] >= 1) {
                    count++;
                }
            }
        }

        return count;
    }
}