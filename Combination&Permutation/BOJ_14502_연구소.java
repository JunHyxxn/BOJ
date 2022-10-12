package com.day1004;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_연구소 {
	static int N, M, result;
	static Point[] walls;
	static Queue<Point> viruses;
	static int[][] lab, temp, dxdy;
	static boolean[][] visited;
	static {
		dxdy = new int[][] {
			{0,1}, {1,0}, {0,-1}, {-1,0}
		};
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		viruses = new ArrayDeque<>();
		lab = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				if(lab[i][j] == 2) viruses.add(new Point(i,j));
			}
		}
		
		walls = new Point[3];
		result = Integer.MIN_VALUE;
		temp = new int[N][M];
		visited=  new boolean[N][M];
		comb(0, 0);
		System.out.println(result);
	}
	
	static void comb(int cnt, int x) {
		if(cnt == 3) {
			
			// 1. 배열 복사
			copyMatrix();
			
			// 2. 벽 생성
			for (Point wall : walls) {
				temp[wall.x][wall.y] = 1;  
			}
			
			// 3. 바이러스 전이 시작
			spread();
			
			// 4. count
			result = Math.max(result, count());
			return;
		}
		
		for (int i = x; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(lab[i][j] == 0 && !visited[i][j]) {
					walls[cnt] = new Point(i,j);
					visited[i][j] = true;
					comb(cnt+1, i);
					visited[i][j] = false;
				}
			}
			
		}
	}
	
	static boolean isValid(int x, int y) {
		return !(x<0 || x>=N || y<0 || y>=M);
	}
	static void copyMatrix() {
		for (int i = 0; i < N; i++) {
			temp[i] = Arrays.copyOfRange(lab[i], 0, M);
		}
		return;
	}
	
	
	static void spread() {
		Queue<Point> queue = new ArrayDeque<>(viruses);
		
		while(!queue.isEmpty()) {
			Point state = queue.poll();
			int x = state.x, y = state.y;
			temp[x][y] = 2;
			for (int i = 0; i < 4; i++) {
				int nx = x+dxdy[i][0], ny = y+dxdy[i][1];
				if(!isValid(nx, ny) || temp[nx][ny] != 0) continue;
				queue.offer(new Point(nx,ny));
			}
		}
	}
	
	static int count() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(temp[i][j] ==0) cnt++;
			}
		}
		return cnt;
	}
}


