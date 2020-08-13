package day03.p2504;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static char[] str;
    static Stack<Integer> stack;
    static int answer;
    static boolean success = true;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day03/p2504/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine().toCharArray();
        System.out.println(Arrays.toString(str));

        stack = new Stack<>();

        // '(' : -2, '[' : -3
        for (int i = 0;i < str.length;i++) {
            if (str[i] == '(') {
                stack.push(-2);
                continue;
            } else if (str[i] == '[') {
                stack.push(-3);
                continue;
            }
            int tmp = 0;
            while (true) {
                if (stack.isEmpty()) {
                    success = false;
                    break;
                }
                if (stack.peek() > 0) {
                    tmp += stack.pop();
                } else {
                    int n = str[i] == ')' ? -2 : -3;
                    if (stack.peek() == n) {
                        stack.pop();
                        if (tmp == 0) {
                            stack.push(Math.abs(n));
                        } else {
                            stack.push(Math.abs(n * tmp));
                        }
                        break;
                    } else {
                        success = false;
                        break;
                    }
                }
            }
            if (success == false) {
                break;
            }
        }
        while (stack.isEmpty() == false) {
            if (stack.peek() < 0) {
                success = false;
                break;
            } else {
                answer += stack.pop();
            }
        }
        if (success) {
            System.out.println(answer);
        } else {
            System.out.println(0);
        }
    }
}