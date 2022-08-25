package com.day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10971 {
	static int N, result;
	static boolean[] visited;
	static int[][] W;
	public static void main(String[] args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		W = new int[N+1][N+1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				W[i][j] =  Integer.parseInt(st.nextToken());
			}
		}
		
		result = Integer.MAX_VALUE;
		visited = new boolean[N+1];
		for (int i = 0; i < N; i++) {
			dfs(i, i, 0, 0);
		}
		System.out.println(result);
	}
	static void dfs(int start, int now, int cnt, int sum) {
		if(cnt == N && start == now) {
			if(result > sum) result = sum;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(visited[i] || W[now][i] == 0) continue;
			visited[i] = true;
			if(result >= sum+W[now][i]) {
				dfs(start, i,cnt+1, sum+W[now][i]);
			}
			visited[i] = false;
		}
	}
}
