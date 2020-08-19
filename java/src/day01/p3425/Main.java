package day01.p3425;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static GoStack program;
    static List<Instruction> instructions;
    static int N;
    static boolean flag;
    static int cnt;
    static int numProgram;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day01/p3425/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        instructions = new ArrayList<>();

        while (true) {
            String[] tokens = br.readLine().split(" ");
            if (tokens[0].equals("QUIT")) {
                break;
            }
            if (tokens[0].equals("END")) {
                if (numProgram > 0) {
                    System.out.println();
                }
                flag = true;
                N = Integer.parseInt(br.readLine());
                continue;
            }
            if (flag) {
                execute(Integer.parseInt(tokens[0]));
                cnt++;
                if (cnt == N) {
                    br.readLine();
                    flag = false;
                    instructions.clear();
                    cnt = 0;
                }
                numProgram++;
            } else {
                if (tokens[0].equals("NUM")) {
                    instructions.add(new Instruction(tokens[0], Integer.parseInt(tokens[1])));
                } else {
                    instructions.add(new Instruction(tokens[0]));
                }
            }

        }
    }

    public static void execute(int value) {
        program = new GoStack();
        program.stack.push(value);
        
        for (Instruction elem : instructions) {
            if (elem.inst.equals("NUM")) {
                program.num(elem.value);
            } else if (elem.inst.equals("POP")) {
                program.pop();
            } else if (elem.inst.equals("INV")) {
                program.inv();
            } else if (elem.inst.equals("DUP")) {
                program.dup();
            } else if (elem.inst.equals("SWP")) {
                program.swap();
            } else if (elem.inst.equals("ADD")) {
                program.add();
            } else if (elem.inst.equals("SUB")) {
                program.sub();
            } else if (elem.inst.equals("MUL")) {
                program.mul();
            } else if (elem.inst.equals("DIV")) {
                program.div();
            } else {
                program.mod();
            }
            if (program.error) {
                break;
            }
        }
        System.out.println(program.stack);
        if (program.error || program.stack.size() != 1) {
            System.out.println("ERROR");
        } else {
            System.out.println(program.stack.pop());
        }
    }
    
}

class Instruction {
    String inst;
    int value;

    public Instruction(String inst, int value) {
        this.inst = inst;
        this.value = value;
    }

    public Instruction(String inst) {
        this.inst = inst;
    }

    @Override
    public String toString() {
        return "Instruction [inst=" + inst + ", value=" + value + "]";
    }

}

class GoStack {

    Stack<Integer> stack = new Stack<>();

    boolean error = false;


    void num(int x) {
        stack.push(x);
    }

    void pop() {
        if (stack.isEmpty()) {
            error = true;
        } else {
            stack.pop();
        }
    }

    void inv() {
        if (stack.isEmpty()) {
            error = true;
        } else {
            int x = stack.pop();
            stack.push(-x);
        }
    }

    void dup() {
        if (stack.isEmpty()) {
            error = true;
        } else {
            stack.push(stack.peek());
        }
    }

    void swap() {
        if (stack.size() < 2) {
            error = true;
        } else {
            int a = stack.pop();
            int b = stack.pop();
            stack.push(a);
            stack.push(b);
        }
    }

    void add() {
        if (stack.size() < 2) {
            error = true;
        } else {
            int a = stack.pop();
            int b = stack.pop();
            if (Math.abs(a + b) > 1000000000) {
                error = true;
            } else {
                stack.push(a + b);
            }
        }
    }

    void sub() {
        if (stack.size() < 2) {
            error = true;
        } else {
            int a = stack.pop();
            int b = stack.pop();
            if (Math.abs(b - a) > 1000000000) {
                error = true;
            } else {
                stack.push(b - a);
            }
        }
    }

    void mul() {
        if (stack.size() < 2) {
            error = true;
        } else {
            long a = stack.pop();
            long b = stack.pop();
            if (Math.abs(a * b) > 1000000000) {
                error = true;
            } else {
                stack.push((int) (a * b));
            }
        }
    }
    
    void div() {
        if (stack.size() < 2) {
            error = true;
        } else {
            int a = stack.pop();
            int b = stack.pop();
            if (a == 0) {
                error = true;
            } else {
                int c = Math.abs(b) / Math.abs(a);
                if ((a > 0 && b > 0) || (a < 0 && b < 0)) {
                    stack.push(c);
                } else {
                    stack.push(-c);
                }
            }
        }
    }

    void mod() {
        if (stack.size() < 2) {
            error = true;
        } else {
            int a = stack.pop();
            int b = stack.pop();
            if (a == 0) {
                error = true;
            } else {
                int c = Math.abs(b) % Math.abs(a);
                if (b > 0) {
                    stack.push(c);
                } else {
                    stack.push(-c);
                }
            }
        }
    }
}