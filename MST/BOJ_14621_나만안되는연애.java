package com.day0902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_14621_나만안되는연애 {
	static int N, M;
	static int[] parents;
	static String[] genders;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		genders = br.readLine().split(" ");
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			pq.offer(new int[] {a,b,w});
		}
		make();
		int res = 0, cnt = 0;
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			int a = now[0], b = now[1], w = now[2];
			if(genders[a].equals(genders[b])) continue;
			if(union(a,b)) {
				res+= w;
				cnt++;
			}
			if(cnt == N-1) break;
		}
		System.out.println(cnt == N-1 ? res : -1);
	}
	static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
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
		if (aRoot == bRoot) return false;
		parents[Math.max(aRoot, bRoot)] = Math.min(aRoot, bRoot);
		return true;
	}
}

