package com.day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2961 {
	/**
	 * BOJ - 2961 도영이가 만든 맛있는 음식 Silver Ⅱ
	 * */
	static int N, result;
	static int[] sourArr, bitterArr, dp;
	
	static void solve(int idx, int sour, int bitter, int state) {
		if(idx==N) {
			if(state == 0) return;
			int temp = Math.abs(sour - bitter);
			dp[state] = temp;
			result = result > temp ? temp : result;
			return;
		}
		if(dp[state] != 0) return;
		
		solve(idx+1, sour*sourArr[idx] , bitter+bitterArr[idx], (state|1<<idx));
		solve(idx+1, sour, bitter, state);
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		result = Integer.MAX_VALUE;
		sourArr = new int[N];
		bitterArr = new int[N];
		dp = new int[(1<<N)];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			sourArr[i] = Integer.parseInt(st.nextToken());
			bitterArr[i] = Integer.parseInt(st.nextToken());
		}
		
		solve(0,1,0, 0);
		sb.append(result);
		System.out.println(sb);
		
	}
}
