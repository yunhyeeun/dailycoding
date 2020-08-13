package day02.p1072;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    
    static long X, Y;
    static long Z, answer;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./src/day02/p1072/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        Z = Y * 100 /  X;
        if (winRate(X + X, Y + X) == winRate(X, Y)) {
            System.out.println(-1);
        } else {
            int low = 1;
            int high = (int) X;
            int result  = 0;
            while (low <= high) {
                int mid = (low + high) / 2;
                long tmp = winRate(X + mid, Y + mid);
                if (tmp - Z >= 1) {
                    high = mid - 1;
                    result = mid;
                } else {
                    low = mid + 1;
                }
            }
            System.out.println(result);
        }
    }
    public static long winRate(long x, long y) {
        return y * 100 / x;
    }
}