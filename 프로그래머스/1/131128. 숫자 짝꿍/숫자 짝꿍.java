import java.lang.StringBuilder;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

class Solution {
    static final char BASE = '0';
    
    public String solution(String X, String Y) throws IOException {
        int[] pairs = new int[10];
        for (int i = 0; i < X.length(); i++) {
            int n = X.charAt(i) - BASE;
            pairs[n]++;
        }
        
        int[] nums = new int[10];
        for (int i = 0; i < Y.length(); i++) {
            int n = Y.charAt(i) - BASE;
            if (pairs[n] > 0) {
                nums[n]++;
                pairs[n]--;
            }
        }
        
        StringBuilder res = new StringBuilder();
        for (int i = 9; i > 0; i--) {
            while (nums[i]-- > 0) {
                res.append(i);
            }
        }
        
        if (res.length() == 0) {
            return nums[0] > 0 ? "0" : "-1";
        }
        
        while (nums[0]-- > 0) {
            res.append(0);
        }
        return res.toString();
    }
}