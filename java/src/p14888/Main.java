package p14888;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] NUMBERS;
    // 0: plus, 1: minus, 2: multiply, 3: divide
    static int[] OPS;
    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/p14888/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        NUMBERS = new int [N];
        OPS = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0;i < N;i++) {
            NUMBERS[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0;i < 4;i++) {
            OPS[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, NUMBERS[0]);
        System.out.println(MAX);
        System.out.println(MIN);
    }

    public static void dfs(int current, int result) {
        if (current == N - 1) {
            MAX = Math.max(MAX, result);
            MIN = Math.min(MIN, result);
        } else {
            for (int i = 0;i < 4;i++) {
                if (OPS[i] > 0) {
                    int num = NUMBERS[current + 1];
                    OPS[i] --;
                    int next = calculate(i, result, num);
                    dfs(current + 1, next);
                    OPS[i] ++;
                }
            }
        }
    }

    public static int calculate(int op, int a, int b) {
        switch(op) {
            case 0:
                return a + b;
            case 1:
                return a - b;
            case 2:
                return a * b;
            case 3:
                if (a < 0) return -Math.floorDiv(-a, b);
                else return Math.floorDiv(a, b);
        }
        return Integer.MAX_VALUE;
    }
}