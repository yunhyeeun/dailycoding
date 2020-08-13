package day03.p2042;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class Main {
    
    static int N, M, K;
    static long[] nums;
    static Instruction[] inst;
    static long[] tree;
    static int depth, leafSize;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day03/p2042/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new long[N + 1];
        nums[0] = 0;
        for (int i = 1;i <= N;i++) {
            st = new StringTokenizer(br.readLine());
            nums[i] = Long.parseLong(st.nextToken());
        }
        // System.out.println(Arrays.toString(nums));
        inst = new Instruction[M + K];
        for (int i = 0;i < M + K;i++) {
            st = new StringTokenizer(br.readLine());
            inst[i] = new Instruction(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        // System.out.println(Arrays.toString(inst));
        depth = 0;
        leafSize = 0;
        while (Math.pow(2, depth) < N) {
            depth++;
        }
        leafSize = (int) Math.pow(2, depth);
        tree = new long[(int) Math.pow(2, depth + 1)];
        // System.out.println(depth);
        // System.out.println(tree.length);

        // solution
        // 1. make indexed tree
        makeTree(1, 1, leafSize);
        System.out.println(Arrays.toString(tree));
        // 2. Do instruction
        for (int i = 0;i < inst.length;i++) {
            doInst(inst[i]);
            // System.out.println(Arrays.toString(tree));
        }
    }

    public static long makeTree(int idx, int left, int right) {
        if (left == right) {
            if (left <= N) {
                return tree[idx] = nums[left];
            } else {
                return tree[idx] = 0;
            }
        }
        int mid = (left + right) / 2;
        tree[idx] = makeTree(2 * idx, left, mid);
        tree[idx] += makeTree(2 * idx + 1, mid + 1, right);
        return tree[idx];
    }

    public static void doInst(Instruction inst) {
        // type == 1: update
        if (inst.type == 1) {
            long diff = inst.second - nums[inst.first];
            nums[inst.first] = inst.second;
            update(inst.first, diff, 1, 1, leafSize);
        } 
        // type == 2: add
        else {
            System.out.println(query(inst.first, inst.second, 1, 1, leafSize));
        }
    }

    public static void update(int idx, long diff, int node, int left, int right) {
        if (idx < left || right < idx) {
            return;
        } else {
            tree[node] += diff;
            if (left != right) {
                int mid = (left + right) / 2;
                update(idx, diff, 2 * node, left, mid);
                update(idx, diff, 2 * node + 1, mid + 1, right);
            }
        }
    }

    public static long query(int start, int end, int node, int left, int right) {
        if (end < left || right < start) {
            return 0;
        } else if (start <= left && right <= end) {
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            return query(start, end, 2 * node, left, mid) + query(start, end, 2 * node + 1, mid + 1, right);

        }
    }
}

class Instruction {
    int type;
    int first;
    int second;

    public Instruction(int type, int first, int second) {
        this.type = type;
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "Instruction [type=" + type + ", first=" + first + ", second=" + second +  "]";
    }
}