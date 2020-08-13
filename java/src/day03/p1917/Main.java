package day03.p1917;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
    static int N;
    static int[] array;
    static MinHeap heap;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day03/p1917/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());

        array = new int[N];

        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            array[i] = Integer.parseInt(st.nextToken());
        }

        // System.out.println(N);
        // System.out.println(Arrays.toString(array));

        heap = new MinHeap();

        for (int i = 0;i < array.length;i++) {
            if (array[i] == 0) {
                System.out.println(heap.delete());
            } else {
                heap.insert(array[i]);
            }
            // System.out.println(heap);
            
        }
    }
}

class MinHeap {
    List<Integer> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
        heap.add(0);
    }

    public void insert(int value) {
        heap.add(value);
        int current = heap.size() - 1;
        int parent = current / 2;
        while (true) {
            if (parent == 0 || heap.get(parent) < heap.get(current)) {
                break;
            }
            int tmp = heap.get(parent);
            heap.set(parent, heap.get(current));
            heap.set(current, tmp);

            current = parent;
            parent = current / 2;
        }
    }

    public int delete() {
        if (heap.size() == 1) {
            return 0;
        }

        int top = heap.get(1);
        heap.set(1, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        int parent = 1;
        while (true) {
            int leftChild = parent * 2;
            int rightChild = parent * 2 + 1;

            if (leftChild >= heap.size()) {
                break;
            }

            int minValue = heap.get(leftChild);
            int minChild = leftChild;

            if (rightChild < heap.size() && minValue > heap.get(rightChild)) {
                minValue = heap.get(rightChild);
                minChild = rightChild;
            }

            if (heap.get(parent) > minValue) {
                int tmp = heap.get(parent);
                heap.set(parent, heap.get(minChild));
                heap.set(minChild, tmp);
                parent = minChild;
            } else {
                break;
            }
        }
        return top;
    }

    @Override
    public String toString() {
        return "MinHeap [heap=" + heap + "]";
    }
    
}