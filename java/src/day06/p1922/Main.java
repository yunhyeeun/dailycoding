package day06.p1922;

import java.io.FileInputStream;
import java.util.*;

public class Main {

    static int N, M;
    static Line[] lines;
    static int[] parent;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day06/p1922/input.txt"));
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine();
        lines = new Line[M];
        parent = new int[N + 1];
        for (int i = 0;i <= N;i++) {
            parent[i] = i;
        }
        for (int i = 0;i < M;i++) {
            String[] tmp = sc.nextLine().split(" ");
            lines[i] = new Line(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]));
        }
        Arrays.sort(lines);
        System.out.println(Arrays.toString(lines));

        for (int i = 0;i < M;i++) {
            lines[i].connect();
        }

    }
    
}

class Line implements Comparable<Line> {
    int start;
    int end;
    int weight;

    @Override
    public String toString() {
        return "Line [start=" + start + ", end=" + end + ", weight=" + weight + "]";
    }

    public Line(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Line o) {
        if (weight < o.weight) return -1;
        else if (weight == o.weight) return 0;
        else return 0;
    }

    public void connect() {
    }

}