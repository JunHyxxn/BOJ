package com.day0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1260 {
	static int N, M, V;
	static boolean[] visited;
	static List<List<Integer>> adjList;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		adjList = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<>());
		}
		for (int j = 0; j < M; j++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList.get(a).add(b);
			adjList.get(b).add(a);			
		}
		for (int i = 1; i <= N; i++) {
			adjList.get(i).sort((a,b) -> a-b);
		}
		sb.append(V + " ");
		visited = new boolean[N+1];
		visited[V] = true;
		DFS(V);
		sb.append("\n");
		
		BFS();
		
		System.out.println(sb);
	}
	static void DFS(int cur) {
		for (Integer nxt : adjList.get(cur)) {
			if(visited[nxt]) continue;
			sb.append(nxt+" ");
			visited[nxt] = true;
			DFS(nxt);
		}
	}
	
	static void BFS() {
		visited = new boolean[N+1];
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(V);
		visited[V] = true;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			sb.append(now + " ");
			for (Integer nxt : adjList.get(now)) {
				if(visited[nxt]) continue;
				visited[nxt] = true;
				queue.offer(nxt);
			}
		}
		
		sb.append("\n");
	}
}
