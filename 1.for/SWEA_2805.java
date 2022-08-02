package com.day0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_2805 {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.valueOf(bf.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.valueOf(bf.readLine());
			String[][] farm = new String[N][N];
			for (int i = 0; i < N; i++) {
				farm[i] = bf.readLine().split("");
			}
			
			int result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= Math.min(i, N-i-1); j++) {
					result += Integer.valueOf(farm[i][N/2+j]);
					result += Integer.valueOf(farm[i][N/2-j]);
				}
				// 중복 지역 제거
				result -= Integer.valueOf(farm[i][N/2]);
			}
			
			System.out.println("#" + t + " " +result);
		}
	}
}
