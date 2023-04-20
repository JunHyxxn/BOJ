package algo0404;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14891_톱니바퀴 {
    static int[][] chain;
    static int K;
    static int[] isRotate;
    static Point[] adjacentPoints;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        chain = new int[4][8];
        adjacentPoints = new Point[4];
        for (int i = 0; i < 4; i++) {
            chain[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::valueOf).toArray();
            adjacentPoints[i] = new Point(6, 2);
        }

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken());
            isRotate = new int[4];
            isRotate[start] = dir;
            findLeft(start, dir);
            findRight(start, dir);
            for (int j = 0; j < 4; j++) {
                if(isRotate[j] == 1) {
                    adjacentPoints[j].x = (adjacentPoints[j].x - 1 + 8) % 8;
                    adjacentPoints[j].y = (adjacentPoints[j].y - 1 + 8) % 8;
                } else if (isRotate[j] == -1) {
                    adjacentPoints[j].x = (adjacentPoints[j].x + 1) % 8;
                    adjacentPoints[j].y = (adjacentPoints[j].y + 1) % 8;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < 4; i++) {
            int root = (adjacentPoints[i].y - 2 + 8)%8;
            result += chain[i][root] == 1 ? (1<<i) : 0;
        }
        System.out.println(result);
    }

    public static void findLeft(int now, int dir) {
        if(now == 0 || chain[now-1][adjacentPoints[now-1].y] == chain[now][adjacentPoints[now].x]) return;
        isRotate[now-1] = -1*dir;
        findLeft(now-1, -1*dir);
    }

    public static void findRight(int now, int dir) {
        if(now == 3 || chain[now][adjacentPoints[now].y] == chain[now+1][adjacentPoints[now+1].x]) return;
        isRotate[now+1] = -1*dir;
        findRight(now+1, -1*dir);
    }
}
