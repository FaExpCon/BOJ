import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int r;      
        int c;      
        int dist;   
        int k;      

        public Node(int r, int c, int dist, int k) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.k = k;
        }
    }

    static int N, M, K;
    static char[][] map;
    static boolean[][][] visited; 
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs());
    }

    static int bfs() {
        if (N == 1 && M == 1) return 1;

        Queue<Node> q = new ArrayDeque<>();
        visited = new boolean[N][M][K + 1];

        q.offer(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (curr.r == N - 1 && curr.c == M - 1) {
                return curr.dist;
            }

            for (int i = 0; i < 4; i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];

                if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                    if (map[nr][nc] == '0') {
                        if (!visited[nr][nc][curr.k]) {
                            visited[nr][nc][curr.k] = true;
                            q.offer(new Node(nr, nc, curr.dist + 1, curr.k));
                        }
                    } 
                    else if (map[nr][nc] == '1') {
                        if (curr.k < K && !visited[nr][nc][curr.k + 1]) {
                            visited[nr][nc][curr.k + 1] = true;
                            q.offer(new Node(nr, nc, curr.dist + 1, curr.k + 1));
                        }
                    }
                }
            }
        }

        return -1;
    }
}