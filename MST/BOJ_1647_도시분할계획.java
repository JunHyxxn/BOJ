package day0710;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1647_도시분할계획 {
    static int N, M, cnt, lastCost, totalCost;
    static Map<Integer, List<Integer>> adjList;
    static int[] groups;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new HashMap<>();
        groups = new int[N+1];
        cnt = 1;
        lastCost = 0;
        totalCost = 0;
        for (int i = 1; i <= N; i++) {
            adjList.put(i, new ArrayList<>());
            groups[i] = i;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.offer(new int[] {a, b, c});
        }

        while(!pq.isEmpty()) {
            if(cnt == N) break;
            int[] edge = pq.poll();
            union(edge[0], edge[1], edge[2]);
        }

        System.out.println(totalCost - lastCost);
    }

    static int findRoot(int x) {
        // 탐색 중 경로 갱신을 통해 효율적으로 탐색하도록 만들기
        if(groups[x] != x) return groups[x] = findRoot(groups[x]);
        return x;
    }

    static void union(int a, int b, int cost) {
        int aRoot = findRoot(a);
        int bRoot = findRoot(b);
        if(aRoot == bRoot) return;
        cnt++; // 선택한 간선 개수 + 1
        totalCost += cost; // 총 비용
        lastCost = Math.max(lastCost, cost); // 최대 간선 비용 갱신
        groups[Math.max(aRoot, bRoot)] = Math.min(aRoot, bRoot);
    }


}
