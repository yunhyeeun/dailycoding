package day01.p9663;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    
    static int N;
    static boolean[][] visited;
    static int answer;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./src/day01/p9663/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        System.out.println(N);

        visited = new boolean[N][N];

        // Solution
        for (int i = 0;i < N; i++) {
            visited[0][i] = true;
            dfs(1);
            visited[0][i] = false;
        }
        System.out.println(answer);

    }

    public static void dfs(int row) {
        if (row == N) {
            answer++;
        } else {
            // 
            for (int i = 0;i < N;i++) {
                if (visited[row][i] == false && check(row, i)) {
                    visited[row][i] = true;
                    dfs(row + 1);
                    visited[row][i] = false;
                }
            }
        }
    }

    public static boolean check(int row, int col) {
        for (int i = 0;i < N;i++) {
            if (visited[i][col]) return false;
            if (visited[row][i]) return false;
        }
        for (int i = row, j = col;i >= 0 && j >= 0; i--, j--) {
            if (visited[i][j] == true) return false;
        }
        for (int i = row, j = col;i < N && j >= 0; i++, j--) {
            if (visited[i][j] == true) return false;
        }
        for (int i = row, j = col;i >= 0 && j < N; i--, j++) {
            if (visited[i][j] == true) return false;
        }
        for (int i = row, j = col;i < N && j < N; i++, j++) {
            if (visited[i][j] == true) return false;
        }
        
        return true;
    }
}