package day01.p1103;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map;
	static int[] mx = {0, 1, 0, -1};
	static int[] my = {-1, 0, 1, 0};
	static boolean[][] visited;
	static int[][] time;
	static int answer;
	static boolean infinite;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.setIn(new FileInputStream("./src/p1103/input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());	
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		time = new int[N][M];
		for (int i = 0;i < N;i++) {
			char[] arr = br.readLine().toCharArray();
			for (int j = 0;j < M;j++) {
				if (arr[j] == 'H') {
					map[i][j] = 0;
				} else {
					map[i][j] = arr[j] - '0';
				}
			}
		}
		dfs(0, 0, 1);
		if (infinite) {
			System.out.println(-1);
		} else {
			System.out.println(answer);			
		}
	}
	
	public static void dfs(int y, int x, int cnt) {
		
		visited[y][x] = true;
		time[y][x] = cnt;
		answer = Math.max(answer, cnt);
		for (int i = 0;i < 4;i++) {
			int ty = y + my[i] * map[y][x];
			int tx = x + mx[i] * map[y][x];
			if (!canMove(ty, tx) || map[ty][tx] == 0) {
				continue;
			}
			if (visited[ty][tx]) {
				infinite = true;
				return;
			}
			if (time[ty][tx] > cnt) {
				continue;
			}
			dfs(ty, tx, cnt + 1);				
		}
		visited[y][x] = false;
		
	}

	public static boolean canMove(int y, int x) {
		return y >= 0 && y < N && x >= 0 && x < M;
	}
}
