package day04.p2725;

import java.io.FileInputStream;
import java.util.*;

public class Main {

    static int C;
    static boolean[] notPrime;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day04/p2725/input.txt"));
        Scanner sc = new Scanner(System.in);
        C = sc.nextInt();
        for (int i = 0;i < C;i++) {
            System.out.println(solution(sc.nextInt()));
        }
    }

    public static int solution(int N) {
        notPrime = new boolean[N + 1];
        makePrime(N);
        int result = 0;
        for (int i = 2;i <= N;i++) {
            result += pi(i);
        }
        return 2 * result + 3;
    }

    public static void makePrime(int N) {
        for (int i = 2;i < N + 1;i++) {
            if (notPrime[i] == false) {
                for (int j = 2;i * j < N + 1;j++) {
                    notPrime[i * j] = true;
                }
            }
        }
    }
    public static int pi(int n) {
        int i = 2;
        int answer = 1;
        while (i <= n) {
            int tmp = n;
            int cnt = 0;
            while (notPrime[i] == false && tmp % i == 0) {
                tmp /= i;
                cnt++;
            }
            if (cnt > 0) {
                answer *= Math.pow(i, cnt) - Math.pow(i, cnt - 1);
            }
            i++;
        }
        return answer;
    }
    
}