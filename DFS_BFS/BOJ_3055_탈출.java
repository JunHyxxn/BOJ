package com.day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055 {
	static int R, C;
	static int[][] dxdy = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	static char[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		int hedgehogX = 0, hedgehogY = 0;
		Queue<int[]> waters = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if(map[i][j] == 'S') {
					hedgehogX = i;
					hedgehogY = j;
					map[i][j] = '.';
					continue;
				}else if (map[i][j] == '*') {
					waters.offer(new int[] {i, j});
				}
			} 
		}
		
		visited = new boolean[R][C];
		visited[hedgehogX][hedgehogY] = true;
		Queue<int[]> hedgehogs = new LinkedList<>();
		hedgehogs.offer(new int[] {hedgehogX, hedgehogY, 0});
		int waterSize;
		int hedgehogSize;
		
		while(true) {
			if(hedgehogs.isEmpty()) break;
			waterSize = waters.size();
			hedgehogSize = hedgehogs.size();
			
			for (int i = 0; i < waterSize; i++) {
				int[] watertemp = waters.poll();
				int wx = watertemp[0], wy = watertemp[1];
				for (int j = 0; j < 4; j++) {
					int wnx = wx+dxdy[j][0], wny = wy+dxdy[j][1];
					if(!isValid(wnx,wny) || map[wnx][wny] != '.') continue;
					waters.offer(new int[] {wnx, wny});
					map[wnx][wny] = '*';
				}
			}
			
			for (int i = 0; i < hedgehogSize; i++) {
				int[] hedgehogtemp = hedgehogs.poll();
				int hx = hedgehogtemp[0], hy = hedgehogtemp[1], time = hedgehogtemp[2];
				for (int j = 0; j < 4; j++) {
					int hnx = hx+dxdy[j][0], hny = hy+dxdy[j][1];
					if(!isValid(hnx, hny) || visited[hnx][hny]) continue;
					if(map[hnx][hny] == 'D') {
						System.out.println(time+1);
						return;
					}
					if(map[hnx][hny] == '.') {
						visited[hnx][hny] = true;
						hedgehogs.offer(new int[] {hnx, hny, time+1});
					}
				}
			}
		}
		System.out.println("KAKTUS");
		
	}
	static boolean isValid(int x, int y) { 
		return !(x <0 || x>=R || y <0 || y>= C);
	}
}
