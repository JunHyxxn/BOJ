package algo0319;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BOJ_11559_PuyoPuyo {
    static final int R = 12;
    static final int C = 6;
    static char[][] map;
    static boolean[][] visited;
    static int[][] dxdy = {
            {0,1}, {1,0}, {0,-1}, {-1,0}
    };
    static List<Point> bombs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        bombs = new ArrayList<>();

        int result = play();
        System.out.println(result);
    }
    static int play() {
        int chain = 0;
        while(true) {
            // find possible bomb
            visited = new boolean[R][C];
            bombs = new ArrayList<>();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if(map[i][j] == '.' || visited[i][j]) continue;
                    List<Point> pb = isPossibleBomb(map[i][j], new Point(i,j));
                    if(pb.size() >= 4)  bombs.addAll(pb);
                }
            }

            if(bombs.size() == 0) return chain;
            // bomb and find shift column
            Set<Integer> columns = new HashSet<>();
            for (Point bomb : bombs) {
                columns.add(bomb.y);
                map[bomb.x][bomb.y] = '.';
            }
            // shift
            for (Integer col : columns) {
                shift(col);
            }

            chain++;
        }
    }
    static boolean isValid(int x, int y) {
        return !(x<0 || x>=R || y <0 || y >=C);
    }
    static List<Point> isPossibleBomb(char color, Point now) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(now);
        visited[now.x][now.y] = true;
        List<Point> result = new ArrayList<>();
        result.add(now);
        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dxdy[i][0];
                int ny = cur.y + dxdy[i][1];
                if(!isValid(nx, ny) || visited[nx][ny] || map[nx][ny] != color) continue;
                result.add(new Point(nx, ny));
                visited[nx][ny] = true;
                queue.offer(new Point(nx,ny));
            }
        }
        return result;
    }

    static void shift(int col) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            if(map[i][col] == '.') continue;
            deque.offer(map[i][col]);
        }

        for (int i = R-1; i >= 0; i--) {
            if(deque.isEmpty()) map[i][col] = '.';
            else map[i][col] = deque.pollLast();
        }
    }
}
