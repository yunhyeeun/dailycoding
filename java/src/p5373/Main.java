package p5373;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T, N;
    static Order[] ORDERLIST;
    static Cube CUBE;
    public static void main(String[] args) throws Exception{

        System.setIn(new FileInputStream("src/p5373/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for (int t = 0;t < T;t++) {
            CUBE = new Cube();
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            ORDERLIST = new Order[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0;i < N;i++) {
                char[] tmp = st.nextToken().toCharArray();
                boolean c = false;
                if (tmp[1] == '+') c = true;
                ORDERLIST[i] = new Order(tmp[0], c);
            }
            for (int i = 0;i < N;i++) {
                play(ORDERLIST[i]);
            }
            System.out.println("UP");
            for (int i = 0;i < 3;i++) {
                System.out.println(CUBE.spaces[0][i]);
            }
            System.out.println();
            System.out.println("LEFT");
            for (int i = 0;i < 3;i++) {
                System.out.println(CUBE.spaces[4][i]);
            }
            System.out.println();
            System.out.println("DOWN");
            for (int i = 0;i < 3;i++) {
                System.out.println(CUBE.spaces[1][i]);
            }
            System.out.println();
            System.out.println("RIGHT");
            for (int i = 0;i < 3;i++) {
                System.out.println(CUBE.spaces[5][i]);
            }
            System.out.println();
            System.out.println();
        }
    }

    public static void play(Order order) {
        if (order.clockwise) {
            rotate(order.space);
        } else {
            for (int i = 0;i < 3;i++) {
                rotate(order.space);
            }
        }
    }

    public static void putColors(int space) {
        Stack<Character>result = new Stack<>();
        for (int i = 0;i < 3;i++) {
            result.add(CUBE.spaces[space][0][i]);
        }
        for (int i = 0;i < 3;i++) {
            result.add(CUBE.spaces[space][i][2]);
        }
        for (int i = 0;i <3;i++) {
            result.add(CUBE.spaces[space][2][2 - i]);
        }
        for (int i = 0;i < 3;i++) {
            result.add(CUBE.spaces[space][2 - i][0]);
        }
        if (space % 2 == 0) {
            for (int i = 0;i < 3;i++) {
                CUBE.spaces[space][0][2 - i] = result.pop();
            }
            for (int i = 0;i < 3;i++) {
                CUBE.spaces[space][i][0] = result.pop();
            }
            for (int i = 0;i < 3;i++) {
                CUBE.spaces[space][2][i] = result.pop();
            }
            for (int i = 0;i < 3;i++) {
                CUBE.spaces[space][2 - i][2] = result.pop();
            }
        } else {
            for (int i = 0;i < 3;i++) {
                CUBE.spaces[space][2][i] = result.pop();
            }
            for (int i = 0;i < 3;i++) {
                CUBE.spaces[space][2 - i][2] = result.pop();
            }
            for (int i = 0;i < 3;i++) {
                CUBE.spaces[space][0][2 - i] = result.pop();
            }
            for (int i = 0;i < 3;i++) {
                CUBE.spaces[space][i][0] = result.pop();
            }
        }
    }
 
    public static void rotate(char space) {
        char[] tmp1 = new char[3];
        char[] tmp2 = new char[3];
        switch(space) {
            //U+ : F[0] -> L[0], L[0] -> B[0], B[0] -> R[0], R[0] -> F[0]
            // 2 -> 4 -> 3-> 5-> 2
            case 'U':
                putColors(0);
                for (int i = 0;i < 3;i++) {
                    tmp1[i] = CUBE.spaces[4][0][i];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[4][0][i] = CUBE.spaces[2][0][i];
                }
                for (int i = 0;i < 3;i++) {
                    tmp2[i] = CUBE.spaces[3][0][i];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[3][0][2 - i] = tmp1[i];
                }
                for (int i = 0;i < 3;i++) {
                    tmp1[i] = CUBE.spaces[5][0][i];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[5][0][2 - i] = tmp2[i];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[2][0][i] = tmp1[i];
                }
                break;

            // D+ : F[2] -> L[2], L[2] -> B[2], B[2] -> R[2], R[2] -> F[2]
            case 'D':
                putColors(1);
                for (int i = 0;i < 3;i++) {
                    tmp1[i] = CUBE.spaces[5][2][i];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[5][2][i] = CUBE.spaces[2][2][i];
                }
                for (int i = 0;i < 3;i++) {
                    tmp2[i] = CUBE.spaces[3][2][i];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[3][2][2 - i] = tmp1[i];
                }
                for (int i = 0;i < 3;i++) {
                    tmp1[i] = CUBE.spaces[4][2][i];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[4][2][2 - i] = tmp2[i];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[2][2][i] = tmp1[i];
                }
                break;

            //F+ : U[2] -> R[][0], R[][0] -> D[2], D[2] -> L[][2], L[][2] -> U[2]
            case 'F':
                putColors(2);
                for (int i = 0;i < 3;i++) {
                    tmp1[i] = CUBE.spaces[5][i][0];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[5][i][0] = CUBE.spaces[0][2][i];
                }
                for (int i = 0;i < 3;i++) {
                    tmp2[i] = CUBE.spaces[1][2][i];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[1][2][2 - i] = tmp1[i];
                }
                for (int i = 0;i < 3;i++) {
                    tmp1[i] = CUBE.spaces[4][i][2];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[4][i][2] = tmp2[i];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[0][2][2 - i] = tmp1[i];
                }
                break;

            //B+ : U[0] -> R[][2], R[][2] -> D[0], D[0] -> L[][0], L[][0] -> U[0]
            case 'B':
                putColors(3);
                for (int i = 0;i < 3;i++) {
                    tmp1[i] = CUBE.spaces[0][0][i];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[0][0][i] = CUBE.spaces[5][i][2];
                }
                for (int i = 0;i < 3;i++) {
                    tmp2[i] = CUBE.spaces[4][i][0];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[4][2 - i][0] = tmp1[i];
                }
                for (int i = 0;i < 3;i++) {
                    tmp1[i] = CUBE.spaces[1][0][i];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[1][0][i] = tmp2[i];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[5][2 - i][2] = tmp1[i];
                }
                break;

            //L+ : U[][0] -> F[][0], F[][0] -> D[][0], D[][0] -> B[][0](reverse), B[][0] -> U[][0](reverse)
            case 'L':
                putColors(4);
                for (int i = 0;i < 3;i++) {
                    tmp1[i] = CUBE.spaces[2][i][0];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[2][i][0] = CUBE.spaces[0][i][0];
                }
                for (int i = 0;i < 3;i++) {
                    tmp2[i] = CUBE.spaces[1][i][0];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[1][2 - i][0] = tmp1[i];
                }
                for (int i = 0;i < 3;i++) {
                    tmp1[i] = CUBE.spaces[3][i][0];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[3][i][0] = tmp2[i];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[0][2 - i][0] = tmp1[i];
                }
                break;

            // R+ : U[][2] -> F[][2], F[][2] -> D[][0], D[][0] -> B[][0](reverse), B[][0] -> U[][2](reverse)
            case 'R':
                putColors(5);
                for (int i = 0;i < 3;i++) {
                    tmp1[i] = CUBE.spaces[3][i][2];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[3][2 - i][2] = CUBE.spaces[0][i][2];
                }
                for (int i = 0;i < 3;i++) {
                    tmp2[i] = CUBE.spaces[1][i][2];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[1][i][2] = tmp1[i];
                }
                for (int i = 0;i < 3;i++) {
                    tmp1[i] = CUBE.spaces[2][i][2];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[2][2 - i][2] = tmp2[i];
                }
                for (int i = 0;i < 3;i++) {
                    CUBE.spaces[0][i][2] = tmp1[i];
                }
                break;
        }
    }
}
class Cube {

    char[][][] spaces = new char[6][3][3];

    public Cube () {
        char[] colors = {'w', 'y', 'r', 'o', 'g', 'b'};
        for (int i = 0;i < 6;i++) {
            for (int j = 0; j < 3;j++) {
                for (int k = 0;k < 3;k++) {
                    this.spaces[i][j][k] = colors[i];
                }
            }
        }
    }
}
class Order {
    // U: 위, D: 아래, F: 앞, B: 뒤, L: 왼, R: 오
    char space;
    boolean clockwise;

    public Order (char s, boolean c) {
        this.space = s;
        this.clockwise = c;
    }
}