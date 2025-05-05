class Solution {
    public int solution(String name) {
        int N = name.length();
        int vertical = 0;
        for (char c : name.toCharArray()) {
            vertical += Math.min(c - 'A', 'Z' - c + 1);
        }
        int horizontal = N - 1; 
        for (int i = 0; i < N; i++) {
            int next = i + 1;
            while (next < N && name.charAt(next) == 'A') {
                next++;
            }
            horizontal = Math.min(horizontal, 2 * i + N - next);
            horizontal = Math.min(horizontal, i + 2 * (N - next));
        }
        return vertical + horizontal;
    }
}