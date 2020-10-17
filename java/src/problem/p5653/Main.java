package problem.p5653;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T;
    static int N, M, K;
    static int[][] BOARD;
    static Queue<Cell> QUEUE;
    static boolean[][] VISITED;
    static int[] DY = {-1, 0, 1, 0};
    static int[] DX = {0, 1, 0, -1};
    static int TIME;
    public static void main(String[] args) throws Exception{


        System.setIn(new FileInputStream("src/problem/p5653/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for (int t = 1;t <= T;t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            BOARD = new int[2 * K + 50][2 * K + 50];
            VISITED = new boolean[2 * K + 50][2 * K + 50];
            QUEUE = new LinkedList<>();
            for (int i = 0;i < N;i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0;j < M;j++) {
                    BOARD[K + i][K + j] = Integer.parseInt(st.nextToken());
                    if (BOARD[K + i][K + j] > 0) {
                        QUEUE.add(new Cell(K + i, K + j, 0, BOARD[K + i][K + j] * 2));
                        VISITED[K + i][K + j] = true;
                    }
                }
            }
            for (TIME = 0;TIME < K;TIME++) {
                spread();
            }
            System.out.println("#" + t + " " + QUEUE.size());
        }               
    }

    public static void spread() {
        int[][] cells = new int[2 * K + 50][2 * K + 50];
        Cell cell = QUEUE.poll();
        int t = cell.time;
        while (true) {
            if (BOARD[cell.y][cell.x] < cell.life) {
                QUEUE.add(new Cell(cell.y, cell.x, TIME + 1, cell.life - 1));
            } else if (cell.life > 0) {
                if (cell.life > 1) QUEUE.add(new Cell(cell.y, cell.x, TIME + 1, cell.life - 1));
                for (int i = 0;i < 4;i++) {
                    int ty = cell.y + DY[i];
                    int tx = cell.x + DX[i];
                    if (VISITED[ty][tx] == false) {
                        cells[ty][tx] = Math.max(cells[ty][tx], BOARD[cell.y][cell.x] * 2);
                    }
                }
            }
            if (QUEUE.isEmpty()) break;
            if (t != QUEUE.peek().time) break;
            cell = QUEUE.poll();
        }
        for (int i = 0;i < 2 * K + 50;i++) {
            for (int j = 0;j < 2 * K + 50;j++) {
                if (cells[i][j] > 0) {
                    VISITED[i][j] = true;
                    BOARD[i][j] = cells[i][j] / 2;
                    QUEUE.add(new Cell(i, j, TIME + 1, cells[i][j]));
                }
            }
        } 
    }
}

class Cell implements Comparable<Cell>{
    int y;
    int x;
    int time;
    int life;

    public Cell (int y, int x, int time, int life) {
        this.y = y;
        this.x = x;
        this.time = time;
        this.life = life;
    }

    @Override
    public int compareTo(Cell c) {
        if (this.life > c.life) return -1;
        else if (this.life == c.life) return 0;
        else return 1;
    }
    @Override
    public String toString() {
        return "Cell [time=" + time + ", life=" + life + ", x=" + x + ", y=" + y + "]";
    }
}
