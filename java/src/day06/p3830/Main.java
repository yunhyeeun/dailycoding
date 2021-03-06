package day06.p3830;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int parent[];
    static int weight[];
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day06/p3830/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            String inst = st.nextToken();
            if (inst.equals("!")) {
                int light = Integer.parseInt(st.nextToken());
                int heavy = Integer.parseInt(st.nextToken());
                int diff = Integer.parseInt(st.nextToken());
                union(light, heavy, diff);
            } else if (inst.equals("?")) {
                int light = Integer.parseInt(st.nextToken());
                int heavy = Integer.parseInt(st.nextToken());
                int lightRoot = find(light);
                int heavyRoot = find(heavy);
                if (lightRoot == heavyRoot) {
                    System.out.println(weight[heavy] - weight[light]);
                } else {
                    System.out.println("UNKNOWN");
                }
            } else {
                N = Integer.parseInt(inst);
                M = Integer.parseInt(st.nextToken());
                if (N == 0 && M == 0) break;
                parent = new int[N + 1];
                weight = new int[N + 1];
                for (int i = 1;i <= N;i++) {
                    parent[i] = i;
                }
            }
        }
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        else {
            int tmp = find(parent[x]);
            weight[x] += weight[parent[x]];
            return parent[x] = tmp;
        }
    }

    public static void union(int light, int heavy, int diff) {
        int lightRoot = find(light);
        int heavyRoot = find(heavy);
        if (lightRoot != heavyRoot) {
            parent[heavyRoot] = lightRoot;
            weight[heavyRoot] = weight[light] - weight[heavy] + diff;
        }
    }

    
}
