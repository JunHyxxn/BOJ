package com.day0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_7465 {
	/*
	 * SWEA 7465 창용 마을 무리의 개수
	 * 
	 * 유향 그래프라면 Kosaraju or Tarjan's algorithm 을 사용해 SCC 구하면된다.
	 * 무향 그래프이기 때문에 V 번의 DFS를 통해 해결할 수 있다.
	 * 
	 * 최악의 경우
	 * V * DFS => V * O(V+E) => O(V^2 + V*E) 
	 * => E := V^2 이라 한다면, O(V^3) Time 
	 * V = 100 => 1개의 TC당 100만번의 연산 -> 10개의 TC 1000만번의 연산으로 해결
	 * 따라서 V 번의 DFS로 해결 가능하다.  
	 * */
	
	static int N, M;
	static List<List<Integer>> adjList;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			adjList = new ArrayList<>();
			for (int i = 0; i <=N; i++) {
				adjList.add(new ArrayList<>());
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjList.get(a).add(b);
				adjList.get(b).add(a);
			}
			
			visited = new boolean[N+1];
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				if(visited[i])continue;
				visited[i] = true;
				DFS(i);
				cnt++;
			}
			sb.append("#" + t + " " + cnt + "\n");
		}
		System.out.println(sb);
	}
	
	static void DFS(int now) {
		for (int nxt : adjList.get(now)) {
			if(visited[nxt]) continue;
			visited[nxt] = true;
			DFS(nxt);
		}
	}

}
