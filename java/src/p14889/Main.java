package p14889;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] ARR;
    static boolean[] VISITED;
    static int ANSWER = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{

        System.setIn(new FileInputStream("src/p14889/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        ARR = new int[N][N];
        VISITED = new boolean[N];
        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0;j < N;j++) {
                ARR[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0;i < N;i++) {
            VISITED[i] = true;
            dfs(i, 1);
            VISITED[i] = false;
        }
        System.out.println(ANSWER);
    }

    public static void dfs(int person, int current) {
        if (current == N / 2) {
            int sum1 = 0;
            int sum2 = 0;
            for (int i = 0;i < N;i++) {
                if (VISITED[i]) {
                    for (int j = i + 1;j < N;j++) {
                        if (VISITED[j]) {
                            sum1 += ARR[i][j];
                            sum1 += ARR[j][i];
                        }
                    }
                } else {
                    for (int j = i + 1;j < N;j++) {
                        if (VISITED[j] == false) {
                            sum2 += ARR[i][j];
                            sum2 += ARR[j][i];
                        }
                    }
                }
            }
            ANSWER = Math.min(ANSWER, Math.abs(sum1 - sum2));
        } else {
            for (int i = person + 1;i < N;i++) {
                VISITED[i] = true;
                dfs(i, current + 1);
                VISITED[i] = false;
            }
        }
    }
}