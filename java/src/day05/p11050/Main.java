package day05.p11050;

import java.io.FileInputStream;
import java.util.*;
public class Main {
    
    static int N, K;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src//day05/p11050/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        int[][] pascal = new int[N + 1][N + 1];
        for (int i = 0;i <= N;i++) {
            for (int j = 0;j <= i; j++) {
                if (j == 0 || j == N) {
                    pascal[i][j] = 1;
                } else {
                    pascal[i][j] = pascal[i - 1][j] + pascal[i - 1][j - 1];
                }
            }
        }

        System.out.println(pascal[N][K]);
    }
}