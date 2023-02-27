package algo0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_12851_숨바꼭질2 {
    static int N, K;
    static final int MAX = 100001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs();
    }

    static void bfs() {
        boolean[] visited = new boolean[MAX];
        int[] dp = new int[MAX];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(N);
        visited[N] = true;
        dp[N] = 1;
        int time = N==K ? 0 : 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            Set<Integer> syncVisited = new HashSet<>();
            for (int i = 0; i < size; i++) {
                Integer now = queue.poll();
                int minus = now-1;
                int plus = now+1;
                int mul = now*2;
                if(isValid(minus) && !visited[minus]) dp[minus] += dp[now];
                if(isValid(plus) && !visited[plus]) dp[plus] += dp[now];
                if(isValid(mul) && !visited[mul]) dp[mul] += dp[now];

                if(isValid(minus) && !visited[minus]) {
                    syncVisited.add(minus);
                }

                if(isValid(plus) && !visited[plus]) {
                    syncVisited.add(plus);
                }

                if(isValid(mul) && !visited[mul]) {
                    syncVisited.add(mul);
                }
            }
            if(dp[K] != 0) {
                System.out.println(time + "\n" + dp[K]);
                return;
            }
            for (Integer nxt : syncVisited) {
                visited[nxt] = true;
                queue.offer(nxt);
            }
            time++;
        }
    }

    static boolean isValid(int x) {
        return x >= 0 && x < MAX;
    }
}
