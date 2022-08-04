package com.day0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2001 {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int result = -1;
			for (int i = 0; i <= N-M; i++) {
				for (int j = 0; j <= N-M; j++) {
					int temp = 0;
					for (int k = 0; k < M; k++) {
						for (int l = 0; l < M; l++) {
							temp += map[i+k][j+l];
						}
					}
					result = result < temp ? temp : result;
				}
			}
			sb.append("#"+ t + " " + result + "\n");
		}
		System.out.println(sb);
	}
}
