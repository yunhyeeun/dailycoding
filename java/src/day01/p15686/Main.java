package day01.p15686;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static List<Point> chickens;
    static List<Point> homes;
    static boolean[] visited;
    static int count, numChicken, numHome;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day01/p15686/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        chickens = new ArrayList<>();
        homes = new ArrayList<>();

        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0;j < N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    chickens.add(new Point(i, j));
                } else if (map[i][j] == 1) {
                    homes.add(new Point(i, j));
                }
            }
        }

        System.out.println(N + ", " + M);
        for (int i = 0;i < N;i++) {
            System.out.println(Arrays.toString(map[i]));
        }

        numChicken = chickens.size();
        numHome = homes.size();
        visited = new boolean[numChicken];

        for (int i = 0;i < numChicken;i++) {
            dfs(i);
        }
        System.out.println(answer);
    }

    public static void dfs(int current) {
        // 1. 체크인
        visited[current] = true;
        count++;
        // 2. 목적지인가?
        if (count == M) {
            answer = Math.min(answer, chickenDistance());
        } else {
            // 3. 갈 수 있는 곳 순회
            for (int i = current + 1;i < numChicken;i++) {
                // 4. 갈 수 있는가?
                if (visited[i] == false) {
                    // 5. 간다
                    dfs(i);
                }
            }
        }
        // 6. 체크아웃
        visited[current] = false;
        count--;
    }

    public static int chickenDistance() {
        int result = 0;
        for (Point p : homes) {
            int tmp = Integer.MAX_VALUE;
            for (int i = 0;i < numChicken;i++) {
                if (visited[i]) {
                    tmp = Math.min(tmp, Math.abs(p.r - chickens.get(i).r) + Math.abs(p.c - chickens.get(i).c));
                }
            }
            result += tmp;
        }
        return result;
    }
}

class Point {
    int r;
    int c;
    
    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
    
    @Override
    public String toString() {
        return "Point [c=" + c + ", r=" + r + "]";
    }
    
}