package com.day0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15683 {
	static int N, M, min, size;
	static int[][] map;
	static int[][] dxdy = {{0,1},{1,0},{0,-1},{-1,0}};
	static List<int[]> cctv;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		map = new int[N][M];
		cctv = new ArrayList<>();
		int temp;
		List<int[]> init = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				temp = Integer.parseInt(st.nextToken());
				if (temp >0 && temp <5) {
					cctv.add(new int[] {i,j});
					map[i][j] = temp;
				} else if (temp == 5) {
					init.add(new int[] {i,j});
				}else {
					map[i][j] = temp;
				}
			}
		}
		
		size = cctv.size();
			
		for (int[] start : init) {
			int x = start[0], y = start[1];
			map[x][y] = '#';
			for (int dir = 0; dir < 4; dir++) {
				x = start[0]; y = start[1];
				while(true) {
					int nx = x+dxdy[dir][0], ny = y+dxdy[dir][1];
					if(!isValid(nx, ny) || map[nx][ny] == 6) break;
					if (map[nx][ny] == 0) map[nx][ny] = '#';
					x = nx; y = ny;
				}
			}
		}
		
		
		solve(0, map);
		System.out.println(min);
	}
	
	static boolean isValid(int x, int y) {
		return !(x<0 || x >= N || y < 0 || y >= M);
	}
	
	static int count(int[][] temp) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(temp[i][j] == 0) cnt++;
			}
		}
		
		return cnt;
	}
	
	static void solve(int idx, int[][] map) {
		if(idx == size) {
			min = Math.min(min, count(map));
//			int t = count(map);
//			if(min > t) {
//				min = t;
//				
//				System.out.println(Arrays.deepToString(map));
//			}
			return;
		}
		
		int[] xy = cctv.get(idx);
		int x = xy[0], y = xy[1];
		int[][] temp = new int[N][M];
		if(map[x][y] == 1) {
			temp = right(x, y, deepcopy(map));
			solve(idx+1, temp);
			
			temp = down(x, y, deepcopy(map));
			solve(idx+1, temp);
			
			temp = left(x, y, deepcopy(map));
			solve(idx+1, temp);
			
			temp = up(x, y, deepcopy(map));
			solve(idx+1, temp);
		} else if(map[x][y] == 2) {
			temp = left(x, y, deepcopy(map));
			temp = right(x, y, deepcopy(temp));
			solve(idx+1, temp);
			
			temp = up(x, y, deepcopy(map));
			temp = down(x, y, deepcopy(temp));
			solve(idx+1, temp);			
		} else if(map[x][y] == 3) {
			
			temp = up(x, y, deepcopy(map));
			temp = right(x, y, deepcopy(temp));
			solve(idx+1, temp);			
			
			temp = down(x, y, deepcopy(map));
			temp = right(x, y, deepcopy(temp));
			solve(idx+1, temp);			
			
			temp = left(x, y, deepcopy(map));
			temp = down(x, y, deepcopy(temp));
			solve(idx+1, temp);			
			
			temp = left(x, y, deepcopy(map));
			temp = up(x, y, deepcopy(temp));
			solve(idx+1, temp);			
			
		} else if(map[x][y] == 4) {
			temp = left(x, y, deepcopy(map));
			temp = up(x, y, deepcopy(temp));
			temp = right(x, y, deepcopy(temp));
			solve(idx+1, temp);
			
			temp = up(x, y, deepcopy(map));
			temp = right(x, y, deepcopy(temp));
			temp = down(x, y, deepcopy(temp));
			solve(idx+1, temp);
			
			temp = left(x, y, deepcopy(map));
			temp = down(x, y, deepcopy(temp));
			temp = right(x, y, deepcopy(temp));
			solve(idx+1, temp);
			
			temp = down(x, y, deepcopy(map));
			temp = left(x, y, deepcopy(temp));
			temp = up(x, y, deepcopy(temp));
			solve(idx+1, temp);
		}
		
	}
	static int[][] deepcopy(int[][] map) {
		int[][] temp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
		return temp;
	}
	
	static int[][] left(int x, int y, int[][] map) {
		for (int j = y-1; j >= 0; j--) {
			if(map[x][j] == 6) return map;
			else if(map[x][j] == 0) map[x][j] = '#';
		}
		return map;
	}
	static int[][] right(int x, int y, int[][] map) {
		for (int j = y+1; j < M; j++) {
			if(map[x][j] == 6) return map;
			else if(map[x][j] == 0) map[x][j] = '#';
		}
		return map;
	}
	static int[][] up(int x, int y, int[][] map) {
		for (int i = x-1; i >=0; i--) {
			if(map[i][y] == 6) return map;
			else if(map[i][y] == 0) map[i][y] = '#';
		}
		return map;
	}
	static int[][] down(int x, int y, int[][] map) {
		for (int i = x+1; i < N; i++) {
			if(map[i][y] == 6) return map;
			else if(map[i][y] == 0) map[i][y] = '#';
		}
		return map;
	}
}


