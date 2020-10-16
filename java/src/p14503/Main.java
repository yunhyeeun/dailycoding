package p14503;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    //D: (0, 위), (1, 오른쪽), (2, 아래), (3, 왼쪽)
    static int N, M, Y, X, D;
    static int[][] MAP;
    static int[] DY = {-1, 0, 1, 0};
    static int[] DX = {0, 1, 0, -1};
    static boolean[][] VISITED;
    static Robot ROBOT;
    static int ANSWER;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/p14503/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        MAP = new int [N][M];
        VISITED = new boolean [N][M];
        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0;j < M;j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ROBOT = new Robot(Y, X, D);
        while (true) {
            int x = ROBOT.getX();
            int y = ROBOT.getY();
            if (VISITED[y][x] == false) ANSWER ++;
            VISITED[y][x] = true;
            boolean isAllClean = true;
            for (int i = 0;i < 4;i++) {
                int d = ROBOT.getDirection() - i - 1;
                if (d < 0) d = 4 + d;
                int ty = y + DY[d];
                int tx = x + DX[d];
                if (isValidPosition(ty, tx) && VISITED[ty][tx] == false) {
                    isAllClean = false;
                    ROBOT.setX(tx);
                    ROBOT.setY(ty);
                    ROBOT.setDirection(d);
                    break;
                }
            }
            if (isAllClean == false) continue;
            else {
                int d = (ROBOT.getDirection() + 2) % 4;
                int ty = y + DY[d];
                int tx = x + DX[d];
                if (isValidPosition(ty, tx)) {
                    ROBOT.setX(tx);
                    ROBOT.setY(ty);
                    continue;
                } else break;
            }
        }
        System.out.println(ANSWER);
    }
    public static void clean() {
        
    }

    public static boolean isValidPosition(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M && MAP[y][x] == 0;
    }
    
}

class Robot {
    int y;
    int x;
    int direction;

    public Robot(int y, int x, int d) {
        this.y = y;
        this.x = x;
        this.direction = d;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}