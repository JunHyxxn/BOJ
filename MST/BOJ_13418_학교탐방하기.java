package com.day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_13418 {
	static class Edge {
		int from, to;

		public Edge(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
	static int N,M;
	static int[] parents;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		List<Edge> uphill = new ArrayList<>();
		List<Edge> downhill = new ArrayList<>();
		for (int i = 0; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int mode = Integer.parseInt(st.nextToken());
			Edge temp = new Edge(a, b);
			if (mode == 0) uphill.add(temp);
			else if(mode == 1) downhill.add(temp); 
		}
		parents = new int[N+1];
		make();
		int upRes = 0;
		for (Edge edge : uphill) {
			if(union(edge.from, edge.to)) {
				if(++upRes == N) break;
			}
		}

		parents = new int[N+1];
		make();
		int downRes = 0;
		for (Edge edge : downhill) {
			if(union(edge.from, edge.to)) {
				if(++downRes == N) break;
			}
		}
		System.out.println((int)(Math.pow(upRes, 2) - Math.pow((N-downRes), 2)));
	}
	
	static void make() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[Math.max(aRoot, bRoot)] = Math.min(aRoot, bRoot);
		return true;
	}
}
