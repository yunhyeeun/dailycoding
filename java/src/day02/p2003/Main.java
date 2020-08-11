package day02.p2003;

import java.util.*;
import java.io.FileInputStream;

public class Main {
    
    static int N, M;
    static int[] array;
    static int low = 0;
    static int high = 0;
    static int sum;
    static int answer = 0;
    public static void main(String[] args) throws Exception {
        
        System.setIn(new FileInputStream("./day02/p2003/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        // System.out.println(N + ", " + M);

        array = new int[N];
        for (int i = 0;i < N;i++) {
            array[i] = sc.nextInt();
        }
        // System.out.println(Arrays.toString(array));

        // solution
        sum = array[0];
        while (high < N) {
            if (sum < M) {
                high++;
                if (high == N) break;
                sum += array[high];
            } else if (sum == M) {
                // System.out.println("sum: " + sum + " index: " + low + ", " + high);
                answer++;
                high++;
                if (high == N) break;
                sum += array[high];
            } else {
                sum -= array[low];
                low++;
                if (low > high) {
                    high++;
                    if (high == N) break;
                    sum = array[high];
                }
            }
        }
        System.out.println(answer);
    }
}
