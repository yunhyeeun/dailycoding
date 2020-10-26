package problem.p62560;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main  {
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int[][] land;
    static int[][] visited;
    static int[] parent;
    static int N, height, numarea;
    static Queue<Point> queue;
    static PriorityQueue<Node> pq;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/problem/p62560/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        land = new int[N][N];
        st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0;j < N;j++) {
                land[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        visited = new int[N][N];
        pq = new PriorityQueue<>();
        for (int i = 0;i < N;i++) {
            for (int j = 0;j < N;j++) {
                if (visited[i][j] > 0) continue;
                else {
                    setArea(land, height, i, j);
                }
            }
        }
        makeEdge(land);
        parent = new int[numarea];
        for (int i = 0;i < numarea;i++) {
            parent[i] = i;
        }

        int cnt = 0;
        while (pq.isEmpty() == false) {
            Node n = pq.poll();
            if (union(n.src - 1 , n.dest - 1)) {
                answer += n.weight;
                cnt++;
                if (cnt == numarea - 1) break;
            }
        }
        System.out.println(answer);
    }

    public static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return false;
        else {
            parent[pb] = pa;
            return true;
        }
    }
    public static int find(int n) {
        if (n == parent[n]) return n;
        else {
            int tmp = find(parent[n]);
            return parent[n] = tmp;
        }

    }
    public static void makeEdge(int[][] land) {
        for (int i = 0;i < N;i++) {
            for (int j = 0;j < N;j++) {
                int src = visited[i][j];
                for (int k = 1;k < 3;k++) {
                    int ty = i + dy[k];
                    int tx = j + dx[k];
                    if (isValidPosition(ty, tx) && src != visited[ty][tx]) {
                        pq.add(new Node(src, visited[ty][tx], Math.abs(land[ty][tx] - land[i][j])));
                    }
                }
            }
        }       
    }

    public static void setArea(int[][] land,int height, int y, int x) {
        numarea++;
        visited[y][x] = numarea;
        queue = new LinkedList<>();
        queue.add(new Point(y, x));
        while (queue.isEmpty() == false) {
            Point p = queue.poll();
            for (int i = 0;i < 4;i++) {
                int ty = p.y + dy[i];
                int tx = p.x + dx[i];
                if (isValidPosition(ty, tx) && visited[ty][tx] == 0 && (Math.abs(land[p.y][p.x] - land[ty][tx]) <= height)) {
                    visited[ty][tx] = numarea;
                    queue.add(new Point(ty, tx));
                }
            }
        }
    }

    public static boolean isValidPosition(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }
}

class Node implements Comparable<Node> {
    int src;
    int dest;
    int weight;

    public Node (int s, int d, int w) {
        this.src = s;
        this.dest = d;
        this.weight = w;
    }

    @Override
    public boolean equals(Object o) {
        Node n = (Node) o;
        return this.src == n.src && this.dest == n.dest && this.weight == this.weight;
    }

    @Override
    public int hashCode() {
        return src + dest + weight * 3;
    }

    @Override
    public String toString() {
        return "[Src=" + src + ",Dest=" + dest + ", Weight=" + weight + "]";
    }

    @Override
    public int compareTo(Node n) {
        if (this.weight < n.weight) return -1;
        else if (this.weight == n.weight) return 0;
        else return 1;
    }
}

class Point {
    int y;
    int x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}