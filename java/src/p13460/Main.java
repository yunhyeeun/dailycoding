package p13460;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static Point[][] board;
    static char[] inputs;
    static Point redpoint, bluepoint;
    static int x1, x2, y1, y2;
    static int tx1, tx2, ty1, ty2;
    static boolean[][][][] visited; // redy, redx, bluey, bluex
    static int min = 11;
    static Queue<Stage> queue;
    static boolean rhole, bhole, rflag, bflag;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws Exception {

        // . : 빈칸, # : 이동할 수 없는 장애물 또는 벽
        // 0 : 구멍의 위치, R : 빨간 구슬의 위치, B : 파란 구슬의 위치 

        System.setIn(new FileInputStream("./src/p13460/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new Point[N][M];
        visited = new boolean[N][M][N][M];
        queue = new LinkedList<>();
        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            inputs = st.nextToken().toCharArray();
            for (int j = 0;j < inputs.length;j++) {
                board[i][j] = new Point(i, j, inputs[j]);
                if (board[i][j].getType() == 'R') {
                    redpoint = board[i][j];
                } else if (board[i][j].getType() == 'B') {
                    bluepoint = board[i][j];
                }
            }
        }
        visited[redpoint.getY()][redpoint.getX()][bluepoint.getY()][bluepoint.getX()] = true;
        queue.offer(new Stage(redpoint, bluepoint, 0));

        while (queue.isEmpty() == false) {
            Stage p = queue.poll();
            if (p.getCurrent() > 10) continue;
            int rx = p.getRed().getX();
            int ry = p.getRed().getY();
            int bx = p.getBlue().getX();
            int by = p.getBlue().getY();
            // 0: up, 1: right, 2: down, 3: left
            for (int i = 0;i < 4;i++) {
                boolean isRedFirst = true;
                tx1 = rx;
                ty1 = ry;
                tx2 = bx;
                ty2 = by;
                rhole = false;
                bhole = false;
                rflag = false;
                bflag = false;

                switch (i) {
                    case 0:
                        if (by < ry) isRedFirst = false;
                        break;
                    case 1:
                        if (bx > rx) isRedFirst = false;
                        break;
                    case 2:
                        if (by > ry) isRedFirst = false;
                        break;
                    case 3:
                        if (bx < rx) isRedFirst = false;
                        break;
                }
                while (true) {
                    if (isRedFirst) {
                        move(i, true);
                        move(i, false);
                    } else {
                        move(i, false);
                        move(i, true);
                    }
                    if ((rhole || rflag) && (bhole || bflag)) break;
                }
                if (bhole) {
                    continue;
                }
                if (rhole) {
                    min = Math.min(min, p.getCurrent() + 1);
                    continue;
                }
                if (visited[ty1][tx1][ty2][tx2] == false) {
                    visited[ty1][tx1][ty2][tx2] = true;
                    queue.offer(new Stage(new Point(ty1, tx1, 'R'), new Point(ty2, tx2, 'B'), p.getCurrent() + 1));
                }
            }
        }

        if (min > 10) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    public static void move(int direction, boolean isRed) {
        if (isRed) {
            tx1 += dx[direction];
            ty1 += dy[direction];
            if (board[ty1][tx1].getType() == 'O') {
                rhole = true;
            }
            if (isInBoard(ty1, tx1) == false || (tx1 == tx2 && ty1 == ty2) || board[ty1][tx1].getType() == '#') {
                tx1 -= dx[direction];
                ty1 -= dy[direction];
                rflag = true;
            }
        } else {
            tx2 += dx[direction];
            ty2 += dy[direction];
            if (board[ty2][tx2].getType() == 'O') {
                bhole = true;
            }
            if (isInBoard(ty2, tx2) == false || (tx1 == tx2 && ty1 == ty2) || board[ty2][tx2].getType() == '#') {
                tx2 -= dx[direction];
                ty2 -= dy[direction];
                bflag = true;
            }
        }
    }

    public static boolean isInBoard(int y, int x) {
        return y > 0 && y < N - 1 && x > 0 && y < M - 1;
    }

    
}

class Stage {
	Point red;
	Point blue;
	int current;

	public Stage (Point r, Point b, int current) {
		this.red = r;
		this.blue = b;
		this.current = current;
	}

    public Point getRed() {
        return red;
    }

    public void setRed(Point red) {
        this.red = red;
    }

    public Point getBlue() {
        return blue;
    }

    public void setBlue(Point blue) {
        this.blue = blue;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "Stage : " + current + "\nRedBall : " + red + "\nBlueBall : " + blue;
    }
}

class Point {
    int y;
    int x;
    char type;

    public Point(int y, int x, char type) {
        this.y = y;
        this.x = x;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Point [type=" + type + ", x=" + x + ", y=" + y + "]";
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
    
    public char getType() {
        return type;
    }
}
