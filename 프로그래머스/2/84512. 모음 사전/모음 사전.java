class Solution {
    public int solution(String word) {
        int answer = word.length(); 
        String vowels = "AEIOU";
        
        int[] weights = {781, 156, 31, 6, 1};

        for (int i = 0; i < word.length(); i++) {
            int index = vowels.indexOf(word.charAt(i));
            
            answer += weights[i] * index;
        }

        return answer;
    }
}