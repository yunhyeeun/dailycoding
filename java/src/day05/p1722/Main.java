package day05.p1722;

import java.io.FileInputStream;
import java.util.*;

public class Main {

    static int N;
    // 1 : k, 2 : permutation
    static int type;
    static long k;
    static int[] array;
    static long[] factorial;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        
        System.setIn(new FileInputStream("./src/day05/p1722/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        type = sc.nextInt();

        factorial = new long[N + 1];
        factorial[0] = 1;
        for (int i = 1;i <= N;i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        // System.out.println(Arrays.toString(factorial));

        if (type == 1) {
            k = sc.nextLong();
            System.out.println(findP(k));
        } else {
            array = new int[N + 1];
            for (int i = 1;i <= N;i++) {
                array[i] = sc.nextInt();
            }
            System.out.println(findK(array));
        }
    }

    public static String findP(long k) {
        long tmp = k;
        visited = new boolean[N + 1];
        StringBuilder sb = new StringBuilder();
        for (int i = 1;i <= N;i++) {
            int cnt = 1;
            while (true) {
                if (visited[cnt]) {
                    cnt++;
                    continue;
                } else if (factorial[N - i] < tmp) {
                    tmp -= factorial[N - i];
                    cnt++;
                } else {
                    sb.append(cnt);
                    if (i < N) sb.append(" ");
                    visited[cnt] = true;
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static long findK(int[] array) {
        visited = new boolean[N + 1];
        long k = 1;
        for (int i = 1;i <= N;i++) {
            int cnt = 1;
            while (true) {
                if (cnt == array[i]) {
                    break;
                }
                if (visited[cnt] == false) {
                    k += factorial[N - i];            
                }
                cnt++;
            }
            visited[array[i]] = true;
        }
        return k;
    }
    
}