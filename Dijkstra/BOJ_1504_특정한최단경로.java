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

public class BOJ_1504_특정한최단경로 {

    static int N, E, v1, v2;
    static int[] dist;
    static List<List<Point>> adjList;
    static class State implements Comparable<State> {
        int cur;
        int total;

        State(int cur, int total)  {
            this.cur = cur;
            this.total = total;
        }

        @Override
        public int compareTo(State o) {
            return total - o.total;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); E = Integer.parseInt(st.nextToken());
        dist = new int[N+1];

        adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        int a, b, c;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            adjList.get(a).add(new Point(b, c));
            adjList.get(b).add(new Point(a, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
        long result1 = 0;
        result1 += dijkstra(1, v1);
        result1 += dijkstra(v1, v2);
        result1 += dijkstra(v2, N);

        long result2 = 0;
        result2 += dijkstra(1, v2);
        result2 += dijkstra(v2, v1);
        result2 += dijkstra(v1, N);

        long result = Math.min(result1, result2);
        System.out.println(result < Integer.MAX_VALUE ? result : -1);
    }
    static int dijkstra(int now, int destination) {
        PriorityQueue<State> pq = new PriorityQueue<State>();
        pq.offer(new State(now, 0));
        boolean[] visited = new boolean[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[now] = 0;
        while(!pq.isEmpty()) {
            State curNode = pq.poll();
            if(curNode.cur == destination) {
                return dist[destination];
            }
            if(visited[curNode.cur]) continue;
            visited[curNode.cur] = true;

            for (Point nxt : adjList.get(curNode.cur)) {
                if(visited[nxt.x] || dist[nxt.x] < curNode.total + nxt.y) continue;
                dist[nxt.x] = curNode.total + nxt.y;
                pq.offer(new State(nxt.x, dist[nxt.x]));
            }
        }
        return dist[destination];
    }
}

