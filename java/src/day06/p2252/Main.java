package day06.p2252;

import java.io.FileInputStream;
import java.util.*;

public class Main {

    static int N, M;
    static int[] indegree;
    static StringBuilder sb;
    

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day06/p2252/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        // System.out.println(N + " " + M);

        indegree = new int[N + 1];
        ArrayList<Integer>[] list = new ArrayList[N + 1];
        for (int i = 1;i <= N;i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0;i < M;i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            list[a].add(b);
            indegree[b]++;
        }

        sb = new StringBuilder();

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1;i <= N;i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        while (true) {
            if (queue.isEmpty()) break;
            int tmp = queue.poll();
            sb.append(tmp);
            sb.append(" ");
            for (int i = 0;i < list[tmp].size();i++) {
                int next = list[tmp].get(i);
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
        System.out.print(sb.toString());
    }
}