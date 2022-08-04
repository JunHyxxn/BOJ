package com.day0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11660 {
	/*
	 * 사각형 구간합 구히기 : O(N^2)
	 * 쿼리 처리하기 : O(M)
	 * 
	 * 총 시간복잡도 : O(N^2 + M) 
	 * */
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N+1][N+1];
		long[][] dp = new long[N+1][N+1];
		// 입력 받기
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 1; j <= N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 누적합 구하기. - O(N^2)
		// 첫 번째 행, 첫 번째 열은 단순히 누적 합 기록 
		for (int i = 1; i <= N; i++) {
			dp[1][i] = dp[1][i-1] + board[1][i];
			dp[i][1] = dp[i-1][1] + board[i][1];			
		}
		// 사각형 누적합 구하기.
		for (int i = 2; i <= N; i++) {
			for (int j = 2; j <= N; j++) {
				// 현재까지 누적합 = 행까지 누적합 + 열까지 누적합 - 대각선 위의 사각형까지의 누적합 + 자기자신
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + board[i][j];
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			// 결과 = x2, y2까지의 사각형 - 윗 부분의 직사각형 - 왼쪽 부분의 직사각형 + 겹치는 사각형
			// O(M)
			long result = dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1];
			System.out.println(result);
		}
	}
}
