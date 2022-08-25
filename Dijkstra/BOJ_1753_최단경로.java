package com.day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753 {
	static int V, E, K, Dist[];
	static boolean[] visited;
	static List<Map<Integer, Integer>> adjList;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		adjList = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			adjList.add(new HashMap<>());
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			if(adjList.get(a).get(b) == null) {
				adjList.get(a).put(b,  w);
			}else {
				adjList.get(a).put(b, Math.min(w,adjList.get(a).get(b)));
			}
		}
		
		// Ver. PriorityQueue
		visited = new boolean[V+1];
		Dist = new int[V+1];
		Arrays.fill(Dist, Integer.MAX_VALUE);
		Dist[K] = 0;
		dijkstra();
		for (int i = 1; i <= V; i++) {
			sb.append((Dist[i]==Integer.MAX_VALUE ? "INF" : Dist[i])+"\n");
		}
		System.out.println(sb);
	}
	
	static void dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		pq.add(new int[] {K, 0});
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int curNode = cur[0], dist = cur[1];
			if(visited[curNode]) continue;
			visited[curNode] = true;
			for (Entry<Integer, Integer> entry : adjList.get(curNode).entrySet()) {
				int nxtNode = entry.getKey(), cost = entry.getValue();
				if(visited[nxtNode]) continue;
				if(Dist[nxtNode] > dist + cost) { // Prim과의 차이점
					Dist[nxtNode] = dist+cost;
					pq.add(new int[] {nxtNode, dist + cost});
				}
			}
		}
	}
	
}
