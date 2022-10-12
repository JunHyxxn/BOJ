package com.day1006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3307_최장증가부분수열 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			int[] number = new int[N];
			for (int i = 0; i < N; i++) {
				number[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] dp = new int[N];
			int result=  Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				dp[i] = 1;
				for (int j = 0; j < N; j++) {
					if(number[i] > number[j]) {
						dp[i] = Math.max(dp[i], dp[j]+1);
					}
				}
				result = Math.max(result, dp[i]);
			}
			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
	}
}
