package p15683;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] STUDIO;
    static ArrayList<Camera> CAMERALIST;
    static int[] DY = {-1, 0, 1, 0};
    static int[] DX = {0, 1, 0, -1};
    static int MIN = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{

        System.setIn(new FileInputStream("src/p15683/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        STUDIO = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        CAMERALIST = new ArrayList<>();

        for (int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0;j < M;j++) {
                STUDIO[i][j] = Integer.parseInt(st.nextToken());
                if (STUDIO[i][j] >= 1 && STUDIO[i][j] <= 5) {
                    CAMERALIST.add(new Camera(STUDIO[i][j], i, j));
                }
            }
        }
        dfs(0, visited);
        System.out.println(MIN);


    }

    public static void dfs(int current, boolean[][] visited) {
        if (current == CAMERALIST.size()) {
            int num = findSpot(visited);
            MIN = Math.min(MIN, num);
        } else {
            Camera camera = CAMERALIST.get(current);
            // direction: 위-오른쪽-아래-왼쪽
            for (int i = 0;i < 4;i++) {
                camera.direction = i;
                boolean[][] updatedVisited = watchAll(camera, visited);
                dfs(current + 1, updatedVisited);
            }
        }
    }

    public static int findSpot(boolean[][] visited) {
        int cnt = 0;
        for (int i = 0;i < N;i++) {
            for (int j = 0;j < M;j++) {
                if (visited[i][j] == false && STUDIO[i][j] != 6) cnt++;
            }
        }
        return cnt;
    }


    public static boolean[][] watch(Camera camera, boolean[][] visited) {
        int ty = camera.y;
        int tx = camera.x;
        while (isValidPosition(ty, tx) && STUDIO[ty][tx] != 6) {
            visited[ty][tx] = true;
            ty += DY[camera.direction];
            tx += DX[camera.direction];
        }
        return visited;
    }
    
    public static boolean[][] watchAll(Camera camera, boolean[][] visited) {
        boolean[][] tmp = new boolean[N][M];
        for (int i = 0;i < N;i++) {
            for (int j = 0;j < M;j++) {
                tmp[i][j] = visited[i][j];
            }
        }
        boolean[][] result = new boolean[N][M];
        switch (camera.type) {
            case 1:
                result = watch(camera, tmp);
                break;
            // 2: (0,2), (1,3)
            case 2:
                tmp = watch(camera, tmp);
                camera.direction = (camera.direction + 2) % 4;
                result = watch(camera, tmp);
                camera.direction = (camera.direction + 2) % 4;
                break;
            // 3: (0,1), (1,2), (2,3), (3,0)
            case 3:
                tmp = watch(camera, tmp);
                camera.direction = (camera.direction + 1) % 4;
                result = watch(camera, tmp);
                camera.direction = (camera.direction + 3) % 4;
                break;
            case 4:
                tmp = watch(camera, tmp);
                camera.direction = (camera.direction + 1) % 4;
                tmp = watch(camera, tmp);
                camera.direction = (camera.direction + 2) % 4;
                result = watch(camera, tmp);
                camera.direction = (camera.direction + 1) % 4;
                break;
            case 5:
                for (int i = 0;i < 4;i++) {
                    tmp = watch(camera, tmp);
                    camera.direction = (camera.direction + 1) % 4;
                }
                result = tmp;
                break;
        }
        return result;
    }

    public static boolean isValidPosition(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}

class Camera {
    int type;
    int y;
    int x;
    int direction;

    public Camera(int t, int y, int x) {
        this.type = t;
        this.y = y;
        this.x = x;
    }

}