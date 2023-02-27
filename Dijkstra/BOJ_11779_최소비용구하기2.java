package algo0226;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11779_최소비용구하기2 {

    static int n, m, A, B;
    static Map<Integer, List<Point>> adjList;
    static int[] cost;
    static String[] path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        adjList = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            adjList.put(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjList.get(a).add(new Point(b, c));
        }

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        cost = new int[n+1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        path = new String[n+1];

        dijkstra();
        StringBuilder sb = new StringBuilder();
        sb.append(cost[B]).append("\n");
        sb.append(path[B].split(" ").length).append("\n");
        sb.append(path[B]);
        System.out.println(sb);
    }
    static void dijkstra() {
        PriorityQueue<Point> pq = new PriorityQueue<>((p1, p2) -> p1.y - p2.y);
        pq.offer(new Point(A, 0));
        path[A] = String.valueOf(A);
        cost[A] = 0;
        while(!pq.isEmpty()){
            Point now = pq.poll();
            if(cost[now.x] < now.y) continue;
            for (Point nxt : adjList.get(now.x)) {
                int dist = nxt.y + now.y;
                if(dist >= cost[nxt.x]) continue;
                cost[nxt.x] = dist;
                path[nxt.x] = path[now.x] + " " +String.valueOf(nxt.x);
                pq.offer(new Point(nxt.x, dist));
            }
        }
    }
}

