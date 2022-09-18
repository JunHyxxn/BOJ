package com.day0917;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1005_ACMCraft {
	static int N, K, destination;
	static int[] time, indegree, dp;
	static List<List<Integer>> adjList;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			time = new int[N+1];
			indegree = new int[N+1];
			dp = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			adjList = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				adjList.add(new ArrayList<>());
			}
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				indegree[b]+=1;
				adjList.get(a).add(b);
			}
			
			destination = Integer.parseInt(br.readLine());
			
			Topology();
			sb.append(dp[destination]+"\n");
		}
		System.out.println(sb); 
	}
	
	static void Topology() {
		Queue<Integer> queue = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			if(indegree[i] ==0) {
				queue.offer(i);
			}
		}
		while(!queue.isEmpty()) {
			int now = queue.poll();
			if(now == destination) {
				dp[destination]+=time[destination];
				return;
			}
			
			for (Integer nxt : adjList.get(now)) {
				dp[nxt] = Math.max(dp[nxt], dp[now] + time[now]);
				if(--indegree[nxt] == 0) {
					queue.offer(nxt);
				}
			}
		}
	}
}
