package algo0319;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_3190_뱀 {

    static int N, K, L, time;
    static int[] commands;
    static Map<String, Integer> dict2int = new HashMap<>();
    static {
        dict2int.put("D", 1);
        dict2int.put("L", -1);
    }
    static int[][] dxdy = {
            {1,0}, {0, -1}, {-1, 0}, {0,1}
    };
    static int[][] map;
    static final int SNAKE = 9;
    static final int X = 10001;
    static Deque<Point> body;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        int a;
        int b;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
        }

        commands = new int[X];
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            commands[Integer.parseInt(st.nextToken())] = dict2int.get(st.nextToken());
        }

        body = new ArrayDeque<>();
        play();
        System.out.println(time);
    }

    static int play(){
        Point now = new Point(1,1);
        map[1][1] = SNAKE;
        int dir = 3;
        body.offerFirst(now);
        time = 0;

        while(true) {
            // move
            Point head = body.peekFirst();
            Point tail = body.pollLast();
            int nx = head.x + dxdy[dir][0], ny = head.y +  dxdy[dir][1];
            if(isFinish(nx, ny)) return time++;
            body.offerFirst(new Point(nx, ny));
            if(map[nx][ny] == 1) body.offerLast(tail);
            else map[tail.x][tail.y] = 0;
            map[nx][ny] = SNAKE;
            time++;
            // change dir
            dir = ((dir+commands[time] + 4) % 4);
        }



    };

    static boolean isFinish(int x, int y) {
        return (x <= 0 || x > N || y <= 0 || y > N) || (map[x][y] == SNAKE);
    }

    static void Print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
