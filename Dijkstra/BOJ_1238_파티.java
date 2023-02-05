package algo0205;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1238_파티 {

    static int N, M, X;
    static List<List<Point>> adjList, reverseAdjList;
    static int[] go, come;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        reverseAdjList = new ArrayList<>();
        for (int i = 0; i <=N; i++) {
            adjList.add(new ArrayList<>());
            reverseAdjList.add(new ArrayList<>());
        }
        int a, b, cost;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());
            adjList.get(a).add(new Point(b, cost));
            reverseAdjList.get(b).add(new Point(a, cost));
        }

        go = new int[N+1];
        come = new int[N+1];
        Arrays.fill(go, Integer.MAX_VALUE);
        Arrays.fill(come, Integer.MAX_VALUE);

        dijkstra(new int[] {X, 0});
        reverDijkstra(new int[] {X, 0});

        int result = 0;
        for (int i = 1; i <= N; i++) {
            if(i == X || go[i] == Integer.MAX_VALUE || come[i] == Integer.MAX_VALUE) continue;
            result = result < go[i] + come[i] ? go[i] + come[i] : result;
        }
        System.out.println(result);
    }

    static void dijkstra(int[] now) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> o1[1] - o2[1]);
        pq.add(now);

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0], total = cur[1];
            for (Point info : adjList.get(curNode)) {
                if(go[info.x] < total + info.y) continue;
                pq.offer(new int[] {info.x, total+info.y});
                go[info.x] = total + info.y;
            }
        }
    }

    static void reverDijkstra(int[] now) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> o1[1] - o2[1]);
        pq.add(now);

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0], total = cur[1];
            for (Point info : reverseAdjList.get(curNode)) {
                if(come[info.x] < total + info.y) continue;
                pq.offer(new int[] {info.x, total+info.y});
                come[info.x] = total + info.y;
            }
        }
    }
}
