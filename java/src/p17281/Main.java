package p17281;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] scores; // 선수별 이닝 스코어
    static int[] players = { 4, 3, 2, 1, 5, 6, 7, 8, 9 }; // 타순
    static int next_player; // 다음 타자
    static int score, maxscore;
    static boolean[] visited;
    static int[] tmp, best;
    public static void main(String[] args) throws Exception{

        /*
        0: 아웃 1: 안타 2: 이루타 3: 삼루타 4: 홈런
        */
        // input
        System.setIn(new FileInputStream("src/p17281/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        scores = new int[N][10];
        visited = new boolean[10];
        tmp = new int[9];
        tmp[3] = 1;
        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1;j <= 9;j++) {
                scores[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        permutation(0);
       
        System.out.println(maxscore);

    }

    public static void permutation(int depth) {
        if (depth == 8) {
            BasketBall game = new BasketBall(tmp);
            int result = game.play(N, scores);
            if (result > maxscore) {
                maxscore = result;
                best = game.players;
            }
        } else {
            for (int i = 2;i <= 9;i++) {
                if (visited[i] == false) {
                    visited[i] = true;
                    if (depth < 3) tmp[depth] = i;
                    else tmp[depth + 1] = i;
                    permutation(depth + 1);
                    visited[i] = false;
                }
            }
        }
    }
    
}

class BasketBall {
    boolean[] bases;
    int[] players; // 타순
    int next_player;
    int outcount;
    int score;

    public BasketBall(int[] players) {
        this.bases = new boolean[4];
        this.players = players;
        this.next_player = 0;
        this.outcount = 0;
        this.score = 0;
    }

    public int play(int N, int[][] scores) {
        int result = 0;
        for (int i = 0;i < N;i++) {
            outcount = 0;
            Arrays.fill(bases, false);
            while (outcount < 3) {
                switch (scores[i][players[next_player]]) {
                    case 0:
                        outcount ++;
                        break;
                    case 1:
                        if (bases[3]) {
                            result ++;
                            bases[3] = false;
                        }
                        for (int j = 2;j > 0;j--) {
                            if (bases[j]) {
                                bases[j + 1] = true;
                                bases[j] = false;
                            }
                        }
                        bases[1] = true;
                        break;
                    case 2:
                        for (int j = 3;j > 1;j--) {
                            if (bases[j]) {
                                result ++;
                                bases[j] = false;
                            }
                        }
                        if (bases[1]) {
                            bases[1] = false;
                            bases[3] = true;
                        }
                        bases[2] = true;
                        break;
                    case 3:
                        for (int j = 1;j < 4;j++) {
                            if (bases[j]) {
                                result ++;
                                bases[j] = false;
                            }
                        }
                        bases[3] = true;
                        break;
                    case 4:
                        for (int j = 1;j < 4;j++) {
                            if (bases[j]) {
                                result ++;
                                bases[j] = false;
                            }
                        }
                        result++;
                        break;
                }
                if (next_player == 8) {
                    next_player = 0;
                } else {
                    next_player ++;
                }
                // System.out.println(String.format("%d회차 - 점수: %d, 아웃: %d, 다음타자: %d\n", i, result, outcount, players[next_player]));
            }
        }
        this.score = result;
        return result;
    }

}