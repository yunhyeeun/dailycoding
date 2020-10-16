package p14890;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, L;
    static int[][] MAP;
    static boolean[][] VISITED;
    static int ANSWER;
    public static void main(String[] args) throws Exception{

        System.setIn(new FileInputStream("src/p14890/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        MAP = new int[N][N];
        VISITED = new boolean[N][N];
        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0;j < N;j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0;i < N;i++) {
            if (check(true, i, MAP[i])) {
                // System.out.println("ROW: " + i);
                ANSWER++;
            }
        }
        VISITED = new boolean[N][N];
        int[] col = new int[N];
        for (int i = 0;i < N;i++) {
            for (int j = 0;j < N;j++) {
                col[j] = MAP[j][i];
            }
            if (check(false, i, col)) {
                // System.out.println("COL: " + i);
                ANSWER++;
            }
        }

        System.out.println(ANSWER);
    }

    public static boolean check(boolean isRow, int idx, int[] arr) {
        boolean possible = true;
        boolean[] tmp = new boolean[N];
        if (isRow) tmp = Arrays.copyOf(VISITED[idx], N);
        else {
            for (int i = 0;i < N;i++) {
                tmp[i] = VISITED[i][idx];
            }
        }

        for (int i = 0;i < N - 1;i++) {
            if (arr[i + 1] - arr[i] == 1) {
                for (int j = i;j > i - L;j--) {
                    if (j >= 0 && arr[i] == arr[j] && tmp[j] == false) {
                        tmp[j] = true;
                    } else {
                        possible = false;
                        break;
                    }
                }
            } else if (arr[i] - arr[i + 1] == 1) {
                for (int j = i + 1;j < i + L + 1;j++) {
                    if (j < N && arr[i + 1] == arr[j] && tmp[j] == false) {
                        tmp[j] = true;
                    } else {
                        possible = false;
                        break;
                    }
                }
            } else if (arr[i] != arr[i + 1]) {
                possible = false;
            }

            if (possible == false) {
                break;
            }              
        }
        if (possible) {
            if (isRow) VISITED[idx] = tmp;
            else {
                for (int i = 0;i < N;i++) {
                    VISITED[i][idx] = tmp[i];
                }
            }
        }
        return possible;
    }
}