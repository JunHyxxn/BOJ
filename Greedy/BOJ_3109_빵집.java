package com.day0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3109 {
	static int R, C, maxPipe;
	static boolean isFinish, visited[][];
	static int[][] dxdy = {{-1,-1}, {0,-1}, {1,-1}};
	static String[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		maxPipe = 0;
		map = new String[R][C];
		
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().split("");
		}
		
		for (int i = 0; i < R; i++) {
			isFinish = false;
			visited[i][C-1] = true;
			DFS(i, C-1);
		}
		System.out.println(maxPipe);
	}
	
	static boolean isValid(int x, int y) {
		return !(x<0 || x>= R || y <0 || y>=C);
	}
	static String DFS(int x, int y) {
		if(y == 0) {
			map[x][y] = "x";
			maxPipe++;
			isFinish = true;
			return "x";
		}
		
		for (int i = 0; i < 3; i++) {
			int nx = x+dxdy[i][0], ny = y+dxdy[i][1];
			if(!isValid(nx, ny) || map[nx][ny].equals("x") || visited[nx][ny]) continue;
			visited[nx][ny] = true;
			map[nx][ny] = DFS(nx, ny);
			if(isFinish) {
				return map[nx][ny]; 
			}
		}
		
		return ".";
	}
}
