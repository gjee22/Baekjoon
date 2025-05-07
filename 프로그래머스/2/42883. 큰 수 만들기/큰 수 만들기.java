import java.lang.StringBuilder;
import java.util.Stack;

class Solution {
    public String solution(String number, int k) {
        Stack<Character> st = new Stack<>();
        int count = 0;
        for (char c : number.toCharArray()) {
            while (!st.isEmpty() && st.peek() < c && count < k) {
                st.pop();
                count++;
            }
            st.push(c);
        }
        
        while (count++ < k) {
            st.pop();
        }
        
        StringBuilder answer = new StringBuilder();
        while (!st.isEmpty()) {
            answer.append(st.pop());
        }
        return answer.reverse().toString();
    }
}