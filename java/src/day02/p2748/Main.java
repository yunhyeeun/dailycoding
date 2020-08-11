package day02.p2748;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./day02/p2748/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int a = 1;
        int b = 1;
        int c = 1;

        for (int i = 3;i <= N;i++) {
            c = a + b;
            a = b;
            b = c;
        }
        System.out.println(c);
    }
}