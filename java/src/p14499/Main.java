package p14499;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, X, Y, K;
    static int[][] BOARD;
    static int[] ORDER;
    static Dice DICE;
    static int[] DX = {0, 1, -1, 0, 0};
    static int[] DY = {0, 0, 0, -1, 1};
    public static void main(String[] args) throws Exception{

        System.setIn(new FileInputStream("src/p14499/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        BOARD = new int[N][M];
        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0;j < M;j++) {
                BOARD[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ORDER = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0;i < K;i++) {
            ORDER[i] = Integer.parseInt(st.nextToken());
        }

        int[] initial = {0, 0, 0, 0, 0, 0};
        DICE = new Dice(initial, X, Y);
        
        for (int i = 0;i < K;i++) {
            play(ORDER[i]);
        }

    }

    public static void play(int direction) {
        int ty = DICE.getY() + DY[direction];
        int tx = DICE.getX() + DX[direction];
        if (isValidPosition(ty, tx)) {
            DICE.roll(direction);
            change();
            System.out.println(DICE.getTop());
        }

    }

    public static void change() {
        int x = DICE.getX();
        int y = DICE.getY();
        if (BOARD[y][x] == 0) {
            BOARD[y][x] = DICE.getBottom();
        } else {
            DICE.setBottom(BOARD[y][x]);
            BOARD[y][x] = 0;
        }
    }

    public static boolean isValidPosition(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }

}

class Dice {
    // numbers: top, bottom, up, down, left, right
    int[] numbers;
    int y;
    int x;

    public Dice(int[] arr, int x, int y) {
        this.numbers = arr;
        this.x = x; 
        this.y = y;
    }

    public void roll(int direction) {
        int prevTop = numbers[0];
        int prevBottom = numbers[1];
        int prevUp = numbers[2];
        int prevDown = numbers[3];
        int prevLeft = numbers[4];
        int prevRight = numbers[5];
        switch (direction) {
            //1: 동, 2: 서, 3: 북, 4: 남
            case 1:
                this.setTop(prevLeft);
                this.setBottom(prevRight);
                this.setLeft(prevBottom);
                this.setRight(prevTop);
                this.setX(this.getX() + 1);
                break;
            case 2:
                this.setTop(prevRight);
                this.setBottom(prevLeft);
                this.setLeft(prevTop);
                this.setRight(prevBottom);
                this.setX(this.getX() - 1);
                break;
            case 3:
                this.setTop(prevDown);
                this.setBottom(prevUp);
                this.setUp(prevTop);
                this.setDown(prevBottom);
                this.setY(this.getY() - 1);
                break;
            case 4:
                this.setTop(prevUp);
                this.setBottom(prevDown);
                this.setUp(prevBottom);
                this.setDown(prevTop);
                this.setY(this.getY() + 1);
                break;
        }
    }

    public int getTop() {
        return numbers[0];
    }

    public void setTop(int top) {
        this.numbers[0] = top;
    }

    public int getBottom() {
        return numbers[1];
    }

    public void setBottom(int bottom) {
        this.numbers[1] = bottom;
    }

    public int getUp() {
        return numbers[2];
    }

    public void setUp(int up) {
        this.numbers[2] = up;
    }

    public int getDown() {
        return numbers[3];
    }

    public void setDown(int down) {
        this.numbers[3] = down;
    }

    public int getLeft() {
        return numbers[4];
    }

    public void setLeft(int left) {
        this.numbers[4] = left;
    }

    public int getRight() {
        return numbers[5];
    }

    public void setRight(int right) {
        this.numbers[5] = right;
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