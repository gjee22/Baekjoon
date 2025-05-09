class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        for (int i = 1; i <= yellow; i++) {
            if (yellow % i == 0 && brown == 4 + 2 * i + 2 * (yellow / i)) {
                answer[0] = Math.max(yellow / i + 2, i + 2);
                answer[1] = Math.min(yellow / i + 2, i + 2);
            }
        }
        return answer;
    }
}