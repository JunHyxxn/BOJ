package com.day1005;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2636_치즈 {
	static int R, C, cheeze;
	static int[][] board;
	static int[][] dxdy = {
			{0,1},{1,0},{0,-1},{-1,0}
	};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		cheeze = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 1) cheeze++;
			}
		}
		
		run();
	}
	static void run() {
		int prev = 0;
		Queue<Point> start = new ArrayDeque<>();
		start.offer(new Point(0,0));
		int time = 0;
		while(cheeze != 0) {
			prev = cheeze;
			
			Queue<Point> temp = BFS(start);
			time++;
			start = temp;
		}
		System.out.println(time);
		System.out.println(prev);
	}
	
	static Queue<Point> BFS(Queue<Point> queue) {
		boolean[][] visited = new boolean[R][C];
		Point start = queue.peek();
		visited[start.x][start.y] = true;
		int num = board[start.x][start.y];
		Queue<Point> ret = new ArrayDeque<>();
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx= now.x+dxdy[i][0], ny = now.y + dxdy[i][1];
				if(!isValid(nx, ny) || visited[nx][ny]) continue;
				if(board[nx][ny] == 1) {
					ret.offer(new Point(nx,ny));
					board[nx][ny] = 0;
					cheeze--;
					visited[nx][ny] = true;
					continue;
				} 
				visited[nx][ny] = true;
				queue.offer(new Point(nx,ny));
			}
		}
		
		return ret;
	}
	static boolean isValid(int x, int y) {
		return !(x<0 || x>= R || y <0 || y>= C);
	}
}

