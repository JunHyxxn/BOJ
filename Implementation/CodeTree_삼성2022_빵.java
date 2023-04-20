package algo0407;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class CodeTree_삼성2022_빵 {

    static int n, m, time;
    static List<Point> stores, baseCamp, players;
    static boolean[] isFinished;
    static int[][] map;
    static int[][] dxdy = {
            {-1,0}, {0,-1}, {0,1}, {1,0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        time = 1;
        stores = new ArrayList<>();
        baseCamp = new ArrayList<>();
        players = new ArrayList<>();
        isFinished = new boolean[m+1];
        map = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        stores.add(new Point(-1,-1));
        baseCamp.add(new Point(-1,-1));
        players.add(new Point(-1,-1));
        for (int i = 1; i < m+1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y =Integer.parseInt(st.nextToken());
            stores.add(new Point(x, y));
            baseCamp.add(new Point(-1,-1));
            players.add(new Point(-1,-1));
        }

        play();
        System.out.println(time-1);
    }
    static boolean isValid(int x, int y) {
        return !(x <= 0 || x > n || y <= 0 || y > n);
    }

    static boolean checkFinish() {
        for (int i = 1; i <= m; i++) {
            if(!isFinished[i]) return false;
        }
        return true;
    }
    static void play() {

        while (!checkFinish()) {
            List<Point> changeNegative = new ArrayList<>();
            for (int i = 1; i <= m; i++) {
                if(isFinished[i]) continue;
                if(i == time) {
                    for (Point negative : changeNegative) {
                        map[negative.x][negative.y] = -1;
                    }
                    findBaseCamp(i);
                    map[baseCamp.get(i).x][baseCamp.get(i).y] = -1;
                    continue;
                } else if (i > time) break;
                int dir = findShortest(i);
                Point curPlay = players.get(i);
                Point nxt = new Point(curPlay.x + dxdy[dir][0], curPlay.y + dxdy[dir][1]);
                if(nxt.x == stores.get(i).x && nxt.y == stores.get(i).y) {
                    changeNegative.add(nxt);
                    isFinished[i] = true;
                }
                players.set(i, nxt);
            }

            time++;
        }
    }

    static int findShortest(int idx) {
        Point start = players.get(idx);
        Point destination = stores.get(idx);
        boolean[][] visited = new boolean[n+1][n+1];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { start.x, start.y, -1});
        visited[start.x][start.y] = true;
        int moveDir = -1;
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0], y = now[1], firstDir = now[2];
            for (int i = 0; i < 4; i++) {
                int nx = x + dxdy[i][0], ny = y + dxdy[i][1];
                if(!isValid(nx, ny) || map[nx][ny] == -1 || visited[nx][ny]) continue;
                if(destination.x == nx && destination.y == ny) {
                    return firstDir == -1 ? i : firstDir;
                }
                visited[nx][ny] = true;
                queue.offer(new int[] {nx, ny, firstDir == -1 ? i : firstDir});
            }
        }
        return moveDir;
    }
    static void findBaseCamp(int idx) {
        Point store = stores.get(idx);
        boolean[][] visited = new boolean[n+1][n+1];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{store.x, store.y, 0});
        visited[store.x][store.y] = true;
        Point goal = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        int minDist = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0], y = now[1], dist = now[2];
            for (int i = 0; i < 4; i++) {
                int nx = x + dxdy[i][0], ny = y + dxdy[i][1];
                if(!isValid(nx, ny) || map[nx][ny] == -1 || visited[nx][ny] || dist > minDist) continue;
                if(map[nx][ny] == 1) {
                    minDist = Math.min(minDist, dist);
                    if(goal.x > nx || (goal.x == nx && goal.y > ny)) {
                        goal = new Point(nx, ny);
                    }
                }
                visited[nx][ny] = true;
                queue.offer(new int[] {nx, ny, dist+1});
            }
        }
        players.set(idx, goal);
        baseCamp.set(idx, goal);
    }
}