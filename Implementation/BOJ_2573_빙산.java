package algo0315;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573_빙산 {
    static int N, M, remain, time;
    static int[][] ice, melt;
    static boolean[][] visited;
    static int[][] dxdy = {
            {0,1}, {1,0}, {0,-1}, {-1,0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ice = new int[N][M];
        remain = 0;
        time = 0;
        int num;
        Point start = new Point();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                num = Integer.parseInt(st.nextToken());
                if(num > 0) {
                    start.x = i;
                    start.y = j;
                    remain++;
                }
                ice[i][j] = num;
            }
        }


        while(start != null) {
            start = bfs(start);
            time++;
        }
        System.out.println(remain == 0 ?  0 : time);
    }

    static boolean isValid(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= M);
    }

    static boolean isPossibleMelt(int x, int y) {
        int sea = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dxdy[i][0], ny = y + dxdy[i][1];
            if(!isValid(nx, ny)) continue;
            if(ice[nx][ny] == 0) sea++;
        }
        melt[x][y] = sea;
        return (ice[x][y] <= sea);
    }

    static Point bfs(Point start) {
        Queue<Point> queue = new ArrayDeque<>();
        visited = new boolean[N][M];
        melt = new int[N][M];
        int meltCount = 0;
        Point ret = null;
        visited[start.x][start.y] = true;
        queue.offer(start);
        if(isPossibleMelt(start.x, start.y)) {
            meltCount++;
        }

        while(!queue.isEmpty()) {
            Point now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dxdy[i][0], ny = now.y + dxdy[i][1];
                if(!isValid(nx, ny) || visited[nx][ny] || ice[nx][ny] == 0) continue;
                if(isPossibleMelt(nx, ny)) {
                    meltCount++;
                }
                else ret = new Point(nx, ny);
                visited[nx][ny] = true;
                queue.offer(new Point(nx, ny));
            }
        }
        if (remain == meltCount) {
            remain = 0;
            return null;
        }

        boolean first = true;
        visited = new boolean[N][M];
        int group = 0;
        remain -= meltCount;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ice[i][j] = ice[i][j] <= melt[i][j] ? 0 : ice[i][j]-melt[i][j];
                if( first && ice[i][j] > 0) {
                    visited[i][j] = true;
                    group++;
                    queue.offer(new Point(i, j));
                    first = false;
                }
            }
        }

        while(!queue.isEmpty()) {
            Point now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dxdy[i][0], ny = now.y + dxdy[i][1];
                if(!isValid(nx, ny) || visited[nx][ny] || ice[nx][ny] == 0) continue;
                group++;
                visited[nx][ny] = true;
                queue.offer(new Point(nx, ny));
            }
        }




        return remain == group ? ret : null;
    }

    static void Print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(ice[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.append("\n"));
    }
}
