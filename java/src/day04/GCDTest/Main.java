package day04.GCDTest;

public class Main {
    
    public static void main(String[] args) {
        System.out.println(gcd(132, 36));
    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return Math.abs(a);
     }
}