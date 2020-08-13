package day02.p2096;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
    static int N;
    static int[][] map;
    static int[] minarray;
    static int[] maxarray;
    static int max = 0;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./src/day02/p2096/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][3];
        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0;j < 3;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(N);
        for (int i = 0;i < N;i++) {
            System.out.println(Arrays.toString(map[i]));
        }

        // Solution
        minarray = new int [3];
        maxarray = new int [3];
        int min0, min1, min2, max0, max1, max2;
        for (int i = 0;i < N;i++) {
            min0 = Math.min(minarray[0], minarray[1]) + map[i][0];
            min1 = Math.min(minarray[0], Math.min(minarray[1], minarray[2])) + map[i][1];
            min2 = Math.min(minarray[1], minarray[2]) + map[i][2];
            max0 = Math.max(maxarray[0], maxarray[1]) + map[i][0];
            max1 = Math.max(maxarray[0], Math.max(maxarray[1], maxarray[2])) + map[i][1];
            max2 = Math.max(maxarray[1], maxarray[2]) + map[i][2];
            
            minarray[0] = min0;
            minarray[1] = min1;
            minarray[2] = min2;
            maxarray[0] = max0;
            maxarray[1] = max1;
            maxarray[2] = max2;
        }
        Arrays.sort(minarray);
        Arrays.sort(maxarray);
        System.out.println(maxarray[2] + " " + minarray[0]);
    } 
}