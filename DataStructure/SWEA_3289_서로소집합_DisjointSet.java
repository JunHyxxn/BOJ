package com.day0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3289 {
	static int N, M;
	static int[] parents;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			make();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int cmd = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (cmd == 0) {
					union(a,b);
				} else if (cmd == 1) {
					sb.append(isSame(a,b) ? "1" : "0");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	static void make() {
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean isSame(int a, int b) {
		int aPar = find(a);
		int bPar = find(b);
		if(aPar == bPar) return true;
		return false;
	}
	
	static void union(int a, int b) {
		int aPar = find(a);
		int bPar = find(b);
		if(aPar == bPar) return;
		parents[Math.max(aPar, bPar)] = Math.min(aPar, bPar);
		return;
	}
}
