package com.day0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1197 {
	/*
	 * https://www.acmicpc.net/problem/1197
	 * 최소 신장 트리
	 * */
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	static int V, E; 
	static int[] parents;
	static Edge[] edgeList;
	static void makeSet() {
		parents = new int[V+1];
		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}
	static int findSet(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		
		parents[Math.min(aRoot, bRoot)] =  Math.max(aRoot, bRoot);
		return true;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		makeSet();
		edgeList = new Edge[E];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			edgeList[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(edgeList);
		int result = 0;
		int cnt = 0;
		for (Edge edge : edgeList) {
			if(union(edge.from, edge.to)) {
				result += edge.weight;
				cnt++;
				if(cnt == E-1) break;
			}
		}
		
		System.out.println(result);
	}
}
