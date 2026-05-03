class Solution {
    public long solution(int n, int[] times) {
        long left = 1;
        long right = (long) times[0] * n; 
        
        for (int time : times)  right = Math.max(right, (long) time * n); 
        long answer = right;
        
        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;
            
            for (int time : times) {
                count += mid / time;
                if (count >= n) break;
            }
            if (count >= n) {
                answer = mid;
                right = mid - 1;
            } 
            else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
}