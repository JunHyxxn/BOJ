package algo0106;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_13549_숨바꼭질3 {
    static int N, K;
    static Deque<Point> queue;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited =  new boolean[100000+1];

        int result = BFS(N);
        System.out.println(result);
    }
    static int BFS(int now) {
        queue = new ArrayDeque<>();
        queue.offerFirst(new Point(now, 0));
        visited[now] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.pollFirst();
            int loc = cur.x, level = cur.y;
            if(loc == K) return level;
            if(isValid(loc*2) && !visited[loc*2]) {
                visited[loc*2] = true;
                queue.offerFirst(new Point(loc*2, level));
            }
            if(isValid(loc-1) && !visited[loc-1]) {
                visited[loc-1] = true;
                queue.offerLast(new Point(loc-1, level+1));
            }

            if(isValid(loc+1) && !visited[loc+1]) {
                visited[loc+1] = true;
                queue.offerLast(new Point(loc+1, level+1));
            }
        }
        return -1;
    }


    static boolean isValid(int now) {
        return now>=0 && now <= 100000;
    }
}
