class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j <= i; j++) {
                int l = Math.max(j - 1, 0);
                int r = Math.min(j, i - 1);
                triangle[i][j] += Math.max(triangle[i - 1][l], triangle[i - 1][r]);
                answer = Math.max(answer, triangle[i][j]);
            }
        }
        return answer;
    }
}