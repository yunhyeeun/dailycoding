package day02.p2842;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static Point map[][];

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day02/p2842/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        N = Integer.parseInt(st.nextToken());
        map = new Point[N][N];
        for (int i = 0;i < N;i++) {
            String str = br.readLine();
            for (int j = 0;j < N;j++) {
                map[i][j] = new Point(str.charAt(j));
            }
        }
        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0;j < N;j++) {
                map[i][j].longitude = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0;i < N;i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
    
}
class Point {
    char type;
    int longitude;

    public Point(char type, int longitude) {
        this.type = type;
        this.longitude = longitude;
    }

    public Point(char type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Point [type=" + type + ", longitude=" + longitude + "]";
    }
}