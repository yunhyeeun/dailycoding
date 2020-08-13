package day04.p3955;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static int T;
    static int K, C;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./src/day04/p3955/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        for (int i = 0;i < T;i++) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            long answer = solution(eGcd(-K, C));
            if (answer < 0) {
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println(answer);
            }
        }

    }
    static long solution(EgcdResult egcd) {
        if (Math.abs(egcd.r) != 1) {
            return -1;
        } else {
            long x0 = egcd.s * 1 / egcd.r;
            long y0 = egcd.t * 1 / egcd.r;
            while (x0 <= 0 || y0 <= 0) {
                x0 += C / Math.abs(egcd.r);
                y0 += K / Math.abs(egcd.r);
                if (y0 > 1000000000) break;
            }
            if (y0 > 1000000000) return -1;
            else return y0;
        }
    }

    static EgcdResult eGcd(long a, long b) {
        long s0 = 1, t0 = 0, r0 = a;
        long s1 = 0, t1 = 1, r1 = b;
        long tmp;

        while (r1 != 0) {
            long q = r0 / r1;
            tmp = r0 - r1 * q;
            r0 = r1;
            r1 = tmp;

            tmp = s0 - s1 * q;
            s0 = s1;
            s1 = tmp;

            tmp = t0 - t1 * q;
            t0 = t1;
            t1 = tmp;
        }

        return new EgcdResult(s0, t0, r0);
    }
}

class EgcdResult {
    long s;
    long t;
    long r;

    public EgcdResult(long s, long t, long r) {
        this.s = s;
        this.t = t;
        this.r = r;
    }

    @Override
    public String toString() {
        return "EgcdResult [s=" + s + ", t=" + t + ", r=" + r + "]";
    }
    
}