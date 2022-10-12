package com.day1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_5643_키순서 {
	static int N, M, cntA, cntB;
	static boolean[] visited;
	static List<List<Integer>> adjList, revAdjList;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			adjList = new ArrayList<>();
			revAdjList = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				adjList.add(new ArrayList<>());
				revAdjList.add(new ArrayList<>());
			}
			
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjList.get(a).add(b);
				revAdjList.get(b).add(a);
			}
			
			int result = 0;
			for (int i = 1; i <= N; i++) {
				cntA = 0;
				cntB = 0;
				visited= new boolean[N+1];
				visited[i] = true;
				DFS1(i);
				DFS2(i);
				result += (cntA+cntB == N-1 ? 1 : 0);
			}
			sb.append("#" + t + " " + result + "\n");
		}	
		System.out.println(sb);
	}
	
	static void DFS1(int now) {
		for (Integer nxt : adjList.get(now)) {
			if(visited[nxt]) continue;
			visited[nxt] = true;
			cntA++;
			DFS1(nxt);
		}
	}
	
	static void DFS2(int now) {
		for (Integer nxt : revAdjList.get(now)) {
			if(visited[nxt]) continue;
			visited[nxt] = true;
			cntB++;
			DFS2(nxt);
		}
	}

}



