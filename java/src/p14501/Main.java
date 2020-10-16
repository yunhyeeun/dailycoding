package p14501;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static Work[] SCHEDULE;
    static int ANSWER;
    // DP[i][j]: i번째 일로 SCHEDULE[j]를 할 때 얻는 이익
    static int[][] DP;
    public static void main(String[] args) throws Exception{

        
        System.setIn(new FileInputStream("src/p14501/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        SCHEDULE = new Work[N + 1];
        DP = new int[N + 1][N + 1];
        for (int i = 1;i <= N;i++) {
            st = new StringTokenizer(br.readLine());
            SCHEDULE[i] = new Work(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 1;i <= N;i++) {
            if (i + SCHEDULE[i].getTime() <= N + 1) {
                DP[1][i] = SCHEDULE[i].getCost();
            }
        }
        for (int i = 2;i <= N;i++) {
            for (int j = i;j <= N;j++) {
                // 현재 이익 + 이전 DP 중 가능한 경우만
                if (j + SCHEDULE[j].getTime() <= N + 1) {
                    for (int k = 1;k < j;k++) {
                        if (k + SCHEDULE[k].getTime() <= j && DP[i - 1][k] > 0) {
                            DP[i][j] = Math.max(DP[i][j], DP[1][j] + DP[i - 1][k]);
                        }
                    }
                }
            }
        }
        for (int i = 1;i <= N;i++) {
            for (int j = 1;j <= N;j++) {
                ANSWER = Math.max(ANSWER, DP[i][j]);
            }
        }
        System.out.println(ANSWER);
    }
}

class Work {
    int time;
    int cost;

    public Work(int t, int c) {
        this.time = t;
        this.cost = c;
    }

    @Override
    public String toString() {
        return "Work [time=" + time + ", cost=" + cost + "]";
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    
}