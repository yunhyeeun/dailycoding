package day03.p1202;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
    static int N, K;
    static Jewelry[] jewelries;
    static int[] bags;
    static Jewelry[] heap;
    static long answer;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day03/p1202/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(N + ", " + K);
        
        jewelries = new Jewelry[N];
        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            jewelries[i] = new Jewelry(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), false);
        }

        bags = new int[K];
        for (int i = 0;i < K;i++) {
            st = new StringTokenizer(br.readLine());
            bags[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(jewelries, Comparator.comparingInt(Jewelry::getWeight));
        Arrays.sort(bags);
        System.out.println(Arrays.toString(jewelries));
        System.out.println(Arrays.toString(bags));

        PriorityQueue<Jewelry> pq = new PriorityQueue<>(Comparator.comparingInt(Jewelry::getValue).reversed());
 
        int jidx = 0;
        for (int i = 0;i < bags.length;i++) {
            while (jidx < N && jewelries[jidx].weight <= bags[i]) {
                pq.add(jewelries[jidx++]);
            }

            if (!pq.isEmpty()) {
                answer += pq.poll().value;
            }
        }
        System.out.println(answer);
    }
}

class Jewelry {
    int weight;
    int value;

    public Jewelry(int weight, int value, boolean taken) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Jewelry [value=" + value + ", weight=" + weight + "]";
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}