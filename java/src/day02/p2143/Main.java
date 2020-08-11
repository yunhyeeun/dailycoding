package day02.p2143;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T, n, m;
    static int[] A, B;
    static long answer = 0;
    static List<Long> subA;
    static List<Long> subB;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./day02/p2143/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        A = new int[n];
        for (int i = 0;i < n;i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        B = new int[m];
        for (int i = 0;i < m;i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println("T: " + T);
        System.out.println("n: " + n);
        System.out.println(Arrays.toString(A));
        System.out.println("m: " + m);
        System.out.println(Arrays.toString(B));

        subA = getSubarray(A);
        subB = getSubarray(B);

        Collections.sort(subA);
        Collections.sort(subB);

        for (int i = 0;i < subA.size();i++) {
            long target = T - subA.get(i);
            answer += upperBound(target, subB) - lowerBound(target, subB);
        }
        System.out.println(answer);

    }

    public static int upperBound(long target, List<Long>array) {
        int low = 0;
        int high = array.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (subB.get(mid) <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int lowerBound(long target, List<Long>array) {
        int low = 0;
        int high = array.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (subB.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static List<Long> getSubarray(int[] array) {
        List<Long> subarray = new ArrayList<Long>();
        for (int i = 0;i < array.length;i++) {
            long sum = 0;
            for (int j = i;j < array.length;j++) {
                sum += array[j];
                subarray.add(sum);
            }
        }
        return subarray;
    }
    
}