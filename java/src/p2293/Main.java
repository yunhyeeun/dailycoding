package p2293;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static int[] coins;
    static int[] dp;
    public static void main(String[] args) throws Exception{

        System.setIn(new FileInputStream("src/p2293/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coins = new int[N];
        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            coins[i] = Integer.parseInt(st.nextToken());
        }
        
        dp = new int[K + 1];
        dp[0] = 1;
        for (int i = 0;i < N;i++) {
            for (int j = 1;j <= K;j++) {
                if (j >= coins[i]) {
                    dp[j] += dp[j - coins[i]];
                }
            }
        }
        System.out.println(dp[K]);

    }

    
    
}