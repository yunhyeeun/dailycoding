package day03.indexedTree;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] inputs = { 0, 3, 2, 4, 5, 1, 6, 2, 7 };

        IndexedTree it = new IndexedTree(inputs);
        System.out.println(it);
        it.makeTree(1, 1, it.leafSize);
        System.out.println(it);
        System.out.println(it.query(1, 1, it.leafSize, 3, 7));

        int targetIndex = 3;
        int targetValue = 3;
        int diff = targetValue - it.nums[targetIndex];
        it.update(1, 1, it.leafSize, 3, diff);
        System.out.println(it);
    }
}

class IndexedTree {
    int[] tree;
    int[] nums;
    int leafSize, depth;

    public IndexedTree(int[] nums) {
        this.nums = nums;
        this.depth = 0;
        while (Math.pow(2, this.depth) < nums.length - 1) {
            this.depth++;
        }
        this.leafSize = (int) Math.pow(2, depth);
        this.tree = new int[(int) Math.pow(2, depth + 1)];
    }

    // insert value
    public int makeTree(int node, int left, int right) {
        if (left == right) {
            if (left <= nums.length) {
                return tree[node] = nums[left];
            } else {
                return tree[node] = 0;
            }
        }
        int mid = (left + right) / 2;
        tree[node] = makeTree(node * 2, left, mid);
        tree[node] += makeTree(node * 2 + 1, mid + 1, right);
        return tree[node];
    }

    // get value
    public int query(int node, int left, int right, int qLeft, int qRight) {
        if (qRight < left || right < qLeft) {
            return 0;
        } else if (qLeft <= left && right <= qRight) {
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            return query (node * 2, left, mid, qLeft, qRight) +
                query (node * 2 + 1, mid + 1, right, qLeft, qRight);
        }
    }

    // 이론상 leaf에서 올라가면서 값을 수정하지만
    // 코드상 먼저 차이를 구하고 root부터 차례로 바꿔나간다
    // update
    public void update(int node, int left, int right, int index, int diff) {
        if (index < left || right < index) {
            return;
        } else {
            tree[node] += diff;
            if (left != right) {
                int mid = (left + right) / 2;
                update(node * 2, left, mid, index, diff);
                update(node * 2 + 1, mid + 1, right, index, diff);
            }
        }
    }

    @Override
    public String toString() {
        return "IndexedTree [depth=" + depth + ", leafSize=" + leafSize + ", nums=" + Arrays.toString(nums) + ", tree="
                + Arrays.toString(tree) + "]";
    }
}