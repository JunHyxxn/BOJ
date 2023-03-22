package algo0320;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BOJ_1938_통나무옮기기 {

    static int N;
    static char[][] map;
    static boolean[][][] visited;
    static int[][] dxdy = {
            {0,1}, {1,0}, {0,-1}, {-1,0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        visited = new boolean[2][N][N];
        List<Integer> source = findSource();
        int result = bfs(source);
        System.out.println(result);
    }

    public static boolean isValid(int x, int y) {
        return !(x <0 || x >= N || y< 0 || y>= N);
    }
    public static List<Integer> findSource(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] =='B') {
                    int state = 9;
                    List<Integer> result = new ArrayList<>();
                    if(isValid(i, j+1) && map[i][j+1] == 'B') state = 0;
                    else if(isValid(i+1, j) && map[i+1][j] == 'B') state = 1;
                    result.add(state);
                    result.add(state == 0 ? i : i+1);
                    result.add(state == 0 ? j+1 : j);
                    return result;
                }
            }
        }
        return null;
    }

    public static boolean isMove(int dir, int state, int x, int y) {
        if (state ==0) {
            if( dir == 0 || dir == 2) {
                return isValid(x+ dxdy[dir][0] , y + (dxdy[dir][1]*2)) && map[x+ dxdy[dir][0]][y + (dxdy[dir][1]*2)] != '1';
            } else {
                int nx = x + dxdy[dir][0], ny = y + dxdy[dir][1];
                boolean isPossible = true;
                for (int i = -1; i <= 1; i++) {
                    if(!isValid(nx, ny+i) || map[nx][ny+i] == '1') {
                        isPossible = false;
                        break;
                    }
                }
                return isPossible;
            }
        } else {
            if ( dir == 1 || dir == 3) {
                return isValid(x + (dxdy[dir][0] * 2), y+dxdy[dir][1]) && map[x + (dxdy[dir][0] * 2)][y+dxdy[dir][1]] != '1';
            } else {
                int nx = x + dxdy[dir][0], ny = y + dxdy[dir][1];
                boolean isPossible = true;
                for (int i = -1; i <= 1; i++) {
                    if(!isValid(nx+i, ny) || map[nx+i][ny] == '1') {
                        isPossible = false;
                        break;
                    }
                }
                return isPossible;
            }
        }
    }

    public static boolean isTurn(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if(i == 0 && j ==0) continue;
                if(!isValid(x+i, y+j) || map[x+i][y+j] == '1') return false;
            }
        }
        return true;
    }

    public static boolean isFinish(int state, int x, int y) {
        if(state == 0) {
            for (int i = -1; i <= 1; i++) {
                if(!isValid(x, y+i) || map[x][y+i] != 'E') return false;
            }
        } else {
            for (int i = -1; i <= 1; i++) {
                if(!isValid(x+i, y) || map[x+i][y] != 'E') return false;
            }
        }
        return true;
    }

    public static int bfs(List<Integer> source) {
        Queue<List<Integer>> queue = new ArrayDeque<>();
        source.add(0);
        queue.offer(source);
        visited[source.get(0)][source.get(1)][source.get(2)] = true;
        while (!queue.isEmpty()) {
            List<Integer> cur = queue.poll();
            int x = cur.get(1), y = cur.get(2);
            int time = cur.get(3);
            int state = cur.get(0);
            if(map[x][y] == 'E' && isFinish(state, x, y)) return time;
            for (int i = 0; i < 5; i++) {
                if(i < 4) { // move
                    if(!isMove(i, state, x, y)) continue;
                    int nx = x + dxdy[i][0];
                    int ny = y + dxdy[i][1];
                    if(visited[state][nx][ny]) continue;
                    visited[state][nx][ny] = true;
                    queue.offer(List.of(new Integer[] { state, nx, ny, time+1 }));
                } else {
                    if(!isTurn(x, y) || visited[state^1][x][y]) continue;
                    visited[state^1][x][y] = true;
                    queue.offer(List.of(new Integer[] {state^1, x, y, time+1}));
                }
            }
        }
        return 0;
    }
}
