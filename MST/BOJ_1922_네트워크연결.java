package com.day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1922 {
//	// Ver. Kruskal
//	static class Edge implements Comparable<Edge>{
//		int from, to, weight;
//
//		public Edge(int from, int to, int weight) {
//			this.from = from;
//			this.to = to;
//			this.weight = weight;
//		}
//
//		@Override
//		public int compareTo(Edge o) {
//			return this.weight - o.weight;
//		}
//	}
//	static int N, M;
//	static int[] parents;
//	public static void main(String[] args) throws IOException{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
//		N = Integer.parseInt(br.readLine());
//		M = Integer.parseInt(br.readLine());
//		Edge[] edges = new Edge[M];
//		for (int i = 0; i < M; i++) {
//			st = new StringTokenizer(br.readLine());
//			int a = Integer.parseInt(st.nextToken());
//			int b = Integer.parseInt(st.nextToken());
//			int w = Integer.parseInt(st.nextToken());
//			edges[i] = new Edge(a, b, w);
//		}
//		make();
//		Arrays.sort(edges);
//		int cnt = 0;
//		int result = 0;
//		for (Edge edge : edges) {
//			if(union(edge.from, edge.to)) {
//				cnt++;
//				result+= edge.weight;
//			}
//			if(cnt == N-1) {
//				System.out.println(result);
//				return;
//			}
//		}
//	}
//	
//	static void make() { 
//		parents = new int[N+1];
//		for (int i =1; i <= N; i++) {
//			parents[i] = i;
//		}
//	}
//	
//	static int find(int a) {
//		if(parents[a] == a) return a;
//		return parents[a] = find(parents[a]);
//	}
//	static boolean union(int a, int b) {
//		int aRoot = find(a);
//		int bRoot = find(b);
//		if(aRoot == bRoot) return false;
//		parents[Math.max(aRoot, bRoot)] = Math.min(aRoot, bRoot);
//		return true;
//	}
	
	// Prim
	static int V, E;
	static long result;
	static int[][] weights;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());
        weights = new int[V+1][V+1];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b =Integer.parseInt(st.nextToken()), w =Integer.parseInt(st.nextToken());
            weights[a][b] = w;
            weights[b][a] = w;
        }

        Prim();
        sb.append(result+ "\n");
		System.out.println(sb);
	}
	
	static void Prim() {
		boolean[] visited = new boolean[V+1];
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		pq.offer(new int[] {1,0});
		int cnt = 0;
		while(!pq.isEmpty()) {
			int[] info = pq.poll();
			int nowV = info[0]; 
			int nowW = info[1];
			if(visited[nowV]) continue;
			visited[nowV] = true;
			result += nowW;
			cnt++;
			if(cnt == V) {
				return;
			}
			for (int i = 1; i <= V; i++) {
				if(weights[nowV][i] == 0 || visited[i]) continue;
				pq.offer(new int[] {i, weights[nowV][i]});
			}
		}
		
	}
}
