package com.day0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1987 {
	static int R, C, result;
	static int[][] dxdy = {{0,-1},{-1,0},{0,1},{1,0}};
	static String[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new String[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().split("");
		}
		int path = (1<<map[0][0].charAt(0) - 'A');
		result = Integer.MIN_VALUE;
		DFS(0,0,1, path);
		System.out.println(result);
	}
	static boolean isValid(int x, int y) {
		return !(x<0 || x>=R || y<0 || y>=C);
	}
	static void DFS(int x, int y, int cnt, int path) {
		for (int i = 0; i < 4; i++) {
			int nx = x+dxdy[i][0], ny = y+dxdy[i][1];
			if(!isValid(nx, ny) || (path & (1<<map[nx][ny].charAt(0) - 'A')) != 0) continue;
			DFS(nx, ny, cnt+1, (path | 1<<map[nx][ny].charAt(0) -'A'));
		}
		
		result = Math.max(result, cnt);
	}
}
