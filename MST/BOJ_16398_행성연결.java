package com.day0908;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_16398_행성연결 {
	static class Edge {
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}
	static int N;
	static int[] parents;
	static List<Edge> edges;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		edges = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if(i>=j) {
					st.nextToken();
					continue;
				}
				edges.add(new Edge(i, j, Integer.parseInt(st.nextToken())));
			}
		}
		edges.sort((a,b) -> a.weight - b.weight);
		make();
		long min = 0;
		int cnt = 1;
		for (Edge edge : edges) {
			if(union(edge.start, edge.end)) {
				min+= edge.weight;
				cnt++;
			}
			if(cnt == N) break;
		}
		System.out.println(min);
	}
	static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if (parents[a] == a) return a;
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
