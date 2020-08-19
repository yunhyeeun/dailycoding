package day07.p5719;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int S, D;
    static int U, V, P;
    static ArrayList<Path>[] list, r_list;
    static int[] distance;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day07/p5719/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            list = new ArrayList[N];
            r_list = new ArrayList[N];
            distance = new int[N];
            for (int i = 0;i < N;i++) {
                list[i] = new ArrayList<>();
                r_list[i] = new ArrayList<>();
                distance[i] = Integer.MAX_VALUE;
            }
            if (N == 0 && M == 0) break;
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            for (int i = 0;i < M;i++) {
                st = new StringTokenizer(br.readLine());
                U = Integer.parseInt(st.nextToken());
                V = Integer.parseInt(st.nextToken());
                P = Integer.parseInt(st.nextToken());
                list[U].add(new Path(V, P, false));
                r_list[V].add(new Path(U, P, false));
            }
            findMinPath();
            flagMinPath();
            int answer = solution();
            if (answer == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(answer);
            }

        }  
    }

    public static int solution() {
        PriorityQueue<Path> pq = new PriorityQueue();
        distance = new int[N];
        for (int i = 0;i < N;i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        pq.add(new Path(D, 0, false));
        distance[D] = 0;
        while (pq.isEmpty() == false) {
            Path current = pq.poll();
            for (Path next : r_list[current.dest]) {
                if (next.isMin == false && distance[next.dest] > distance[current.dest] + next.dist) {
                    distance[next.dest] = distance[current.dest] + next.dist;
                    pq.add(new Path(next.dest, distance[next.dest], false));
                }
            }
        }
        return distance[S];
    }

    public static void flagMinPath() {
        PriorityQueue<Path> minPath = new PriorityQueue();
        minPath.add(new Path(D, 0, false));
        while (minPath.isEmpty() == false) {
            Path current = minPath.poll();
            for (Path next: r_list[current.dest]) {
                if (distance[current.dest] == distance[next.dest] + next.dist) {
                    next.isMin = true;
                    minPath.add(new Path(next.dest, 0, false));
                }
            }
        }
    }
    public static void findMinPath() {
        PriorityQueue<Path> pq = new PriorityQueue();
        pq.add(new Path(S, 0, false));
        distance[S] = 0;
        while (pq.isEmpty() == false) {
            Path current = pq.poll();
            for (Path next : list[current.dest]) {
                if (distance[next.dest] > distance[current.dest] + next.dist) {
                    distance[next.dest] = distance[current.dest] + next.dist;
                    pq.add(new Path(next.dest, distance[next.dest], false));
                }
            }
        }
    }
}

class Path implements Comparable<Path>{
    int dest;
    int dist;
    boolean isMin;

    public Path (int dest, int dist, boolean isMin) {
        this.dest = dest;
        this.dist = dist;
        this.isMin = isMin;
    }

    @Override
    public String toString() {
        return "Path [dest=" + dest + ", dist=" + dist + ", isMin=" + isMin + "]";
    }

    @Override
    public int compareTo(Path o) {
        if (dist < o.dist) return -1;
        else if (dist > o.dist) return 1;
        else {
            if (dest < o.dest) return -1;
            else if (dest > o.dest) return 1;
            else return 0;
        }
    }
}