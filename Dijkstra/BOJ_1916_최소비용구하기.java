package com.day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1916 {
	static int N, M;
	static List<List<int[]>> adjList;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		adjList = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList.get(a).add(new int[] {b, w});
		}
		
		st = new StringTokenizer(br.readLine());
		int source = Integer.parseInt(st.nextToken());
		int destination = Integer.parseInt(st.nextToken());
		
		int[] D = new int[N+1];
		boolean[] visited = new boolean[N+1];
		Arrays.fill(D, Integer.MAX_VALUE);
		D[source] = 0;
		
		for (int i = 1; i <= N; i++) {
			int min = Integer.MAX_VALUE;
			int curNode = -1;
			for (int j = 1; j <= N; j++) {
				if(!visited[j] && min > D[j]) {
					min = D[j];
					curNode = j;
				}
			}
			
			visited[curNode] = true;
			if(curNode == destination) {
				System.out.println(D[curNode]);
				return;
			}
			
			for (int[] nxt : adjList.get(curNode)) {
				int nxtNode = nxt[0], cost = nxt[1];
				if(!visited[nxtNode] && D[nxtNode] > D[curNode] + cost) {
					D[nxtNode] = D[curNode] + cost;
				}
			}
		}
	}
}
