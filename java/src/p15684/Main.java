package p15684;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, H;
    static boolean[][] BOARD;
    static boolean[][] LEFTPOINT;
    static boolean successFlag;
    static boolean failFlag;
    static int ANSWER = 5;
    public static void main(String[] args) throws Exception{

        System.setIn(new FileInputStream("src/p15684/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        BOARD = new boolean[H + 1][N + 1];
        LEFTPOINT = new boolean[H + 1][N + 1];

        for (int i = 0;i < M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            BOARD[a][b] = true;
            LEFTPOINT[a][b] = true;
            BOARD[a][b + 1] = true;
        }
        if (success()) {
            System.out.println(0);
            return;
        }
        for (int i = 1;i <= H;i++) {
            for (int j = 1;j <= N;j++) {
                dfs(1, i, j);
            }
        }
        if (successFlag == false) ANSWER = -1;
        System.out.println(ANSWER);

    }

    public static void dfs(int current, int y, int x) {
        if (current > 3 || current > M) return;
        if (isInside(y, x) && isPossible(y, x)) {
            BOARD[y][x] = true;
            LEFTPOINT[y][x] = true;
            BOARD[y][x + 1] = true;
            if (success()) {
                successFlag = true;
                ANSWER = Math.min(ANSWER, current);
            } else {
                for (int i = y;i <= H;i++) {
                    for (int j = 1;j <= N;j++) {
                        dfs(current + 1, i, j);
                    }
                }
            }
            BOARD[y][x] = false;
            LEFTPOINT[y][x] = false;
            BOARD[y][x + 1] = false;                
        }
    }
 
    public static boolean success() {
        for (int i = 1;i <= N;i++) {
            if (play(i) == false) return false;
        }
        return true;
    }

    public static boolean play(int start) {
        int x = start;
        for (int y = 1;y <= H;y++) {
            if (BOARD[y][x] == true) {
                x = cross(y, x);
            }
        }
        // System.out.println("Start: " + start + ", Finish: " + x);
        return start == x;
    }

    public static int cross(int y, int x) {
        if (LEFTPOINT[y][x] && x + 1 <= N && BOARD[y][x + 1] == true) {
            return x + 1;
        }
        else if (LEFTPOINT[y][x] == false && x - 1 >= 1 && BOARD[y][x - 1] == true) {
            return x - 1;
        } else {
            return x;
        }
    }

    public static boolean isInside(int y, int x) {
        return y >= 1 && y <= H && x >= 1 && x < N;
    }

    public static boolean isPossible(int y, int x) {
        return BOARD[y][x] == false && BOARD[y][x + 1] == false;
    }
}