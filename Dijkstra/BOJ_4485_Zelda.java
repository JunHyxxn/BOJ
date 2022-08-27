package com.day0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4485_Zelda {
	static int N, tc;
	static int[][] cave, dist, dxdy = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	static boolean[][] visited;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		tc = 0;
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N==0) break;
			cave = new int[N][N];
			dist = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dist[0][0] = cave[0][0];
			
			++tc;
			dijkstra();
		}
		System.out.println(sb);
	}
	static boolean isValid(int x, int y) {
		return !(x<0 || x>= N || y <0 || y>=N);
	}
	static void dijkstra() { 
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		pq.offer(new int[] {0,0,cave[0][0]});
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
//			System.out.println(Arrays.toString(cur));
			int curX = cur[0], curY = cur[1], total = cur[2];
			if(curX == N-1 && curY == N-1) {
				sb.append("Problem " + tc +": "+ total +"\n");
				return;
			}
			if(visited[curX][curY]) continue;
			visited[curX][curY] = true;
			
			for (int i = 0; i < 4; i++) {
				int nx=curX+dxdy[i][0], ny = curY+dxdy[i][1];
				if(!isValid(nx, ny) || visited[nx][ny]) continue;
				if(dist[nx][ny] > total + cave[nx][ny]) {
					dist[nx][ny] = total+cave[nx][ny];
					pq.offer(new int[] {nx, ny, dist[nx][ny]});
				}
			}
		}
		
	}
}
