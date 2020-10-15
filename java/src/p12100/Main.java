package p12100;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static Board currentBoard;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int max;

    public static void main(String[] args) throws Exception{

        System.setIn(new FileInputStream("src/p12100/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        currentBoard = new Board(N);

        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0;j < N;j++) {
                currentBoard.arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, currentBoard);
        System.out.println(max);

    }

    public static int[][] copyBoard(int[][] original) {
        int[][] newboard = new int[original.length][original.length];
        for (int i = 0;i < original.length;i++) {
            for (int j = 0;j < original.length;j++) {
                newboard[i][j] = original[i][j];
            }
        }
        return newboard;
    }
    
    public static void findmax(Board board) {
        for (int i = 0;i < N;i++) {
            for (int j = 0;j < N;j++) {
                max = Math.max(max, board.arr[i][j]);
            }
        }
    }
    public static void dfs(int current, Board board) {
        if (current == 5) {
            // System.out.println("Stage " + current + "\n" + board);
            findmax(board);
        } else {
            for (int i = 0;i < 4;i++) {
                Board tmp = play(i, board);
                // if (current == 0) System.out.println("Stage " + current + "\n" + tmp);
                dfs(current + 1, tmp);
            }
        }
    }

    public static Board play(int direction, Board original) {
        int[][] board = copyBoard(original.arr);
        switch (direction) {
            case 0:
                for (int i = 0;i < N;i++) {
                    for (int j = 0;j < N;j++) {
                        if (board[j][i] == 0) continue;
                        else move(board, j, i, 0);
                    }
                    for (int j = 0;j < N - 1;j++) {
                        if (board[j][i] == board[j+1][i]) {
                            board[j][i] *= 2;
                            board[j+1][i] = 0;
                        }
                    }
                    for (int j = 0;j < N;j++) {
                        if (board[j][i] == 0) continue;
                        else move(board, j, i, 0);
                    }
                }
                break;
            case 1:
                for (int i = 0;i < N;i++) {
                    for (int j = N - 1;j >= 0;j--) {
                        if (board[i][j] == 0) continue;
                        else move(board, i, j, 1);
                    }
                    for (int j = N - 1;j > 0;j--) {
                        if (board[i][j] == board[i][j - 1]) {
                            board[i][j] *= 2;
                            board[i][j - 1] = 0;
                        }
                    }
                    for (int j = N - 1;j >= 0;j--) {
                        if (board[i][j] == 0) continue;
                        else move(board, i, j, 1);
                    }
                }
                break;
            case 2:
                for (int i = 0;i < N;i++) {
                    for (int j = N - 1;j >= 0;j--) {
                        if (board[j][i] == 0) continue;
                        else move(board, j, i, 2);
                    }
                    for (int j = N - 1;j > 0;j--) {
                        if (board[j][i] == board[j - 1][i]) {
                            board[j][i] *= 2;
                            board[j - 1][i] = 0;
                        }
                    }
                    for (int j = N - 1;j >= 0;j--) {
                        if (board[j][i] == 0) continue;
                        else move(board, j, i, 2);
                    }
                }
                break;

            case 3:
                for (int i = 0;i < N;i++) {
                    for (int j = 0;j < N;j++) {
                        if (board[i][j] == 0) continue;
                        else move(board, i, j, 3);
                    }
                    for (int j = 0;j < N - 1;j++) {
                        if (board[i][j] == board[i][j + 1]) {
                            board[i][j] *= 2;
                            board[i][j + 1] = 0;
                        }
                    }
                    for (int j = 0;j < N;j++) {
                        if (board[i][j] == 0) continue;
                        else move(board, i, j, 3);
                    }
                }
                break;
        }
        return new Board(board);
    }

    public static void move(int[][] board, int y, int x, int direction) {
        int ty = y;
        int tx = x;
        int value = board[y][x];
        while (true) {
            ty += dy[direction];
            tx += dx[direction];
            if (isValidPosition(ty, tx) && board[ty][tx] == 0) {
                board[ty - dy[direction]][tx - dx[direction]] = 0;
            } else {
                ty -= dy[direction];
                tx -= dx[direction];
                break;
            }
        }
        board[ty][tx] = value;
    }

    public static boolean isValidPosition(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }

    



    

}

class Board {
    int dimension;
    int[][] arr;

    public Board (int dimension) {
        this.dimension = dimension;
        this.arr = new int[dimension][dimension];
    }

    public Board (int[][] arr) {
        this.dimension = arr.length;
        this.arr = arr;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < dimension;i++) {
            sb.append(Arrays.toString(arr[i]));
            sb.append("\n");
        }
        return sb.toString();
    }
}