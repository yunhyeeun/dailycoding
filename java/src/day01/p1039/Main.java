package day01.p1039;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int N, K;
    static int count = 0;
    static boolean[][] visited;
    static Queue<Number> queue;
    static int answer = -1;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./day01/p1039/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(N + ", " + K);

        visited = new boolean[K][1000001];
        queue = new LinkedList<>();
        queue.add(new Number(N, 0));
        System.out.println(queue.peek());

        while (queue.size() > 0) {
            // 1. 큐에서 꺼내기
            Number num = queue.poll();
            char[] numArray = Integer.toString(num.value).toCharArray();
            // 2. 목적지인가?
            if (num.count == K) {
                answer = Math.max(answer, num.value);
            } else {
                // 3. 갈 수 있는 곳 순회
                for (int i = 0;i < numArray.length;i++) {
                    for (int j = i+1;j < numArray.length;j++) {
                        // 4. 갈 수 있는가?
                        if (i == 0 && numArray[j] == 0) {
                            continue;
                        } else {
                            int swapped = swap(numArray, i, j);
                            if (visited[num.count][swapped] == false) {
                                // 5. 체크인
                                visited[num.count][swapped] = true;
                                // 6. 큐에 넣기
                                queue.add(new Number(swapped, num.count + 1));
                            }
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }

    public static void bfs() {
        // 1. 큐에 삽입
        // 2. 목적지인가?
        // 3. 갈 수 있는 곳 순회
        // 4.   갈 수 있는가?
        // 5.       체크인
        // 6.       큐에 넣는다
    }

    // 0이 제일 앞으로 가는 경우 swap 불가
    // 선택했던 index를 다시 선택 가능 -> count 증가할 때마다 visited 초기화

    public static int swap(char[] numArray, int i, int j) {
        char[] array = Arrays.copyOf(numArray, numArray.length);
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        return Integer.parseInt(new String(array));
    }
}

class Number {
    int value;
    int count;

    public Number(int value, int count) {
        this.value = value;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Number [count=" + count + ", value=" + value + "]";
    }

}