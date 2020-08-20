package day08.p13537;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] array;
    static int[][] dp;

    public static void main(String[] args) throws Exception{

        System.setIn(new FileInputStream("./src/day08/p13537/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        array = new int[N + 1];
        dp = new int[N + 1][N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1;i <= N;i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
    }
    
}