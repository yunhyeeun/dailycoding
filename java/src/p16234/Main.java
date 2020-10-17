package p16234;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, L, R;
    static int[][] BOARD;
    static int[] DY = {-1, 0, 1, 0};
    static int[] DX = {0, 1, 0, -1};
    static int[][] UNION;
    static int UNIONID, ANSWER;
    public static void main(String[] args) throws Exception{

        System.setIn(new FileInputStream("src/p16234/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        BOARD = new int [N][N];
        UNION = new int [N][N];
        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0;j < N;j++) {
                BOARD[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while (true) {
            for (int i = 0;i < N;i++) {
                for (int j = 0;j < N;j++) {
                    if (UNION[i][j] == 0) {
                        UNIONID++;
                    }
                    mark(i, j);
                }
            }
            if (UNIONID == N * N) break;
            move();

            for (int i = 0;i < N;i++) {
                Arrays.fill(UNION[i], 0);
            }
            ANSWER ++;
            UNIONID = 0;
        }
        System.out.println(ANSWER);

    }

    public static void move() {
        ArrayList<Point>[] unions = new ArrayList [UNIONID + 1];
        int[] sum = new int[UNIONID + 1];
        for (int i = 1;i <= UNIONID;i++) {
            unions[i] = new ArrayList<>();
        }
        for (int i = 0;i < N;i++) {
            for (int j = 0;j < N;j++) {
                unions[UNION[i][j]].add(new Point(i, j));
                sum[UNION[i][j]] += BOARD[i][j];
             }
        }
        for (int i = 1;i <= UNIONID;i++) {
            if (sum[i] > 0) sum[i] = Math.floorDiv(sum[i], unions[i].size());
        }
        for (int i = 0;i < N;i++) {
            for (int j = 0;j < N;j++) {
                BOARD[i][j] = sum[UNION[i][j]];
            }
        }
    }


    public static void mark(int y, int x) {
        if (UNION[y][x] == 0) {
            UNION[y][x] = UNIONID;
            for (int i = 0;i < 4;i++) {
                int ty = y + DY[i];
                int tx = x + DX[i];
                if (isValidPosition(ty, tx) && canUnion(ty, tx, y, x)) {
                    mark(ty, tx);
                }
            }
        }
          
    }

    public static boolean isValidPosition(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }

    public static boolean canUnion(int y1, int x1, int y2, int x2) {
        int diff = Math.abs(BOARD[y1][x1] - BOARD[y2][x2]);
        return diff >= L && diff <= R;
    }
}
class Point {
    int y;
    int x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}