package com.day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1010 {
	/**
	 * BOJ - 1010 다리 놓기 Silver Ⅴ 
	 * 가장 빠르게 풀기 위해서는 dp 방식 사용 가능하다.
	 * */
	
	// Ver dp
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st  = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			// M 개 중에서 N 개만 뽑아낸다면 매핑해주지 않아도 됨.
			int K = Math.min(M-N, N);
			
			dp = new int[M+1][K+1];
					
					
//			long total = 1;
//			for (int i = M; i > M-K; i--) {
//				total *= i;
//			}
//			for (int i = 1; i <= K; i++) {
//				total/=i;
//			}
//			sb.append(total+"\n");
			
			sb.append(solve(M, K));
		}
		System.out.println(sb);
	}
	
	// ver. DP => nCr = (n-1) C r + (n-1) C (r-1)
	static int solve(int n, int r) {
		if (n == r) {
			dp[n][r] = 1;
			return dp[n][r];
		} else if (n < r) {
			return 0;
		}
		
		if(r == 1) {
			dp[n][1] = n;
			return n;
		}
		
		dp[n][r] = solve(n-1, r) + solve(n-1, r-1);
		return dp[n][r];
	}
}
