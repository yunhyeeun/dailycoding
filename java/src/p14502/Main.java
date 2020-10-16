package p14502;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] MAP;
    static boolean[][] VISITED;
    static Queue<Point> VIRUS;
    static int ANSWER;
    //위 오른쪽 아래 왼쪽
    static int[] DY = {-1, 0, 1, 0};
    static int[] DX = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception{

        
        System.setIn(new FileInputStream("src/p14502/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MAP = new int [N][M];
        VISITED = new boolean [N][M];
        VIRUS = new LinkedList<>();
        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0;j < M;j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
                if (MAP[i][j] == 2) {
                    VIRUS.offer(new Point(i, j));
                }
            }
        }
        for (int i = 0;i < N;i++) {
            for (int j = 0;j < M;j++) {
                if (isValidPosition(i, j) && MAP[i][j] == 0 && VISITED[i][j] == false) {
                    VISITED[i][j] = true;
                    MAP[i][j] = 1;
                    dfs(0, i, j);
                    VISITED[i][j] = false;
                    MAP[i][j] = 0;
                }
            }
        }
        
        System.out.println(ANSWER);
    }

    public static void dfs(int current, int y, int x) {
        if (current == 2) {
            int[][] tmp = spread();
            
            int safe = countsafe(tmp);
            ANSWER = Math.max(ANSWER, safe);

            for (int i = 0;i < N;i++) {
                for (int j = 0;j < M;j++) {
                    if (MAP[i][j] == 2) {
                        VIRUS.offer(new Point(i, j));
                    }
                }
            }
        } else {
            for (int i = 0;i < N;i++) {
                for (int j = 0;j < M;j++) {
                    if (isValidPosition(i, j) && MAP[i][j] == 0 && VISITED[i][j] == false) {
                        VISITED[i][j] = true;
                        MAP[i][j] = 1;
                        dfs(current + 1, i, j);
                        VISITED[i][j] = false;
                        MAP[i][j] = 0;
                    }
                }
            }
        }
    }

    public static int[][] copyMap(int[][] original) {
        int[][] newMap = new int[N][M];
        for (int i = 0;i < N;i++) {
            for (int j = 0;j < M;j++) {
                newMap[i][j] = original[i][j];
            }
        }
        return newMap;
    }
    public static int[][] spread() {
        int[][] tmp = copyMap(MAP);
        // System.out.println(VIRUS.size());
        while (VIRUS.isEmpty() == false) {
            Point p = VIRUS.poll();
            for (int i = 0;i < 4;i++) {
                int ty = p.getY() + DY[i];
                int tx = p.getX() + DX[i];
                if (isValidPosition(ty, tx) && tmp[ty][tx] == 0) {
                    tmp[ty][tx] = 2;
                    VIRUS.add(new Point(ty, tx));
                }
            }
        }
        return tmp;
    }
    public static int countsafe(int[][] tmp) {
        int result = 0;
        for (int i = 0;i < N;i++) {
            for (int j = 0;j < M;j++) {
                if (tmp[i][j] == 0) result++;
            }
        }
        return result;
    }


    public static boolean isValidPosition(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}

class Point {
    int y;
    int x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }

    @Override
    public String toString() {
        return "Point[y= " + y + ", x= " + x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}