package day08.p1915;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] board;
    static int[][] square;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day08/p1915/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N + 1][M + 1];
        square = new int[N + 1][M + 1];
        for (int i = 1;i <= N;i++) {
            char[] array = br.readLine().toCharArray();
            for (int j = 1;j <= M;j++) {
                board[i][j] = array[j - 1] - '0';
            }
        }
        int answer = 0;
        for (int i = 1;i <= N;i++) {
            for (int j = 1;j <= M;j++) {
                answer = Math.max(answer, findSquare(j ,i));
            }
        }
        System.out.println(answer * answer);

    }

    public static int findSquare(int x, int y) {
        if (square[y][x] > 0) return square[y][x];
        if (board[y][x] == 1) {
            square[y][x] = Math.min(square[y - 1][x], square[y][x - 1]);
            square[y][x] = Math.min(square[y][x], square[y - 1][x - 1]);
            square[y][x]++;
        }
        return square[y][x];
    }
    
}