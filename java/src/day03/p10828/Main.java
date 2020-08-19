package day03.p10828;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static myStack s;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day03/p10828/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        N = Integer.parseInt(st.nextToken());
        s = new myStack();

        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            String inst = st.nextToken();
            if (inst.equals("push")) {
                int value = Integer.parseInt(st.nextToken());
                s.push(value);
            } else if (inst.equals("pop")) {
                System.out.println(s.pop());
            } else if (inst.equals("size")) {
                System.out.println(s.size());
            } else if (inst.equals("empty")) {
                System.out.println(s.empty());
            } else {
                System.out.println(s.top());
            }
        }
    }
    
}
class myStack {
    List<Integer> stack = new ArrayList<>();

    public void push(int value) {
        stack.add(value);
    }

    public int pop() {
        if (stack.size() == 0) {
            return -1;
        } else {
            return stack.remove(stack.size() - 1);
        }
    }

    public int size() {
        return stack.size();
    }

    public int empty() {
        return stack.size() == 0 ? 1 : 0;
    }

    public int top() {
        if (stack.size() == 0) {
            return -1;
        } else {
            return stack.get(stack.size() - 1);
        }
    }
}
class Instruction {
    String type;
    int value;

    public Instruction(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public Instruction(String type) {
        this.type = type;
    }
}
