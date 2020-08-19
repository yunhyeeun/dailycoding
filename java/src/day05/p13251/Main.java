package day05.p13251;

import java.io.FileInputStream;
import java.util.*;

public class Main {

    static int M;
    static int[] rocks;
    static int K;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day05/p13251/input.txt"));
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        int N = 0;
        rocks = new int[M + 1];
        for (int i = 1; i <= M;i++) {
            rocks[i] = sc.nextInt();
            N += rocks[i];
        }
        K = sc.nextInt();

        double answer = 0;
        for (int i = 1;i <= M;i++) {
            if (rocks[i] < K) {
                continue;
            }
            double p = 1;
            for (int j = 0;j < K;j++) {
                p *= (double) (rocks[i] - j) / (N - j);
            }
            answer += p;
        }
        System.out.println(answer);
    
    }
    
}