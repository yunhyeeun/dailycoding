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

        initialize();

        for (int i = 0;i < M;i++) {
            st = new StringTokenizer(br.readLine());
            funcNum = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (funcNum == 0) {
                union(a, b);
            } else {
                checkSet(a, b);
            }
        }
    }

    public static void checkSet(int a, int b) {
        if (find(a) == find(b)) System.out.println("YES");
        else System.out.println("NO");
    }

    public static void initialize() {
        parent = new int[N + 1];
        for (int i = 0;i <= N;i++) {
            parent[i] = i;
        }
    }

    public static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot != bRoot) {
            parent[aRoot] = bRoot;
        }
    }

    public static int find(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    } 
    
}