package p14891;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Wheel[] ARR;
    static int K;
    static Pair[] ORDER;
    static int ANSWER;
    static boolean[] VISITED;
    public static void main(String[] args) throws Exception{

        // 0: N, 1: S
        System.setIn(new FileInputStream("src/p14891/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        ARR = new Wheel[5];
        VISITED = new boolean[5];
        for (int i = 1;i <= 4;i++) {
            st = new StringTokenizer(br.readLine());
            char[] chararr = st.nextToken().toCharArray();
            ArrayList<Integer> intarrlist = new ArrayList<>();
            for (int j= 0;j < 8;j++) {
                intarrlist.add(chararr[j] - '0');
            }
            ARR[i] = new Wheel(intarrlist);
        }
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        ORDER = new Pair[K];
        // -1: 반시계, 1: 시계
        for (int i = 0;i < K;i++) {
            st = new StringTokenizer(br.readLine());
            ORDER[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0;i < K;i++) {
            int[] cycles = check(ORDER[i]);
            cycle(cycles);
        }
        for (int i = 1;i <= 4;i++) {
            ANSWER += score(i);
        }
        System.out.println(ANSWER);
    }

    public static int[] check(Pair p) {
        int[] arr = new int[5];
        int num = p.num;
        int way = p.way;
        arr[num] = way;
        while (num > 1 && ARR[num].getWestPolar() != ARR[num - 1].getEastPolar()) {
            way = -way;
            arr[num - 1] = way;
            num--;
        }
        num = p.num;
        way = p.way;
        while (num < 4 && ARR[num].getEastPolar() != ARR[num + 1].getWestPolar()) {
            way = -way;
            arr[num + 1] = way;
            num++;
        }
        return arr;
    
    }

    public static int score(int num) {
        switch (num) {
            case 1:
                if (ARR[num].getNorthPolar() == 0)
                    return 0;
                else 
                    return 1;
            case 2:
                if (ARR[num].getNorthPolar() == 0)
                    return 0;
                else
                    return 2;
            case 3:
                if (ARR[num].getNorthPolar() == 0)
                    return 0;
                else
                    return 4;
            case 4:
                if (ARR[num].getNorthPolar() == 0)
                    return 0;
                else
                    return 8;
        }
        return -1;       
    }


    public static void cycle(int[] arr) {
        for (int i = 1;i <= 4;i++) {
            rotate(i, arr[i]);
        } 
    }

    public static void rotate(int num, int way) {
        Wheel current = ARR[num];
        if (way == -1) {
            current.counterclockwise();
        } else if (way == 1) {
            current.clockwise();
        }
    }
}

class Wheel {
    ArrayList<Integer> polar;

    public Wheel(ArrayList<Integer> p) {
        this.polar = p;
    }

    public void clockwise() {
        int tmp = polar.remove(7);
        polar.add(0, tmp);
    }

    public void counterclockwise() {
        int tmp = polar.remove(0);
        polar.add(tmp);
    }

    public int getNorthPolar() {
        return polar.get(0);
    }
    public int getWestPolar() {
        return polar.get(6);
    }

    public int getEastPolar() {
        return polar.get(2);
    }

    @Override
    public String toString() {
        return "Wheel [polar=" + polar + "]";
    }
}

class Pair {
    int num;
    int way;

    public Pair(int n, int w) {
        this.num = n;
        this.way = w;
    }
}