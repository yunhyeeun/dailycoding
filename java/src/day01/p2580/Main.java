package day01.p2580;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int[][] sudoku;
	static boolean finish;
	static ArrayList<Point> emptylist;
	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("java/src/day01/p2580/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sudoku = new int[9][9];
		emptylist = new ArrayList<>();
		for (int i = 0;i < 9;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0;j < 9;j++) {
				int num = Integer.parseInt(st.nextToken());
				sudoku[i][j] = num;
				if (num == 0) {
					emptylist.add(new Point(i, j));
				}
			}
		}
		dfs(0);
		
	}
	
	public static void dfs(int depth) {
		if (finish) {
			return;
		}
		if (depth == emptylist.size()) {
			for (int i = 0;i < 9;i++) {
				for (int j = 0;j < 9;j++) {
					System.out.print(sudoku[i][j] + " ");
				}
				System.out.print("\n");
			}
			finish = true;
		} else {
			Point p = emptylist.get(depth);
			for (int i = 1;i <= 9;i++) {
				if (check(p.y, p.x, i)) {
					sudoku[p.y][p.x] = i;
					dfs(depth + 1);
					sudoku[p.y][p.x] = 0;
				}
			}
		}
		
	}
	
	public static boolean check(int y, int x, int num) {
		if (sudoku[y][x] > 0) {
			return false;
		}
		for (int i = 0;i < 9;i++) {
			if (sudoku[y][i] == num || sudoku[i][x] == num) {
				return false;
			}
		}
		int ty = y / 3 * 3;
		int tx = x / 3 * 3;
		for (int i = 0;i < 3;i++) {
			for (int j = 0;j < 3;j++) {
				if (sudoku[ty + i][tx + j] == num) {
					return false;
				}
			}
		}
		return true;
	}

}

class Point {
	int x;
	int y;
	
	public Point(int y, int x) {
		this.x = x;
		this.y = y;
	}
}
