package p16236;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] BOARD;
    static Point STARTPOINT;
    static boolean[][] VISITED;
    static Queue<Point> QUEUE;
    static int[] DY = {-1, 0, 1, 0};
    static int[] DX = {0, 1, 0, -1};
    static int SHARK = 2;
    static int CNT;
    static int FINISH = -1;
    static boolean FINISHFLAG;
    static PriorityQueue<Point> PQ;
    public static void main(String[] args) throws Exception{

        System.setIn(new FileInputStream("src/p16236/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        BOARD = new int[N][N];
        VISITED = new boolean[N][N];
        QUEUE = new LinkedList<>();
        PQ = new PriorityQueue<Point>();
        // 0: 빈칸, 9: 위치
        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0;j < N;j++) {
                BOARD[i][j] = Integer.parseInt(st.nextToken());
                if (BOARD[i][j] == 9) {
                    STARTPOINT = new Point(i, j, 0);
                    VISITED[i][j] = true;
                    BOARD[i][j] = 0;

                }
            }
        }
        while (FINISHFLAG == false) {
            cycle();
        }
        if (FINISH == -1) FINISH = 0;
        System.out.println(FINISH);

    }
    
    public static void cycle() {
        QUEUE.add(STARTPOINT);
        boolean finishFlag = false;
        while (QUEUE.isEmpty() == false) {
            Point p = QUEUE.poll();
            if (BOARD[p.y][p.x] > 0 && BOARD[p.y][p.x] < SHARK) {
                finishFlag = true;
                PQ.add(p);
                continue;
            }
            if (finishFlag) {
                if (p.time == PQ.peek().time) continue;
                else break;
            }
            for (int i = 0;i < 4;i++) {
                int ty = p.y + DY[i];
                int tx = p.x + DX[i];
                if (isValidPosition(ty, tx) && BOARD[ty][tx] >= 0 && BOARD[ty][tx] <= SHARK && VISITED[ty][tx] == false) {
                    VISITED[ty][tx] = true;
                    Point newp = new Point(ty, tx, p.time + 1);
                    QUEUE.add(newp);
                }
            }
        }
        if (finishFlag) {
            Point food = eat();
            FINISH = food.time;
        } else {
            FINISHFLAG = true;
        }
    }

    public static Point eat() {
        Point food = PQ.poll();
        CNT++;
        if (CNT == SHARK) {
            CNT = 0;
            SHARK++;
        }
        BOARD[food.y][food.x] = 0;
        PQ.clear();
        QUEUE.clear();
        STARTPOINT = food;
        VISITED = new boolean[N][N];
        return food;

    }

    public static boolean isValidPosition(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }
}

class Point implements Comparable<Point> {
    int y;
    int x;
    int time;

    public Point(int y, int x, int t) {
        this.y = y;
        this.x = x;
        this.time = t;
    }

    @Override
    public int compareTo(Point p) {
        if (this.time < p.time) return -1;
        else if (this.time == p.time) {
            if (this.y < p.y) return -1;
            else if (this.y == p.y) {
                if (this.x < p.x) return -1;
                else if (this.x == p.x) return 0;
                else return 1;
            } else return 1;
        } else return 1;
    }

    @Override
    public String toString() {
        return "Point [time=" + time + ", y=" + y + ", x=" + x + "]";
    }


}