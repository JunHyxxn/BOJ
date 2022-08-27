package com.day0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_15172_Hunter {
	static int N, CNT, M, result;
	static int[] orders;
	static int[][] map, locInfo;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			CNT = 0;
			map = new int[N][N];
			for (int i = 0; i <  N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					CNT = Math.max(CNT,  map[i][j]);
				}
			}
			M = CNT*2+1;
			locInfo = new int[M][2];
			// start location
			locInfo[0] = new int[]{0,0};
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] > 0) {
						locInfo[map[i][j]] = new int[] {i,j};
					}else if (map[i][j] <0) {
						locInfo[-1*map[i][j] + CNT] = new int[] {i,j};
					}
				}
			}
			
			orders = new int[M];
			result = Integer.MAX_VALUE;
			perm(1,0);
			sb.append("#" + t + " " +result+ "\n");
		}
		System.out.println(sb);
	}
	
	static int getDist(int[] x, int[] y) {
		return Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]);
	}
	static void perm(int idx, int state) {
		if(idx == M) {
			int total = 0;
			for (int i = 1; i < M; i++) {
				total += (getDist(locInfo[orders[i-1]], locInfo[orders[i]]));
			}
			result = Math.min(result, total);
			return;
		}
		
		for (int i = 1; i < M; i++) {
			if((state & (1<<i)) != 0) continue;
			if(i <= M/2) {
				orders[idx] = i;
				perm(idx+1, state|(1<<i));
			}else if (i > M/2) {
				if((state&(1<<(i-CNT))) != 0) {
					orders[idx] = i;
					perm(idx+1, state|(1<<i));
				}
			}
		}
	}
}
