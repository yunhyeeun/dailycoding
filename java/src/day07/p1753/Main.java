package day07.p1753;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int V, E;
    static int start;
    static ArrayList<Path>[] list;
    static PriorityQueue<Path> pq;
    static int[] distance;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day07/p1753/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        list = new ArrayList[V + 1];
        distance = new int[V + 1];
        for (int i = 1;i < V + 1;i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        pq = new PriorityQueue<>();

        for (int i = 1;i <= V;i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0;i < E;i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list[src].add(new Path(dest, weight));
        }
        distance[start] = 0;
        pq.add(new Path(start, 0));
        while (pq.isEmpty() == false) {
            Path current = pq.poll();
            if (distance[current.dest] > current.weight) {
                distance[current.dest] = current.weight;
            }
            for (int i = 0;i < list[current.dest].size();i++) {
                Path next = list[current.dest].get(i);
                if (distance[next.dest] > distance[current.dest] + next.weight) {
                    distance[next.dest] = distance[current.dest] + next.weight;
                    pq.add(new Path(next.dest, distance[next.dest]));
                }
            }
        }
        for (int i = 1;i <= V;i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(distance[i]);
            }
        }
    }  
}

class Path implements Comparable<Path> {
    int dest;
    int weight;

    public Path(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Path [dest=" + dest + ", weight=" + weight + "]";
    }

    @Override
    public int compareTo(Path o) {
        if (weight < o.weight) {
            return -1;
        }
        else if (weight > o.weight) {
            return 1;
        } else {
            if (dest < o.dest) {
                return -1;
            } else if (dest > o.dest) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}