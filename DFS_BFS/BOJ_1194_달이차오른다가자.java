package com.day1005;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1194_달이차오른다가자 {
	static int N, M;
	static int[][] dxdy = {
			{0,1}, {1,0}, {0,-1}, {-1,0}
	};
	static char[][] map;
	static Map<Character, List<Point>> doors, keys;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		doors = new HashMap<>();
		keys = new HashMap<>();
		Point start = null;
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				char d = map[i][j];
				if(d == '0') {
					map[i][j] = '.';
					start = new Point(i,j);
				}
			}
		}
		
		BFS(start);
		System.out.println(-1);
		// a b c d e f 
		
	}
	static boolean isValid(int x, int y) {
		return !(x<0 || x>=N || y <0 || y>=M);
	}
	static void BFS(Point start) {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N][M][(1<<6)];
		visited[start.x][start.y][0] = true;
		queue.offer(new int[] {start.x, start.y, 0 , 0} );
		
		while(!queue.isEmpty()) {
			int[] state = queue.poll();
			int x = state[0], y = state[1], nowKey = state[2], time = state[3];
			for (int i = 0; i < 4; i++) {
				int nx = x + dxdy[i][0], ny = y+dxdy[i][1];
				if(!isValid(nx, ny) || map[nx][ny] == '#') continue;
				if(map[nx][ny] == '1') {
					System.out.println(time+1);
					System.exit(0);
				}
				
				if('a' <= map[nx][ny] && map[nx][ny] <= 'f') {
					int nxtKey = nowKey | (1<<(map[nx][ny]-'a'));
					if(visited[nx][ny][nxtKey]) continue;
					visited[nx][ny][nxtKey] = true;
					queue.offer(new int[] {nx, ny, nxtKey, time+1});
					continue;
				} else if ('A' <= map[nx][ny] && map[nx][ny] <= 'F') {
					if((nowKey & (1<<(map[nx][ny]-'A'))) != 0 && !visited[nx][ny][nowKey]) {
						visited[nx][ny][nowKey] = true;
						queue.offer(new int[] {nx, ny, nowKey, time+1});
					}
					continue;
				}else if(map[nx][ny] == '.' && !visited[nx][ny][nowKey]) {
					visited[nx][ny][nowKey] = true;
					queue.offer(new int[] {nx, ny, nowKey, time+1});					
				}
			}
		}
	}
	
}
