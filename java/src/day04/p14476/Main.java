package day04.p14476;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] nums;
    static int[] gcdLtoR;
    static int[] gcdRtoL;
    static int answer = 0;
    static int K;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day04/p14476/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0;i < N;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(Arrays.toString(nums));

        gcdLtoR = new int[N];
        gcdRtoL = new int[N];
        
        for (int i = 0;i < N;i++) {
            if (i == 0) {
                gcdLtoR[i] = nums[i];
            } else {
                gcdLtoR[i] = gcd(gcdLtoR[i - 1], nums[i]);
            }
        }
        for (int i = N - 1;i >= 0;i--) {
            if (i == N - 1) {
                gcdRtoL[N - 1] = nums[N - 1];
            } else {
                gcdRtoL[i] = gcd(nums[i], gcdRtoL[i + 1]);
            }
        }
        
        System.out.println(Arrays.toString(gcdLtoR));
        System.out.println(Arrays.toString(gcdRtoL));
        for (int i = 0;i < N;i++) {
            int tmp = 0;
            if (i == 0) {
                tmp = gcdRtoL[i + 1];
            }
            else if (i == N - 1) {
                tmp = gcdLtoR[i - 1];
            } else {
                tmp = gcd(gcdLtoR[i - 1], gcdRtoL[i + 1]);
            }
            if (answer < tmp && nums[i] % tmp != 0) {
                K = nums[i];
                answer = tmp;
            }
        }
        if (answer == 0) {
            System.out.println(-1);
        } else {
            System.out.println(answer + " " + K);
        }
    }

    public static int gcd(int a, int b) {
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
    
}