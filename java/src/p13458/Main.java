package p13458;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, FIRST, SECOND;
    static int[] people;
    static long answer;
    static int tmp;
    public static void main(String[] args) throws Exception{

        System.setIn(new FileInputStream("src/p13458/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        people = new int[N];
        for (int i = 0;i < N;i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        FIRST = Integer.parseInt(st.nextToken());
        SECOND = Integer.parseInt(st.nextToken());
        
        answer = N;
        for (int i = 0;i < N;i++) {
            tmp = people[i] - FIRST;
            if (tmp > 0) {
                answer += Math.ceil((float) tmp / SECOND);
            }
        }

        System.out.println(answer);

    }

}