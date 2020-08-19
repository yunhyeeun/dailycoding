package day07.p3176;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static ArrayList<Road>[] connect;
    static int cnt;
    static int[] depthArray;
    static int[][][] parent;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day07/p3176/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        cnt = 0;
        int tmp = 1;
        while (tmp <= N) {
            tmp *= 2;
            cnt++;
        }
        connect = new ArrayList[N + 1];
        parent = new int [cnt][N + 1][3];
        depthArray = new int [N + 1];
        for (int i = 1;i <= N;i++) {
            connect[i] = new ArrayList<>();
        }
        for (int i = 0;i < N - 1;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            connect[a].add(new Road(b, distance));
            connect[b].add(new Road(a, distance));
        }
        dfs(1,1);
        makeParentArray();

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0;i < K;i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            findLca(d, e);
        } 
    }

    public static int findLca(int x, int y) {
        if (depthArray[x] < depthArray[y]) {
            int tmp = y;
            y = x;
            x = tmp;
        }
        
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = cnt - 1;i >= 0;i--) {
            if ((depthArray[x] - depthArray[y] >= (1 << i))) {
                min = Math.min(min, parent[i][x][1]);
                max = Math.max(max, parent[i][x][2]);
                x = parent[i][x][0];
            }
        }
        if (x == y) {
            System.out.println(min + " " + max);
            return x;
        }
        for (int i = cnt - 1;i >= 0;i--) {
            if (parent[i][x][0] != parent[i][y][0]) {
                min = Math.min(min, Math.min(parent[i][x][1], parent[i][y][1]));
                max = Math.max(max, Math.max(parent[i][x][2], parent[i][y][2]));
                x = parent[i][x][0];
                y = parent[i][y][0];
            }
        }
        min = Math.min(min, Math.min(parent[0][x][1], parent[0][y][1]));
        max = Math.max(max, Math.max(parent[0][x][2], parent[0][y][2]));
        System.out.println(min + " " + max);
        return parent[0][x][0];
    }

    public static void makeParentArray() {
        for (int i = 1;i < cnt;i++) {
            for (int j = 1;j <= N;j++) {
                parent[i][j][0] = parent[i - 1][parent[i - 1][j][0]][0];
                parent[i][j][1] = Math.min(parent[i - 1][j][1], parent[i - 1][parent[i - 1][j][0]][1]);
                parent[i][j][2] = Math.max(parent[i - 1][j][2], parent[i - 1][parent[i - 1][j][0]][2]);
            }
        }
    }

    public static void dfs(int node, int depth) {
        depthArray[node] = depth;
        for (int i = 0;i < connect[node].size();i++) {
            Road next = connect[node].get(i);
            if (depthArray[next.dest] == 0) {
                dfs(next.dest, depth + 1);
                parent[0][next.dest][0] = node;
                parent[0][next.dest][1] = next.dist;
                parent[0][next.dest][2] = next.dist;
            }
        }
    }
}

class Road {
    int dest;
    int dist;

    public Road(int dest, int dist) {
        this.dest = dest;
        this.dist = dist;
    }

    @Override
    public String toString() {
        return "Road [dest=" + dest + ", dist=" + dist + "]";
    }
}