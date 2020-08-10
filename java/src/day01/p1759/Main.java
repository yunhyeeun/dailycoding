package day01.p1759;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int L, C;
	static char[] data;

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("java/src/day01/p1759/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		L = sc.nextInt();
		C = sc.nextInt();

		// System.out.println(L + ", " + C);

		data = new char[C];

		for (int i = 0; i < C; i++) {
			data[i] = sc.next().charAt(0);
		}

		// System.out.println(Arrays.toString(data));
		Arrays.sort(data);
		// System.out.println(Arrays.toString(data));

		// option 1: 시작점이 여러개일 경우
		// for (int i = 0;i < C; i++) {
		// 	dfs(i, data[i]);
		// }

		// option 2: 시작점을 하나로 설정한 경우
		dfs(-1, "");

	}
	
	static void dfs(int current, String pwd) {
		// 1. 체크인 (생략가능)
		// 2. 목적지인가? => length == L? => 자음, 모음 개수 확인 => 맞으면 저장, 
		if (pwd.length() == L) {
			if (check(pwd)) {
				System.out.println(pwd);
			}
		} else {
			// 3. 갈 수 있는 곳을 순회 => for (current + 1 ~ C-1)
			for (int i = current + 1;i < data.length;i++) {
				// 4. 	갈 수 있는가? (생략가능)
				// 5.   간다 => dfs(next)
				dfs(i, pwd + data[i]);
			}
		}
		// 6. 체크아웃 (생략가능)
	}

	static boolean check(String pwd) {
		int vNum = 0;
		int cNum = 0;
		for (int i = 0; i < pwd.length(); i++) {
			if (pwd.charAt(i) == 'a' || pwd.charAt(i) == 'e' || pwd.charAt(i) == 'i' || pwd.charAt(i) == 'o' || pwd.charAt(i) == 'u') {
				vNum++;
			} else {
				cNum++;
			}
		}
		if (vNum >= 1 && cNum >= 2) {
			// System.out.println(vNum + ", " + cNum);
			return true;
		} else {
			return false;
		}
	}
}
