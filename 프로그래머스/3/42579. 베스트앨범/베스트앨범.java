import java.util.*;

class Solution {

    class Song implements Comparable<Song> {
        int id;
        int play;

        public Song(int id, int play) {
            this.id = id;
            this.play = play;
        }

        @Override
        public int compareTo(Song other) {
            if (this.play == other.play) {
                return this.id - other.id;
            }
            return other.play - this.play;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        Map<String, Integer> genreTotal = new HashMap<>();
        Map<String, List<Song>> genreSongs = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            genreTotal.put(genre, genreTotal.getOrDefault(genre, 0) + play);

            genreSongs.putIfAbsent(genre, new ArrayList<>());
            genreSongs.get(genre).add(new Song(i, play));
        }

        List<String> sortedGenres = new ArrayList<>(genreTotal.keySet());
        sortedGenres.sort((a, b) -> genreTotal.get(b) - genreTotal.get(a));

        int[] tempAnswer = new int[genres.length]; 
        int count = 0; 

        for (String genre : sortedGenres) {
            List<Song> songs = genreSongs.get(genre);
            Collections.sort(songs); 

            tempAnswer[count++] = songs.get(0).id;
            
            if (songs.size() > 1) {
                tempAnswer[count++] = songs.get(1).id;
            }
        }
        answer = Arrays.copyOf(tempAnswer, count);
        return answer;
    }
}