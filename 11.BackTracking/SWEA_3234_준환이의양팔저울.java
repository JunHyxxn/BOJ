package com.day0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3234 {
	static int N;
	static int[] weights;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			weights = new int[N];
			dp = new int[1<<N][9000];
			for (int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}
			
			int cnt = solve(0,0,0,0);
			sb.append("#" + t + " " +cnt + "\n");
		}
		System.out.println(sb);
	}
	
	static int solve(int idx, int left, int right, int state) {
		if(idx == N) {
			return 1;
		}
		if(dp[state][left] != 0) {
			return dp[state][left];
		}
		int res = 0;
		for (int i = 0; i < N; i++) {
			if((state & (1<<i)) != 0) continue;
			if (left >= right + weights[i]) {
				res += solve(idx+1, left, right+weights[i], state|(1<<i));
			}
			
			res += solve(idx+1, left+weights[i], right, state|(1<<i));
		}
		
		dp[state][left] = res;
		return res;
	}
}


