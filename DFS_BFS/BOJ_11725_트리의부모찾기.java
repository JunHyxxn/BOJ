package algo0115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11725_트리의부모찾 {
    static int N;
    static List<List<Integer>> adjList;
    static int[] order;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        order = new int[N+1];
        visited = new boolean[N+1];
        DFS(0, 1);

        for (int i = 2; i <= N; i++) {
            sb.append(order[i] + "\n");
        }

        System.out.println(sb);
    }
    static void DFS(int prev, int now) {
        order[now] = prev;
        for (int nxt : adjList.get(now)) {
            if(visited[nxt]) continue;
            visited[nxt] = true;
            DFS(now, nxt);
        }
    }
}
