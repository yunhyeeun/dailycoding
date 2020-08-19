package day01.p1103;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] board;
    static int[][] dp;
    static boolean[][] visited;
    static int max;
    // 상 우 하 좌
    static int[] my = {-1, 0, 1, 0};
    static int[] mx = {0, 1, 0, -1};
    static boolean error;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day01/p1103/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0;i < N;i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0;j < M;j++) {
                if (tmp[j] == 'H') {
                    tmp[j] = '0';
                }
                board[i][j] = tmp[j] - '0';
            }
        }

        dfs(new Point(0,0,1));
        if (error) {
            System.out.println(-1);
        } else {
            System.out.println(max);
        }
        
    }

    public static void dfs(Point p) {
        if (error) {
            return;
        }
        // System.out.println(p);
        // 끝남
        // 무한루프
        max = Math.max(max, p.movecnt);
        // 체크인
        visited[p.y][p.x] = true;
        dp[p.y][p.x] = p.movecnt;
        p.movecnt++;
        // 갈 수 있는 곳 순회
        for (int i = 0;i < 4;i++) {
            int ty = p.y + my[i] * board[p.y][p.x];
            int tx = p.x + mx[i] * board[p.y][p.x];
            if (ty < 0 || ty >= N || tx < 0 || tx >= M || board[ty][tx] == 0) {
                continue;
            } else if (visited[ty][tx]) {
                error = true;
                return;
            } else if (dp[ty][tx] > p.movecnt) {
                continue;
            }
            dfs(new Point(ty, tx, p.movecnt));
        }
        visited[p.y][p.x] = false;
        p.movecnt--;
    }
    
}

class Point {
    int y;
    int x;
    int movecnt;

    public Point(int y, int x, int movecnt) {
        this.y = y;
        this.x = x;
        this.movecnt = movecnt;
    }

    @Override
    public String toString() {
        return "Point [movecnt=" + movecnt + ", x=" + x + ", y=" + y + "]";
    }
}