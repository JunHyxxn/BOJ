package com.day0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_3124_Prim {
	static int V, E;
	static long result;
	static List<List<int[]>> adjList;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			adjList = new ArrayList<>();
			for (int i = 0; i <= V; i++) {
				adjList.add(new ArrayList<>());
			}
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()), b =Integer.parseInt(st.nextToken()), w =Integer.parseInt(st.nextToken());
				adjList.get(a).add(new int[] {b, w});
				adjList.get(b).add(new int[] {a, w});
			}
			result = 0;
			Prim();
			sb.append("#" + t + " " + result+ "\n");
		}
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
			for (int[] vertex : adjList.get(nowV)) {
				int nxt = vertex[0], cost = vertex[1];
				if(visited[nxt]) continue;
				pq.offer(new int[] {nxt, cost});
			}
		}
	}
	
}
