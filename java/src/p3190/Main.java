package p3190;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K, L;
    static boolean[][] apple;
    static Queue<Pair> rotate;
    static Track[][] snake;
    static Snake player;
    static boolean finishFlag;
    static int t;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {


        System.setIn(new FileInputStream("./src/p3190/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        snake = new Track[N][N];
        apple = new boolean[N][N];
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        rotate = new LinkedList<>();
        for (int i = 0;i < K;i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            apple[y - 1][x - 1] = true;
        }
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        for (int i = 0;i < L;i++) {
            st = new StringTokenizer(br.readLine());
            rotate.offer(new Pair(Integer.parseInt(st.nextToken()), st.nextToken()));
        }
        for (int i = 0;i < N;i++) {
            Arrays.fill(snake[i], new Track(0, false));
        }
        snake[0][0] = new Track(0, true);
        player = new Snake();

        if (rotate.peek().time == t) {
            Pair p = rotate.poll();
            rotateHead(player, p.direction);
        }

        while (true) {
            t++;
            moveHead(player);
            if (finishFlag) break;
            Point currentHead = player.head;
            if (apple[currentHead.y][currentHead.x]) {
                apple[currentHead.y][currentHead.x] = false;
            } else {
                moveTail(player);
            }
            if (rotate.isEmpty() == false && rotate.peek().time == t) {
                Pair p = rotate.poll();
                rotateHead(player, p.direction);
            }
        }
        System.out.println(t);
    }

    public static void moveHead(Snake player) {
        Point head = player.head;
        int ty = head.y + dy[player.direction];
        int tx = head.x + dx[player.direction];
        if (isValidPosition(ty, tx) && snake[ty][tx].isSnack == false) {
            player.head = new Point(ty, tx);
            snake[ty][tx] = new Track(t, true);
        } else {
            finishFlag = true;
        }
    }

    public static void moveTail(Snake player) {
        Point tail = player.tail;
        snake[tail.y][tail.x] = new Track(t, false);
        player.tail = findTail();

    }

    public static Point findTail() {
        Point p = new Point(0, 0);
        int time = 100000;
        for (int i = 0;i < N;i++) {
            for (int j = 0;j < N;j++) {
                if (snake[i][j].isSnack && time > snake[i][j].time) {
                    time = snake[i][j].time;
                    p = new Point(i, j);
                }
            }
        }
        return p;
    }

    public static boolean isValidPosition(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }

    public static void rotateHead(Snake player, String direction) {
        if (direction.equals("L")) {
            player.direction --;
        } else {
            player.direction ++;
        }
        if (player.direction < 0) {
            player.direction = 3;
        }
        if (player.direction > 3) {
            player.direction = 0;
        }
    }

}
class Track {
    int time;
    boolean isSnack;

    public Track(int t, boolean s) {
        this.time = t;
        this.isSnack = s;
    }

    @Override
    public String toString() {
        return "(" + time + ", " + isSnack + ")";
    }
}
class Snake {
    Point head;
    Point tail;
    int direction;

    public Snake() {
        this.head = new Point(0, 0);
        this.tail = new Point(0, 0);
        this.direction = 1;
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
        return "Point y: " + y + " x: " + x;
    }
}
class Pair {
    int time;
    String direction;

    public Pair (int time, String direction) {
        this.time = time;
        this.direction = direction;
    }

    public String toString() {
        return "Time: " + time + " Direction: " + direction;
    }
}