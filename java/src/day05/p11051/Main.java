package day05.p11051;

import java.io.FileInputStream;
import java.util.*;
;
public class Main {

    static int N, K;
    static int[][] array;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day05/p11051/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        System.out.println(N + " " + K);

        array = new int[N + 1][N + 1];

        for (int i = 0;i <= N;i++) {
            for (int j = 0;j <= i;j++) {
                if (j == 0 || j == N) {
                    array[i][j] = 1;
                }
                else {
                    array[i][j] = (array[i - 1][j - 1] + array[i - 1][j]) % 10007;
                }
            }
        }
        System.out.println(array[N][K]);
    }
    
}