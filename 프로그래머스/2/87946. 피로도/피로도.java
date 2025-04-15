class Solution {
    public int solution(int k, int[][] dungeons) {
        return search(k, dungeons, new boolean[dungeons.length], 0);
    }
    
    private int search(int k, int[][] dungeons, boolean[] visited, int count) {
        if (count == dungeons.length) {
            return count;
        }
        
        System.out.println();
        int max = count;
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                max = Math.max(max, search(k - dungeons[i][1], dungeons, visited, count + 1));
                visited[i] = false;
            }   
        }
        
        return max;
    }
}