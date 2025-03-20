import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

class Solution {    
    LinkedList<int[]> res = new LinkedList<>();
        
    public int[][] solution(int n) {
        hanoi(n, 1, 2, 3); 
        int[][] answer = new int[res.size()][2];
        int i = 0;
        for (int[] move : res) {
            answer[i++] = move;
        }
        return answer;
    }
    
    public void hanoi(int n, int from, int by, int to) {
        if (n == 1) {
            res.add(new int[] { from, to });
            return;
        }
        
        hanoi(n - 1, from, to, by);
        res.add(new int[] { from, to });
        hanoi(n - 1, by, from, to);
    }
}