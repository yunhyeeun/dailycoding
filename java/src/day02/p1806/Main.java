package day02.p1806;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int N, S;
    static int[] array;
    static int low, high = 0;
    static int sum;
    static int answer;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./day02/p1806/input.txt"));
        // BufferedReader는 무조건 한줄씩 읽음
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        System.out.println(N + ", " + S);

        st = new StringTokenizer(br.readLine());
        array = new int[N+1];
        for (int i = 0;i < N;i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(Arrays.toString(array));

        // Solution
        answer = N + 1;
        sum = array[0];
        while (high < N) {
            if (sum < S) {
                sum += array[++high];
            } else {
                answer = Math.min(answer, high - low + 1);
                sum -= array[low++];
            }
            // System.out.println("sum: " + sum + " index: " + low + ", " + high);
            if (high == N) break;
        }
        if (answer == N + 1) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }
}