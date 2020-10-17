package p16235;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][] A;
    static int[][] BOARD;
    static PriorityQueue<Tree>[][] TREEARR;
    static int[] DY = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] DX = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws Exception{

        System.setIn(new FileInputStream("src/p16235/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        BOARD = new int[N + 1][N + 1];
        for (int i = 1;i <= N;i++) {
            for (int j = 1;j <= N;j++) {
                BOARD[i][j] = 5;
            }
        }
        TREEARR = new PriorityQueue[N + 1][N + 1];
        for (int i = 0;i <= N;i++) {
            for (int j = 0;j <= N;j++) {
                TREEARR[i][j] = new PriorityQueue<>();
            }
        }
        A = new int[N + 1][N + 1];
        for (int i = 1;i <= N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1;j <= N;j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0;i < M;i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            TREEARR[y][x].add(new Tree(y, x, Integer.parseInt(st.nextToken())));
        }
        for (int i = 0;i < K;i++) {
            oneyear();
        }
        System.out.println(countTree());



    }

    public static int countTree() {
        int result = 0;
        for (int i = 1;i <= N;i++) {
            for (int j = 1;j <= N;j++) {
                result += TREEARR[i][j].size();
            }
        }
        return result;
    }

    public static void oneyear() {
        PriorityQueue<Tree>[][] newtreearr = spring();
        summer();
        TREEARR = newtreearr;
        autumn();
        winter();
    }

    public static PriorityQueue<Tree>[][] spring() {
        PriorityQueue<Tree>[][] trees = new PriorityQueue[N + 1][N + 1];
        for (int i = 0;i <= N;i++) {
            for (int j = 0;j <= N;j++) {
                trees[i][j] = grow(i, j);
            }
        }
        return trees;
    }

    public static PriorityQueue<Tree> grow(int y, int x) {
        PriorityQueue<Tree> trees = new PriorityQueue<>();
        while (TREEARR[y][x].isEmpty() == false) {
            if (BOARD[y][x] < TREEARR[y][x].peek().age) break;
            BOARD[y][x] -= TREEARR[y][x].peek().age;
            TREEARR[y][x].peek().age += 1;
            trees.add(TREEARR[y][x].poll());
        }
        return trees;
    }

    public static void summer() {
        for (int i = 0;i <= N;i++) {
            for (int j = 0;j <= N;j++) {
                change(i, j);
            }
        }
    }

    public static void change(int y, int x) {
        while (TREEARR[y][x].isEmpty() == false) {
            BOARD[y][x] += Math.floorDiv(TREEARR[y][x].poll().age, 2);
        }
    }

    public static void autumn() {
        for (int i = 1;i <= N;i++) {
            for (int j = 1;j <= N;j++)
                offspring(i, j);
        }

    }

    public static void offspring(int y, int x) {
        Tree[] tmp = new Tree[TREEARR[y][x].size()];
        tmp = TREEARR[y][x].toArray(tmp);
        for (int i = 0;i < tmp.length;i++) {
            if (tmp[i].age % 5 == 0) {
                for (int j = 0;j < 8;j++) {
                    int ty = tmp[i].y + DY[j];
                    int tx = tmp[i].x + DX[j];
                    if (isValidPosition(ty, tx)) {
                        TREEARR[ty][tx].add(new Tree(ty, tx, 1));
                    }
                }
            }
        }
    }

    public static boolean isValidPosition(int y, int x) {
        return y > 0 && y <= N && x > 0 && x <= N;
    }

    public static void winter() {
        for (int i = 1;i <= N;i++) {
            for (int j = 1;j <= N;j++) {
                BOARD[i][j] += A[i][j];
            }
        }
    }
}

class Tree implements Comparable<Tree> {
    int y;
    int x;
    int age;

    public Tree(int y, int x, int age) {
        this.y = y;
        this.x = x;
        this.age = age;
    }

    @Override
    public int compareTo(Tree t) {
        if (this.age < t.age) return -1;
        else if (this.age == t.age) return 0;
        else return 1;
    }

    @Override
    public String toString() {
        return "Tree [age=" + age + ", x=" + x + ", y=" + y + "]";
    }

    

}