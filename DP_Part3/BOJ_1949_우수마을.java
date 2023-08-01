package day0801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1949 {

    private static int N;
    private static int[] weights, visited;
    private static Map<Integer, List<Integer>> adjList;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weights = new int[N + 1];
        adjList = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
            adjList.put(i, new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        visited = new int[N + 1];
        visited[1] = 1;
        dp = new int[N + 1][2];
        dfs(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    static void dfs(int now) {
        int cnt = 0;
        for (Integer nxt : adjList.get(now)) {
            if (visited[nxt] != 0) {
                continue;
            }
            visited[nxt] = visited[now] + 1;
            cnt++;
            dfs(nxt);
        }

        if (cnt == 0) { // Leaf Node
            // Save Minimum Sub Tree Solution
            dp[now][1] = weights[now];
            dp[now][0] = 0;
        } else { // Solution
            /**
             * now is current Sub Tree's Root Node.
             * dp (now, 0) means max value when this root node is not selected.
             * dp (now, 1) means max value when this root node is selected.
             */
            for (Integer child : adjList.get(now)) {
                if (visited[child] < visited[now]) { // parent node
                    continue;
                } else {
                    dp[now][0] += Math.max(dp[child][0], dp[child][1]);
                    dp[now][1] += dp[child][0];
                }
            }
            dp[now][1] += weights[now];
        }
    }

}
