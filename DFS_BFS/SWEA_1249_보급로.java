package com.day0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_1249_보급로 {
	static int N;
	static int[][] dxdy = {
			{0,1}, {1,0}, {0,-1}, {-1,0}
	};
	static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				char[] c = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = (int)(c[j] - '0');
				}
			}
			
			sb.append("#" + t + " " +BFS() + "\n");
			
		}
		System.out.println(sb);
	}
	
	static boolean isValid(int x, int y) {
		return !(x<0 || x>=N || y<0 || y>=N);
	}
	static int BFS() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
		pq.offer(new int[] {0,0,0});
		int[][] dist = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		while(!pq.isEmpty()) {
			int[] state = pq.poll();
			int x = state[0], y = state[1], cost = state[2];
			if(x == N-1 && y == N-1) {
				return cost;
			}
			for (int i = 0; i < 4; i++) {
				int nx = x + dxdy[i][0], ny = y+dxdy[i][1];
				if(!isValid(nx, ny) || dist[nx][ny] <= cost+map[nx][ny]) continue;
				int nxtCost = cost+map[nx][ny];
				dist[nx][ny] = nxtCost;
				pq.offer(new int[] {nx,ny,nxtCost});
			}
		}
		
		return -1;
	}
}
