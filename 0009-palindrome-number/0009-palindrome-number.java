class Solution {
    public boolean isPalindrome(int x) {
        int a = x;
        int reverse = 0;
        if(x<0){
            return false;
        }
        while(x!=0){
            int digit = x % 10;
            reverse = reverse * 10 + digit;
            x /= 10;
        }
        if(a==reverse){
            return true;
        }
        return false;
    }
    public static void main(String[] args){
        int x = 121;
        Solution s = new Solution();
        System.out.println(s.isPalindrome(x));
    }
}