package p16637;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

        static int N;
        static char[] inputs;
        static int maxpar;
        static boolean[] visited;
        static long maxvalue = Long.MIN_VALUE;
        public static void main(String[] args) throws Exception {

            System.setIn(new FileInputStream("./src/p16637/input.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            visited = new boolean[N];
            maxpar = (int) Math.round(N / 4.0);
            st = new StringTokenizer(br.readLine());
            inputs = st.nextToken().toCharArray();
            dfs(0);
            System.out.println(maxvalue);


    }

    public static void dfs(int current) {
        if (current == maxpar) {
            evaluate();
            return;
        } else {
            for (int i = 0;i < N;i++) {
                if (i % 2 == 0 && visited[i] == false) {
                    if (isValidStart(i) || isValidIdx(i)) {
                        visited[i] = true;
                        evaluate();
                        dfs(current + 1);
                        visited[i] = false;
                    } 
                }
            }
        }
    }

    public static void evaluate() {
        ArrayList<Long>numlist = new ArrayList<>();
        ArrayList<Character>oplist = new ArrayList<>();
        for (int i = 0;i < N;i++) {
            if (inputs[i] >= '0' && inputs[i] <= '9') {
                if (visited[i]) {
                    int a = inputs[i++] - '0';
                    char op = inputs[i++];
                    int b = inputs[i] - '0';
                    numlist.add(calculate(a, op, b));
                } else {
                    numlist.add((long) inputs[i] - '0');
                }
            } else {
                oplist.add(inputs[i]);
            }
        }

        Long result;
        if (numlist.size() == 1) {
            result = (long) numlist.get(0);
        } else {
            result = (long) numlist.remove(0);
            while (numlist.isEmpty() == false) {
                result = calculate(result, oplist.remove(0), numlist.remove(0));
            }
        }
        maxvalue = Math.max(maxvalue, result);
    }

    public static long calculate(long a, char op, long b) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return 0;
    }

    public static boolean isValidStart(int idx) {
        return idx == 0 && idx + 2 < N && visited[idx + 2] == false;
    }

    public static boolean isValidIdx(int idx) {
        return idx > 0 && idx < N - 1 && visited[idx - 2] == false && visited[idx + 2] == false;
    }
    
}
