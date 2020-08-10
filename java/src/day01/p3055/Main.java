package day01.p3055;

import java.io.FileInputStream;
import java.util.*;
import java.util.Scanner;

public class Main {

	// 좌, 우, 위, 아래
	static int[] mx = {-1, 1, 0, 0};
	static int[] my = {0, 0, -1, 1};

	static int R, C;
	static char[][] map;
	static int [][] dp;
	static Queue<Point> queue;
	static Point start;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("java/src/day01/p3055/input.txt"));

		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();

		// System.out.println(R + ", " + C);
		
		map = new char[R][C];
		dp = new int[R][C];
		queue = new LinkedList<>();

		List<Point> waterList = new ArrayList<Point>();

		for (int r = 0;r < R;r++) {
			String line = sc.next();
			for (int c = 0;c < C;c++) {
				map[r][c] = line.charAt(c);
				if (map[r][c] == 'S') {
					queue.add(new Point(r, c, 'S'));
				} else if (map[r][c] == '*') {
					waterList.add(new Point(r, c, '*'));
				}

			}
		}
		queue.addAll(waterList);

		// for (int r = 0;r < R;r++) {
		// 	System.out.println(Arrays.toString(map[r]));
		// }
		// System.out.println(queue);

		boolean foundAnswer = false;

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			// System.out.println(p);
			if (p.type == 'D') {
				System.out.println(dp[p.y][p.x]);
				foundAnswer = true;
				break;
			}
			for (int i=0;i<4;i++) {
				int ty = p.y + my[i];
				int tx = p.x + mx[i];
				if (ty >= 0 && ty < R && tx >= 0 && tx < C) {
					if (p.type == '.' || p.type == 'S') {
						// System.out.println(p.type + " x=" + tx + " y=" + ty);
						if (dp[ty][tx] == 0 && checkSafe(tx, ty)) {
							// System.out.println("safe: x=" + tx + ", y=" + ty);
							dp[ty][tx] = dp[p.y][p.x] + 1;
							queue.add(new Point(ty, tx, map[ty][tx]));
						}
					} else if (p.type == '*' && map[ty][tx] == '.') {
						queue.add(new Point(ty, tx, '*'));
						map[ty][tx] = '*';
					}
				}
			}
		}
		if (foundAnswer == false) {
			System.out.println("KAKTUS");
		}
	}

	public static boolean checkSafe(int x, int y) {
		if (map[y][x] == 'D') {
			return true;
		} else if (map[y][x] == '.') {
			for (int i=0;i<4;i++) {
				int ty = y + my[i];
				int tx = x + mx[i];
				if (ty >= 0 && ty < R && tx >= 0 && tx < C && map[ty][tx] == '*') {
					return false;
				} 
			}
			return true;
		} else {
			return false;
		}
	
	}
}

class Point {
	int x;
	int y;
	char type;

	public Point(int y, int x, char type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Point [type=" + type + ", x=" + x + ", y=" + y + "]";
	}
	
}
