package com.day0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9252_LCS2 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();
		int r = str1.length;
		int c = str2.length;
		int[][] dp = new int[r+1][c+1];
		
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				if(str1[i-1] == str2[j-1]) dp[i][j] = dp[i-1][j-1]+1;
				else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		int cnt = dp[r][c];
		System.out.println(cnt);
		StringBuilder sb = new StringBuilder();
		int x = r, y = c;
		while(cnt > 0) {
			if(dp[x][y] == dp[x-1][y]) {
				x -=1;
				continue;
			}
			if(dp[x][y] == dp[x][y-1]) {
				y-=1;
				continue;
			}
			x-=1; y-=1;
			sb.insert(0, str1[x]);
			cnt--;
		}
		System.out.println(sb);
	}

}

