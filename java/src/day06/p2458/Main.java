package day06.p2458;

import java.io.FileInputStream;
import java.util.*;

public class Main {

    static int N, M;
    static boolean[] visited, r_visited;
    static boolean[][] connect;
    static int cnt, answer;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day06/p2458/input.txt"));
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        connect = new boolean[N + 1][N + 1];

        for (int i = 0;i < M;i++) {
            int small = sc.nextInt();
            int tall = sc.nextInt();
            connect[small][tall] = true;
        }
        for (int i = 1;i <= N;i++) {
            visited = new boolean[N + 1];
            r_visited = new boolean[N + 1];
            cnt = 0;
            dfs(i);
            r_dfs(i);
            if (cnt == N + 1) answer++;
        }
        System.out.println(answer);
    }

    public static void dfs(int current) {
        visited[current] = true;
        cnt++;
        for (int i = 1;i <= N;i++) {
            if (visited[i] == false && connect[current][i]) {
                dfs(i);
            }
        }
    }
    public static void r_dfs(int current) {
        r_visited[current] = true;
        cnt++;
        for (int i = 1;i <= N;i++) {
            if (r_visited[i] == false && connect[i][current]) {
                r_dfs(i);
            }
        }
    }

   

}