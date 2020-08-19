package day05.p1256;

import java.io.FileInputStream;
import java.util.*;

public class Main {

    static int N, M, K;
    static int [][] pascal;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day05/p1256/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        makePascal(N + M);
        if (K > pascal[M + N][N]) {
            System.out.println(-1);
        } else {
            System.out.println(search(N, M, K));
        }
        
    }

    public static String search(int N, int M, int K) {
        StringBuilder sb = new StringBuilder();
        int acnt = N;
        int zcnt = M;
        int tmp = K;
        
        while (acnt > 0 && zcnt > 0) {
            if (pascal[acnt + zcnt - 1][acnt - 1] >= tmp) {
                sb.append("a");
                acnt --;
            } else {
                sb.append("z");
                tmp -= pascal[acnt + zcnt - 1][acnt - 1];
                zcnt --;
            }
        }
        while (acnt > 0) {
            sb.append("a");
            acnt--;
        }
        while (zcnt > 0) {
            sb.append("z");
            zcnt--;
        }
        return sb.toString();
    }

    public static void makePascal(int N) {
        pascal = new int[N + 1][N + 1];
        for (int i = 0;i <= N;i++) {
            for (int j = 0;j <=i;j++) {
                if (j == 0 || j == N) {
                    pascal[i][j] = 1;
                } else {
                    pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
                    if (pascal[i][j] > 1e9) {
                        pascal[i][j] = 1000000000;
                    }
                }
            }
        }
    }
    
}