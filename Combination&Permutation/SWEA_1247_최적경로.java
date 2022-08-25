package com.day0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1247 {
	static int N, result;
	static int[] company, home, order, finalCost;
	static int[][] customers;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			result = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			company = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			home = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			customers = new int[N+1][2];
			customers[0] = company;
			order = new int[N+1];
			finalCost = new int[N+1];
			for (int i = 1; i <= N; i++) {
				customers[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
				finalCost[i] = getDistance(customers[i], home);
			}
			
			
			perm(1, 0, 0);
			sb.append("#" + t + " " +result + "\n");
		}
		System.out.println(sb);
	}
	
	static void perm(int idx, int state, int cost) {
		if (idx > N) {
			int now = order[idx-1];
			cost += finalCost[now];
			result = Math.min(result, cost);
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if((state & (1<<i)) == 0) {
				order[idx] = i;
				perm(idx+1, (state|(1<<i)), cost + getDistance(customers[order[idx-1]], customers[order[idx]]));
			}
		}
	}
	
	static int getDistance(int[] source, int[] destination) {
		return Math.abs(source[0]-destination[0]) + Math.abs(source[1] - destination[1]);
	}
}