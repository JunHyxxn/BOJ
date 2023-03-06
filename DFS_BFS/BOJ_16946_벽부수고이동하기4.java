package algo0307;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_16946_벽부수고이동하기4 {
    static int N, M;
    static int[][] map;
    static int[][] dxdy = {
            { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }
    };
    static int[][][] result;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        result = new int[2][N][M];
        for(int i=0; i<N; i++) {
            int[] nums = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = nums[j];
            }
        }
        int group = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] != 0 || visited[i][j]) continue;
                visited[i][j] = true;
                bfs(i,j, group++);
            }
        }

        Set<Integer> uniqueGroup;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(result[1][i][j] != 0) {
                    sb.append(0);
                    continue;
                }
                uniqueGroup = new HashSet<>();
                int total = 1;
                for (int k = 0; k < 4; k++) {
                    int dx = dxdy[k][0], dy = dxdy[k][1];
                    int nx = i+dx , ny = j + dy;
                    if(!isValid(nx, ny) || result[1][nx][ny] == 0) continue;
                    int g = result[0][nx][ny], cnt = result[1][nx][ny];
                    if(uniqueGroup.contains(g)) continue;
                    uniqueGroup.add(g);
                    total += cnt;
                }
                sb.append(total%10);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static boolean isValid(int x, int y) {
        return !(x<0 || x>= N || y <0 || y>=M);
    }

    static void bfs(int x, int y, int group) {
        Queue<Point> queue = new ArrayDeque<>();
        List<Point> sameGroup = new ArrayList<>();
        int cnt = 1;
        Point point = new Point(x, y);
        queue.offer(point);
        sameGroup.add(point);
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int dx = dxdy[i][0], dy = dxdy[i][1];
                int nx = now.x+dx , ny = now.y + dy;
                if(!isValid(nx, ny) || visited[nx][ny] || map[nx][ny] != 0) continue;
                visited[nx][ny] = true;
                cnt++;
                Point nxt = new Point(nx, ny);
                queue.offer(nxt);
                sameGroup.add(nxt);
            }
        }

        for (Point p : sameGroup) {
            result[0][p.x][p.y] = group;
            result[1][p.x][p.y] = cnt;
        }
    }

}


//// TLE
//public class BOJ_16946_벽부수고이동하기4 {
//    static int N, M;
//    static int[][] map;
//    static int[][] dxdy = {
//            {1,0}, {0,1}, {-1,0}, {0,-1}
//    };
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        map = new int[N][M];
//        for(int i=0; i<N; i++) {
//            int[] nums = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
//            for (int j = 0; j < M; j++) {
//                map[i][j] = nums[j];
//            }
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for(int i =0; i<N;i++) {
//            for(int j=0; j<M; j++) {
//                if(map[i][j] != 1) {
//                    sb.append(0);
//                    continue;
//                }
//                sb.append(bfs(i,j));
//            }
//            sb.append("\n");
//        }
//
//        System.out.println(sb);
//    }
//
//    static boolean isValid(int x, int y) {
//        return !(x<0 || x>= N || y <0 || y>=M);
//    }
//
//    static int bfs(int x, int y) {
//        Queue<Point> queue = new ArrayDeque<>();
//        boolean[][] visited = new boolean[N][M];
//        queue.offer(new Point(x,y));
//        visited[x][y] = true;
//        int cnt = 1;
//
//        while(!queue.isEmpty()) {
//            Point now = queue.poll();
//            for(int i=0; i<4; i++) {
//                int dx = dxdy[i][0], dy = dxdy[i][1];
//                int nx = now.x + dx, ny = now.y+dy;
//                if(!isValid(nx, ny) || visited[nx][ny] || map[nx][ny] != 0) continue;
//                visited[nx][ny] = true;
//                cnt++;
//                queue.offer(new Point(nx,ny));
//            }
//        }
//        return cnt;
//    }
//}
