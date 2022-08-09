package com.day0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_9229 {
	static int N, M, result;
	static int[] snacks;
	static boolean[] visited;
	static void solve(int cnt, int total) {
		if (total > M) return;
		if (cnt >=2) {
			result = result < total ? total : result;
			return;
		}
		for (int i = 0; i < snacks.length; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			solve(cnt+1, total + snacks[i]);
			visited[i] = false;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// Ver. BackTracking
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			result = Integer.MIN_VALUE;
			snacks = new int[N];
			visited = new boolean[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < snacks.length; i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}
			solve(0, 0);
			sb.append("#"+t + " "+ (result > 0 ? result : -1)+"\n");
		}
		System.out.println(sb);
		



		
//		// Ver. Brute-Force
//		int T = Integer.parseInt(br.readLine());
//		int N, M;
//		int[] snacks;
//		for (int t = 1; t <= T; t++) {
//			st = new StringTokenizer(br.readLine());
//			N = Integer.parseInt(st.nextToken());
//			M = Integer.parseInt(st.nextToken());
//			snacks = new int[N];
//			st = new StringTokenizer(br.readLine());
//			for (int i = 0; i < snacks.length; i++) {
//				snacks[i] = Integer.parseInt(st.nextToken());
//			}
//			int result = Integer.MIN_VALUE;
//			for (int i = 0; i < snacks.length; i++) {
//				for (int j = i+1; j < snacks.length; j++) {
//					int temp = snacks[i] + snacks[j];
//					if (result < temp && temp <= M) {
//						result  = temp;
//					}
//				}
//			}
//			sb.append("#"+t + " "+ (result > 0 ? result : -1)+"\n");
//		}
//		System.out.println(sb);
	}
}

