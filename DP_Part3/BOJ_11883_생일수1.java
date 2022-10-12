package com.day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11883_생일수1 {
	static final int MAX = 1000001; 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int[] dp = new int[MAX];
		int[] bit = new int[MAX];
		dp[3] = 3; bit[3] = 1;
		dp[5] = 5; bit[5] = 1;
		dp[6] = 3; bit[6] = 2;
		dp[8] = 8; bit[8] = 1;
		
		for (int i = 9; i < MAX; i++) {
			int diff = 8;
			int ret = bit[i-8] == 0 ? MAX : bit[i-8];
			if(ret > (bit[i-5] == 0 ? MAX : bit[i-5])) {
				diff = 5;
				ret = bit[i-5];
			}
			
			if(ret > (bit[i-3] == 0 ? MAX : bit[i-3])) {
				diff = 3;
				ret = bit[i-3];
			}
			if(ret ==0)continue;
			
			dp[i] = diff;
			bit[i] = bit[i-diff] + 1;
		}
		
		
		for (int t = 0; t < T; t++) {
			int num = Integer.parseInt(br.readLine());
			if(dp[num] == 0 ) {
				sb.append("-1\n");
			}else {
				StringBuilder temp = new StringBuilder();
				while(dp[num] != 0) {
					temp.append(dp[num]);
					num-=dp[num];
				}
				sb.append(temp.reverse() + "\n");
			}
		}
		System.out.println(sb);
	}

}
