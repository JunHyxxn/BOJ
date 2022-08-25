package com.day0824;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class BOJ_10026 {
	static int N;
	static int[][] dxdy = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	static char[][] map;
	static boolean[][] visited;
	static Map<Character, Integer> ColorCount;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// First Normal Eyes
		ColorCount = new HashMap<>();
		ColorCount.put('R', 0);
		ColorCount.put('G', 0);
		ColorCount.put('B', 0);
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				BFS(i,j);
				ColorCount.put(map[i][j], ColorCount.get(map[i][j]) + 1);
			}
		}
		int res = 0;
		for (int  v : ColorCount.values()) {
			res+=v;
		}
		sb.append(res + " ");
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]=='R') map[i][j] = 'G';
			}
		}
		// Second 
		ColorCount = new HashMap<>();
		ColorCount.put('R', 0);
		ColorCount.put('G', 0);
		ColorCount.put('B', 0);
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				BFS(i,j);
				ColorCount.put(map[i][j], ColorCount.get(map[i][j]) + 1);
			}
		}
		res = 0;
		for (int  v : ColorCount.values()) {
			res+=v;
		}
		sb.append(res);
		System.out.println(sb);
		
		
	}
	static boolean isValid(int x, int y) {
		return !(x<0 || x>=N || y<0 || y>= N);
	}
	static int BFS(int x, int y) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(new Point(x, y));
		visited[x][y] = true;
		int cnt = 1;
		char color = map[x][y];
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx=now.x+dxdy[i][0], ny=now.y+dxdy[i][1];
				if(!isValid(nx, ny) || visited[nx][ny] || color != map[nx][ny]) continue;
				visited[nx][ny] = true;
				cnt++;
				queue.offer(new Point(nx, ny));
			}
		}
		return cnt;
	}
}
