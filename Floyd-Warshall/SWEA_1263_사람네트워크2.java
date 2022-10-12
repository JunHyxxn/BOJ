package com.day1006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1263_사람네트워크2 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] cost = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					cost[i][j] = Integer.parseInt(st.nextToken());
					if(i==j) continue;
					if(cost[i][j] == 0) cost[i][j] = 999999;
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(i == j) continue;
					for (int j2 = 0; j2 < N; j2++) {
						if(j2 == i || j2 == j) continue;
						cost[j][j2] = Math.min(cost[j][j2], cost[j][i] + cost[i][j2]);
					}
				}
			}
			int result = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int temp = 0;
				for (int j = 0; j < N; j++) {
					if(cost[i][j] == Integer.MAX_VALUE) continue;
					temp += cost[i][j];
				}
				result = Math.min(result, temp);
			}
			
			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
	}
}
