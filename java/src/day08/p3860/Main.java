package day08.p3860;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int W, H, G, E;
    static Node[][] map;
    static int[][] path;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static ArrayList<Node> list;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("./src/day08/p3860/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
    
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            if (W == 0 && H == 0) {
                break;
            }
            map = new Node[H][W];
            path = new int[H][W];
            list = new ArrayList<>();
            for (int i = 0;i < H;i++) {
                Arrays.fill(path[i], Integer.MAX_VALUE);
            }
            path[0][0] = 0;
            for (int i = 0;i < H;i++) {
                for (int j = 0;j < W;j++) {
                    map[i][j] = new Node('.', j, i);
                }
            }
            st = new StringTokenizer(br.readLine());
            G = Integer.parseInt(st.nextToken());
            for (int i = 0;i < G;i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                // 묘지 정보 저장
                map[y][x] = new Node('G', x, y);
            }
            st = new StringTokenizer(br.readLine());
            E = Integer.parseInt(st.nextToken());
            for (int i = 0;i < E;i++) {
                st = new StringTokenizer(br.readLine());
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                // 귀신 구멍 정보 저장
                map[y1][x1] = new Node('E', x1, y1, x2, y2, t);
            }
            makeEdge();
            findPath();
        }
    }  
    public static void makeEdge() {
        for (int i = 0;i < H;i++) {
            for (int j = 0;j < W;j++) {
                // 도착지
                if (i == H - 1 && j == W - 1) continue;
                // 묘비
                else if (map[i][j] != null && map[i][j].type == 'G') continue;
                // 귀신 구멍
                else if (map[i][j] != null && map[i][j].type == 'E') {
                    list.add(map[i][j]);
                }
                else {
                    for (int k = 0;k < 4;k++) {
                        int tx = j + dx[k];
                        int ty = i + dy[k];
                        if (ty >= 0 && ty < H && tx >= 0 && tx < W && map[ty][tx].type != 'G') {
                            list.add(new Node('.', j, i, tx, ty, 1));
                        }
                    }
                }
            }
        }
    }
    public static void findPath() {
        int minus = 0;
        for (int i = 0;i < W * H;i++) {
            minus = 0;
            for (Node edge : list) {
                if (path[edge.y][edge.x] < Integer.MAX_VALUE && path[edge.y2][edge.x2] > path[edge.y][edge.x] + edge.t) {
                    path[edge.y2][edge.x2] = path[edge.y][edge.x] + edge.t;
                    minus++;
                }
            }
        }
        if (minus > 0) {
            System.out.println("Never");
        } else if (path[H - 1][W - 1] == Integer.MAX_VALUE) {
            System.out.println("Impossible");
        } else {
            System.out.println(path[H - 1][W - 1]);
        }
    }


}
class Edge {
    int start;
    int end;
    int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge [start=" + start + ", end=" + end + ", weight=" + weight + "]";
    }
}

class Node {
    char type;
    int x;
    int y;
    int x2;
    int y2;
    int t;

    public Node (char type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
    public Node (char type, int x, int y, int x2, int y2, int t) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.t = t;
    }

    @Override
    public String toString() {
        return "Node [type=" + type + ", x=" + x + ", y=" + y + ", x2=" + x2 +  ", y2=" + y2 + ", t=" + t + "]";
    }

    
}

