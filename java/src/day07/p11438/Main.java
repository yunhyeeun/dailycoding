package day07.p11438;

import java.io.FileInputStream;
import java.util.*;

public class Main {
    
    static int N, M, cnt;
    static int[][] parent;
    static ArrayList<Integer>[] connect;
    static int[] depthArray;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day07/p11438/input.txt"));
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int tmp = 1;
        cnt = 0;
        while (tmp <= N) {
            tmp *= 2;
            cnt++;
        }

        parent = new int[cnt][N + 1];
        connect = new ArrayList[N + 1];
        depthArray = new int[N + 1];

        for (int i = 1;i <= N;i++) {
            connect[i] = new ArrayList<>();
        }
        for (int i = 0;i < N - 1;i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            connect[a].add(b);
            connect[b].add(a);
        }

        dfs(1,1);
        makeParentArray();

        M = sc.nextInt();
        for (int i = 0;i < M;i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            System.out.println(findLca(x, y));
        }
    }

    public static int findLca(int x, int y) {
        if (depthArray[x] < depthArray[y]) {
            int tmp = y;
            y = x;
            x = tmp;
        }

        for (int i = 0;i <= cnt - 1;i++) {
            if ((depthArray[x] - depthArray[y] & (1 << i)) != 0) {
                x = parent[i][x];
            }
        }
        if (x == y) {
            return x;
        }
        for (int i = cnt - 1;i >= 0;i--) {
            if (parent[i][x] != parent[i][y]) {
                x = parent[i][x];
                y = parent[i][y];
            }
        }
        return parent[0][x];
    }

    public static void makeParentArray() {
        for (int i = 1;i < cnt;i++) {
            for (int j = 1;j <= N;j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }
    }

    public static void dfs(int node, int depth) {
        depthArray[node] = depth;
        for (int i = 0;i < connect[node].size();i++) {
            if (depthArray[connect[node].get(i)] == 0) {
                dfs(connect[node].get(i), depth + 1);
                parent[0][connect[node].get(i)] = node;
            }
        }
    }
}