package p17144;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R, C, T;
    static int[][] BOARD;
    static Point[] MACHINE;
    static int[] DY = {-1, 0, 1, 0};
    static int[] DX = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception{

        System.setIn(new FileInputStream("src/p17144/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        BOARD = new int[R][C];
        MACHINE = new Point[2];
        for (int i = 0;i < R;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0;j < C;j++) {
                BOARD[i][j] = Integer.parseInt(st.nextToken());
                if (BOARD[i][j] == -1) {
                    if (MACHINE[0] != null) {
                        MACHINE[1] = new Point(i, j);
                        BOARD[i][j] = 0;
                    } else {
                        MACHINE[0] = new Point(i, j);
                        BOARD[i][j] = 0;
                    }
                }
            }
        }
        for (int i = 0;i < T;i++) {
            spread();
            clean();
        }
        // for (int i = 0;i < R;i++) {
        //     System.out.println(Arrays.toString(BOARD[i]));
        // }
        System.out.println(sum());

    }

    public static int sum() {
        int result = 0;
        for (int i = 0;i < R;i++) {
            for (int j = 0;j < C;j++) {
                result += BOARD[i][j];
            }
        }
        return result;
    }

    public static void spread() {
        int[][] tmp = new int[R][C];
        for (int i = 0;i < R;i++) {
            for (int j = 0;j < C;j++) {
                if (BOARD[i][j] > 0) {
                    int total = BOARD[i][j];
                    int num = Math.floorDiv(BOARD[i][j], 5);
                    for (int k = 0;k< 4;k++) {
                        int ty = i + DY[k];
                        int tx = j + DX[k];
                        if (isValidPosition(ty, tx) && isCleaner(ty, tx) == false) {
                            tmp[ty][tx] += num;
                            total -= num;
                        }
                    }
                    tmp[i][j] += total;
                }
            }
        }
        BOARD = tmp;
    }

    public static void clean() {
        for (int i = MACHINE[0].y;i > 0;i--) {
            BOARD[i][0] = BOARD[i - 1][0];
        }
        for (int i = 0;i < C - 1;i++) {
            BOARD[0][i] = BOARD[0][i + 1];
        }
        for (int i = 0;i < MACHINE[0].y;i++) {
            BOARD[i][C - 1] = BOARD[i + 1][C - 1];
        }
        for (int i = C - 1;i > 0;i--) {
            BOARD[MACHINE[0].y][i] = BOARD[MACHINE[0].y][i - 1];
        }
        BOARD[MACHINE[0].y][MACHINE[0].x] = 0;
        BOARD[MACHINE[0].y][MACHINE[0].x + 1] = 0;

        for (int i = MACHINE[1].y;i < R - 1;i++) {
            BOARD[i][0] = BOARD[i + 1][0];
        }
        for (int i = 0;i < C - 1;i++) {
            BOARD[R - 1][i] = BOARD[R - 1][i + 1];
        }
        for (int i = R - 1;i > MACHINE[1].y;i--) {
            BOARD[i][C - 1] = BOARD[i - 1][C - 1];
        }
        for (int i = C - 1;i > 0;i--) {
            BOARD[MACHINE[1].y][i] = BOARD[MACHINE[1].y][i - 1];
        }
        BOARD[MACHINE[1].y][MACHINE[1].x] = 0;
        BOARD[MACHINE[1].y][MACHINE[1].x + 1] = 0;
    }


    public static boolean isValidPosition(int y, int x) {
        return y >= 0 && y < R && x >= 0 && x < C;
    }

    public static boolean isCleaner(int y, int x) {
        for (int i = 0;i < 2;i++) {
            if (MACHINE[i].y == y && MACHINE[i].x == x) return true;
        }
        return false;
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