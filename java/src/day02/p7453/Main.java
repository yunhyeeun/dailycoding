package day02.p7453;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.*;

public class Main {

    static int n;
    static long[] a, b, c, d;
    static long[] ab, cd;
    static long answer;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day02/p7453/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        a = new long[n];
        b = new long[n];
        c = new long[n];
        d = new long[n];
        for (int i = 0;i < n;i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Long.parseLong(st.nextToken());
            b[i] = Long.parseLong(st.nextToken());
            c[i] = Long.parseLong(st.nextToken());
            d[i] = Long.parseLong(st.nextToken());
        }

        // System.out.println(Arrays.toString(a));
        // System.out.println(Arrays.toString(b));
        // System.out.println(Arrays.toString(c));
        // System.out.println(Arrays.toString(d));

        ab = new long[n * n];
        cd = new long[n * n];
        
        for (int i = 0;i < n;i++) {
            for (int j = 0;j < n;j++) {
                ab[n * i + j] = a[i] + b[j];
                cd[n * i + j] = c[i] + d[j];
            }
        }

        Arrays.sort(ab);
        Arrays.sort(cd);

        int abindex = 0;
        int cdindex = n * n - 1;

        while (abindex < n * n && cdindex >= 0) {
            long abvalue = ab[abindex];
            long cdvalue = cd[cdindex];
            if (abvalue + cdvalue == 0) {
                long abcnt = 0;
                long cdcnt = 0;
                while (abindex < n * n && ab[abindex] == abvalue) {
                    abcnt++;
                    abindex++;
                }
                while (cdindex >= 0 && cd[cdindex] == cdvalue) {
                    cdcnt++;
                    cdindex--;
                }
                answer += abcnt * cdcnt;
            } else if (abvalue + cdvalue < 0) {
                abindex++;
            } else {
                cdindex--;
            }
        }
        System.out.println(answer);

    }
    
}