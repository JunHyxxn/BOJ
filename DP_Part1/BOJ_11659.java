package com.day0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11659 {
	/*
	 * 누적합 구하기 : O(N)
	 * 쿼리 처리하기 : O(M)
	 * 
	 * 총 시간 복잡도 : O(N+M)
	 * */
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 입력값
		int[] nums = new int[N+1];
		// 누적합
		long[] dp = new long[N+1];
		st = new StringTokenizer(bf.readLine());
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			// 이전 누적합 + 해당 위치값
			dp[i] = dp[i-1] + nums[i];
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int a  = Integer.parseInt(st.nextToken());
			int b  = Integer.parseInt(st.nextToken());
			long result = dp[b] - dp[a-1];
			System.out.println(result);
		}
	}
}
