package day02.p2517;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Player[] players;
    static Player[] tmp;
    static int[] answer;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./day02/p2517/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        players = new Player[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            players[i] = new Player(Integer.parseInt(st.nextToken()), i + 1, 0);
        }
        System.out.println("N: " + N);
        System.out.println(Arrays.toString(players));

        tmp = new Player[N];
        answer = new int[N];
        mergeSort(0, players.length - 1);

        System.out.println(Arrays.toString(players));
        for (Player p : players) {
            answer[p.original - 1] = p.original - p.frontNum;
        }

        System.out.println(Arrays.toString(answer));

        for (int i = 0;i < N;i++) {
            System.out.println(answer[i]);
        }

    }

    public static void mergeSort(int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(start, mid);
            mergeSort(mid + 1, end);
            merge(start, mid, end);
        }
    }

    public static void merge(int start, int mid, int end) {
        int frontPtr = start;
        int backPtr = mid + 1;
        int idx = start;
        int frontNum = 0;

        while (frontPtr <= mid && backPtr <= end) {
            if (players[frontPtr].capacity < players[backPtr].capacity) {
                frontNum++;
                Player p = players[frontPtr];
                tmp[idx++] = new Player(p.capacity, p.original, p.frontNum);
                frontPtr++;
            } else {
                Player p = players[backPtr];
                tmp[idx++] = new Player(p.capacity, p.original, p.frontNum + frontNum);
                backPtr++;
            }
        }
        while (frontPtr <= mid) {
            frontNum++;
            Player p = players[frontPtr];
            tmp[idx++] = new Player(p.capacity, p.original, p.frontNum);
            frontPtr++;
        }
        while (backPtr <= end) {
            Player p = players[backPtr];
            tmp[idx++] = new Player(p.capacity, p.original, p.frontNum + frontNum);
            backPtr++;
        }
        for (int i = start; i <= end; i++) {
            players[i] = tmp[i];
        }
    }
}

class Player {
    int capacity;
    int original;
    int frontNum;

    public Player(int capacity, int original, int frontNum) {
        this.capacity = capacity;
        this.original = original;
        this.frontNum = frontNum;
    }

    @Override
    public String toString() {
        return "Player [capacity=" + capacity + ", frontNum=" + frontNum + ", original=" + original + "]";
    }

}