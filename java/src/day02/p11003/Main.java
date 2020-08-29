package day02.p11003;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	static int N, L;
	static int[] arr;
	static int[] D;
	static ArrayDeque<Integer> dq;
	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("java/src/day02/p11003/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		D = new int [N];
		arr = new int [N + 1];
		dq = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 1;i <= N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			while (dq.isEmpty() == false && dq.getFirst() < i - L + 1) {
				dq.removeFirst();
			}
			while (dq.isEmpty() == false && arr[dq.getLast()] > arr[i]) {
				dq.removeLast();
			}
			dq.offer(i);
			D[i - 1] = arr[dq.getFirst()];
		}
		for (int i = 0;i < N;i++) {
			bw.write(D[i] + " ");
        }
        bw.write("\n");
        bw.flush();
        bw.close();
	}
}
