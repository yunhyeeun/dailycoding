package day05.p1010;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T, N, M;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day05/p1010/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int i = 0;i < T;i++) {
            st =  new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            System.out.println(solution(N, M));
        }

    }
    public static int solution(int N, int M) {
        if (N == 0 || N == M) {
            return 1;
        } else {
            return solution(N - 1, M - 1) + solution(N, M - 1);
        }
        // int [][] array = new int[M + 1][M + 1];

        // for (int i = 0;i <= M;i++) {
        //     for (int j = 0;j <= i;j++) {
        //         if (j == 0 || j == M) {
        //             array[i][j] = 1;
        //         } else {
        //             array[i][j] = array[i - 1][j - 1] + array[i - 1][j];
        //         }
        //     }
        // }
        // return array[M][N];
    }
}