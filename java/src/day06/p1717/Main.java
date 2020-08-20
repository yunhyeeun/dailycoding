package day06.p1717;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int funcNum;
    static int a, b;
    static int[] parent;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day06/p1717/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 1;i <= N;i++) {
            parent[i] = i;
        }
        for (int i = 0;i < M;i++) {
            st = new StringTokenizer(br.readLine());
            int func = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // union
            if (func == 0) {
                union(a, b);
            }
            // find
            else {
                int aRoot = find(a);
                int bRoot = find(b);
                if (aRoot == bRoot) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    public static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot != bRoot) {
            parent[bRoot] = aRoot;
        }
    }

    public static int find(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

}