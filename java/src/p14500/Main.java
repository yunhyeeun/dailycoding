package p14500;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] BOARD;
    static int ANSWER;
    static boolean[][] VISITED;
    static int[] DY = {-1, 1, 0, 0};
    static int[] DX = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/p14500/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        BOARD = new int[N][M];
        VISITED = new boolean[N][M];
        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0;j < M;j++) {
                BOARD[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0;i < N;i++) {
            for (int j = 0;j <M;j++) {
                VISITED[i][j] = true;
                dfs(0, i, j, BOARD[i][j]);
                VISITED[i][j] = false;
                compdfs(i, j);            
            }
        }

        System.out.println(ANSWER);

    }

    public static void compdfs(int y, int x) {
        int leaf = 4;
        int min = 10000;
        int sum = BOARD[y][x];
        for (int i = 0;i < 4;i++) {
            int ty = y + DY[i];
            int tx = x + DX[i];
            if (leaf < 3) return;
            if (isValidPosition(ty, tx) == false) {
                leaf--;
                continue;
            } else {
                min = Math.min(min, BOARD[ty][tx]);
                sum += BOARD[ty][tx];
            }
        }
        if (leaf == 4) {
            sum -= min;
        }
        ANSWER = Math.max(ANSWER, sum);
    }

    public static void dfs(int current, int y, int x, int sum) {
        if (current == 3) {
            ANSWER = Math.max(ANSWER, sum);
        } else {
            for (int i = 0;i < 4;i++) {
                int ty = y + DY[i];
                int tx = x + DX[i];
                if (isValidPosition(ty, tx) && VISITED[ty][tx] == false) {
                    VISITED[ty][tx] = true;
                    dfs(current + 1, ty, tx, sum + BOARD[ty][tx]);
                    VISITED[ty][tx] = false;
                }
            }
        }
    }

    public static boolean isValidPosition(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}