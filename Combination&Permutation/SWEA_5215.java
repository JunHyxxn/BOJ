package com.day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5215 {
	// Ver. Prof
	static int[][] material; // 재료들의 맛접수, 열량을 저장하는 배열
	static int N, L, totalScore; 
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			material = new int[2][N];
			totalScore = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				material[0][i] = Integer.parseInt(st.nextToken());
				material[1][i] = Integer.parseInt(st.nextToken());
			}
			
			subset(0, 0);
			
			sb.append("#" + t + " " + totalScore + "\n");
		}
		System.out.println(sb);
	}
	static void subset(int idx, int state) {
		if (idx == N) {
			check(state);
			return;
		}
		
		subset(idx+1, (state|1<<idx));
		subset(idx+1, (state));
	}
	static void check(int state) {
		int limit = 0;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			if((state&1<<i) == 1) {
				System.out.println(material[0][i]);
				sum += material[0][i];
				limit += material[1][i];
			}
			if(limit > L) return;
		}
		
		totalScore = totalScore < sum ? sum : totalScore;
	}
	// Ver. JunHyxxn
//	static int N, L, result;
//	static int[] scores, kcal;
//	static void solve(int totalScore, int totalKcal, int idx) {
//		if(totalKcal > L) return;
//		if(idx == N) {
//			result = result < totalScore ? totalScore : result;
//			return;
//		}
//		
//		solve(totalScore + scores[idx], totalKcal+kcal[idx] , idx+1);
//		solve(totalScore, totalKcal, idx+1);
//	}
//	public static void main(String[] args) throws IOException{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
//		StringBuilder sb = new StringBuilder();
//		int T = Integer.parseInt(br.readLine());
//		for (int t = 1; t <= T; t++) {
//			st = new StringTokenizer(br.readLine());
//			N = Integer.parseInt(st.nextToken());
//			L = Integer.parseInt(st.nextToken());
//			scores = new int[N];
//			kcal = new int[N];
//			result = Integer.MIN_VALUE;
//			for (int i = 0; i < N; i++) {
//				st = new StringTokenizer(br.readLine());
//				scores[i] = Integer.parseInt(st.nextToken());
//				kcal[i] = Integer.parseInt(st.nextToken());
//			}
//			solve(0,0,0);
//			sb.append("#" + t + " " +result+"\n");
//		}
//		
//		System.out.println(sb);
//	}

}

