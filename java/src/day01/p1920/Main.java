package day01.p1920;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    
    static int N, M;
    static int[] array;
    static int[] targets;
    
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./day01/p1920/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        array = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0;i < N;i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        targets = new int[M];
        for (int i = 0;i < M;i++) {
            targets[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(N);
        System.out.println(Arrays.toString(array));
        System.out.println(M);
        System.out.println(Arrays.toString(targets));

        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        for (int i = 0;i < M;i++) {
            System.out.println(contains(targets[i]));
        }

    }
    
    public static int contains(int target) {
        int low = 0;
        int high = N - 1;
        int mid = (low + high) / 2;
        if (target > array[N - 1] || target < array[0]) {
            return 0;
        }
        while (low < high) {
            if (array[mid] < target) {
                low = mid + 1;
                mid = (low + high) / 2;
            } else if (array[mid] == target) {
                return 1;
            }
            else {
                high = mid - 1;
                mid = (low + high) / 2;
            }
        }
        if (array[mid] == target) {
            return 1;
        }
        else {
            return 0;
        }
    }
}