package day04.eratosTest;

public class Main {

    static int[] map = new int[1001];
    public static void main (String[] args) {
        int N = 1;

        for (int i = 2;i < map.length;i++) {
            if (map[i] == 0) {
                for (int j = 2; i * j < map.length;j++) {
                    if (map[i * j] == 0) {
                        map[i * j] = 1;
                    }
                }
            }
        }
        for (int i = 2; i < map.length;i++) {
            if (map[i] == 0) {
                System.out.println(i);
            }
        }
    }
    
}