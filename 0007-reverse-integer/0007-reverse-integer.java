class Solution {
    public int reverse(int x) {
        int digit;
        int revNumber = 0;
        while(x!=0){
            digit = x % 10;
            if (revNumber > Integer.MAX_VALUE / 10 ||
                (revNumber == Integer.MAX_VALUE / 10 && digit > 7))
                return 0;

            if (revNumber < Integer.MIN_VALUE / 10 ||
                (revNumber == Integer.MIN_VALUE / 10 && digit < -8))
                return 0;

            revNumber = revNumber*10 + digit;
            x = x/10;
        }
        return revNumber;
    }
}