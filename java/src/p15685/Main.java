package p15685;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static boolean[][] BOARD;
    static Dragon[] DRAGONARRAY;
    static int[] DY = {0, -1, 0, 1};
    static int[] DX = {1, 0, -1, 0};
    public static void main(String[] args) throws Exception{

        System.setIn(new FileInputStream("src/p15685/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        BOARD = new boolean[101][101];
        DRAGONARRAY = new Dragon[20];
        // d: (0, 오른쪽) (1, 위쪽) (2, 왼쪽) (3, 아래쪽)
        // 0: 1
        // 1: 1 2
        // 2: 1 2 3 2
        // 3: 1 2 3 2 3 0 3 2
        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            ArrayList<Point> arr = new ArrayList<>();
            arr.add(new Point(y, x, d));
            DRAGONARRAY[i] = new Dragon(arr, g);
            makecurve(DRAGONARRAY[i]);
            mark(DRAGONARRAY[i]);
        }
        System.out.println(check());
    }

    public static int check() {
        int cnt = 0;
        for (int i = 0;i < 100;i++) {
            for (int j = 0;j < 100;j++) {
                if (BOARD[i][j] && BOARD[i][j + 1] && BOARD[i + 1][j] && BOARD[i + 1][j + 1]) cnt++;
            }
        }
        return cnt;
    }

    public static void mark(Dragon dragon) {
        for (int i = 0;i < dragon.points.size();i++) {
            Point p = dragon.points.get(i);
            BOARD[p.y][p.x] = true;
            if (i == dragon.points.size() - 1) {
                int ty = p.y + DY[p.direction];
                int tx = p.x + DX[p.direction];
                BOARD[ty][tx] = true;
            }
        }
    }

    public static void makecurve(Dragon dragon) {
        int gcnt = dragon.generation;
        Point endpoint = dragon.endpoint;
        while (gcnt > 0) {
            int size = dragon.points.size();
            for (int i = size - 1;i >= 0;i--) {
                Point tmp = dragon.points.get(i);
                endpoint = dragon.endpoint;
                int ty = endpoint.y + DY[endpoint.direction];
                int tx = endpoint.x + DX[endpoint.direction];
                int newdirection = (tmp.direction + 1) % 4;
                Point newPoint = new Point (ty, tx, newdirection);
                dragon.points.add(newPoint);
                dragon.endpoint = newPoint;
            }
            gcnt--;
        }
    }

}
class Point {
    int y;
    int x;
    int direction;

    public Point (int y, int x, int d) {
        this.y = y;
        this.x = x;
        this.direction = d;
    }

    @Override
    public String toString() {
        return "Point [direction=" + direction + ", x=" + x + ", y=" + y + "]";
    }
}
class Dragon {
    ArrayList<Point> points;
    Point endpoint;
    int generation;

    public Dragon(ArrayList<Point> arr, int g) {
        this.points = arr;
        this.endpoint = arr.get(arr.size() - 1);
        this.generation = g;
    }
}