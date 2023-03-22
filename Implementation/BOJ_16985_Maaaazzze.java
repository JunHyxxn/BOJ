package algo0322;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class BOJ_16985_Maaaazzze {
    static final int N = 5;
    static int result;
    static int[][][] origin, rotated;
    static int[] order, rotateCnt;
    static int[][] move = {
            {1,0,0}, {0, 1, 0}, {0, 0, 1}, {0, -1, 0}, {0, 0, -1}, {-1, 0, 0}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        origin = new int[N][N][N];
        rotated = new int[N][N][N];
        order = new int[N];
        rotateCnt = new int[N];
        result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                origin[i][j] = Arrays.stream(br.readLine().split(" " )).mapToInt(Integer::valueOf).toArray();
            }
        }

        stackPerm(0, 0);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }
    static boolean isValid(int x, int y, int z) {
        return !(x<0 || x>=N || y <0 || y >=N || z < 0 || z >= N);
    }

    static boolean isFinish(int z, int x, int y) {
        return ((z == (N-1)) && (x == (N-1)) && (y == (N-1)));
    }
    static int[][] rotate(int[][] space, int cnt) {
        int[][] rotated = new int[N][N];
//        Print(space);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int x = i, y = j;
                int time = cnt;
                while(time > 0) {
                    int t = x;
                    x = y;
                    y = N - 1 - t;
                    time--;
                }
                rotated[x][y] = space[i][j];
            }
        }
        return rotated;
    }

    static void stackPerm(int depth, int state) {
        if(result == 12) return;
        if(depth == N) {
            // stack all cube
            stackRotateCnt(0);

            return;
        }
        for (int i = 0; i < N; i++) {
            if((state & (1<<i)) != 0) continue;
            order[depth] = i;
            stackPerm(depth+1, (state|(1<<i)));
        }
    }
    static void stackRotateCnt(int depth) {
        if(result == 12) return;
        if (depth == N) {

            for (int i = 0; i < N; i++) {
                int idx = order[i];
                int cnt = rotateCnt[i];
                rotated[i] = rotate(origin[idx], cnt);
            }
            if(rotated[0][0][0] == 0 || rotated[N-1][N-1][N-1] == 0) return;
            bfs();
            return;
        }
        for (int i = 0; i < N; i++) {
            rotateCnt[depth] = i;
            stackRotateCnt(depth+1);
        }
    }

    static void bfs() {
        Queue<List<Integer>> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][N][N];
        queue.offer(List.of(0,0,0,0));
        visited[0][0][0] = true;

        while(!queue.isEmpty()) {
            List<Integer> now = queue.poll();
            int layer = now.get(0), x = now.get(1), y = now.get(2), time = now.get(3);
            if(isFinish(layer, x, y)) {
                result = Math.min(result, time);
                return;
            }
            if(time >= result) return;
            for (int i = 0; i < 6; i++) {
                int nxtLayer = layer + move[i][0];
                int nx = x + move[i][1], ny = y + move[i][2];
                if(!isValid(nx, ny, nxtLayer) || visited[nxtLayer][nx][ny] || rotated[nxtLayer][nx][ny] == 0) continue;
                visited[nxtLayer][nx][ny] = true;
                queue.offer(List.of(nxtLayer, nx, ny, time+1));
            }
        }
    }
}
