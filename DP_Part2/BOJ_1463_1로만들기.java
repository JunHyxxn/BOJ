package com.day0929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1463_1로만들기 {	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N <= 3) {
			System.out.println(N ==1 ? 0 : 1);
			return;
		}
		int[] dp = new int[N+1];
		dp[2] = 1; dp[3] =1;
		for (int i = 4; i <= N; i++) {
			int min = Integer.MAX_VALUE;
			if(i%2 ==0) {
				min = Math.min(min, dp[i/2]+1);
			}
			if(i%3 ==0) {
				min = Math.min(min, dp[i/3]+1);
			}
			
			dp[i] = Math.min(min, dp[i-1]+1);
		}
		
		System.out.println(dp[N]);
	}
}
