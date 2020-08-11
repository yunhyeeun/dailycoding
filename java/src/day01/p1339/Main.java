package day01.p1339;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int N;
    static String[] words;
    static int max;
    static Set<Character> set;
    static boolean[] visited;
    static Character[] alphabets;
    static HashMap<Character, Integer> map;
    static int answer;
    public static void main(String[] args) throws Exception {
        
        System.setIn(new FileInputStream("./day01/p1339/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        words = new String[N];
        set = new HashSet<Character>();
        visited = new boolean[10];
        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            words[i] = st.nextToken();
            for (int j = 0;j < words[i].length();j++) {
                set.add(words[i].charAt(j));
            }
        }
        alphabets = set.toArray(new Character[set.size()]);
        map = new HashMap<>();
        // System.out.println(N);
        // System.out.println(Arrays.toString(words));
        System.out.println(Arrays.toString(alphabets));
        
        dfs(0);

        System.out.println(answer);
    }

    public static void dfs(int current) {
        // 1. 목적지인가?
        if (current == set.size()) {
            // System.out.println(map.entrySet().toString());
            int tmp = calculate();
            answer = Math.max(answer, tmp);
        } else {
            // 2. 갈 수 있는 곳을 순회
            for (int i = 0;i < 10;i++) {
                // 3. 갈 수 있는가?
                if (visited[i] == false) {
                    // 4. 체크인
                    visited[i] = true;
                    map.put(alphabets[current], i);
                    // 5. 간다
                    dfs(current + 1);
                    // 6. 체크아웃
                    visited[i] = false;
                }
            }
        }
    }

    public static int calculate() {
        int sum = 0;
        for (int i = 0;i < N;i++) {
            int tmp = 0;
            for (int j = 0;j < words[i].length();j++) {
                tmp *= 10;
                tmp += map.get(words[i].charAt(j));
            }
            sum += tmp;
        }
        return sum;
    }
    
}