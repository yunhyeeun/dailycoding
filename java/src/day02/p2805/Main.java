package day02.p2805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    
    static int N;
    static long M;
    static long[] trees;
    static long low, high, answer = 0;
    static long mid, sum;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./day02/p2805/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        System.out.println(N + ", " + M);

        st = new StringTokenizer(br.readLine());
        trees = new long[N];
        for (int i = 0; i < N; i++) {
            long tree = Long.parseLong(st.nextToken());
            trees[i] = tree; 
            high = Math.max(high, tree);
        }
        System.out.println(Arrays.toString(trees));

        // Solution
        while (low <= high) {
            mid = (high + low) / 2;
            sum = 0;
            for (int i = 0; i < N; i++) {
                sum += Math.max(trees[i] - mid, 0);
            }
            // sum이 M보다 큰 경우 -> low = mid + 1
            if (sum >= M) {
                low = mid + 1;
                answer = Math.max(answer, mid);
            }
            // sum이 M보다 작은 경우 -> high = mid - 1
            else {
                high = mid - 1;
            }
        }
        System.out.println(answer);
    }
}