package com.day0918;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18188_다오의데이트 {
	static class Info {
		int x, y, time;
		String path;
		public Info(int x, int y, int time, String path) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.path = path;
		}
		
	}
	static int H, W, N;
	static boolean flag;
	static Point start;
	static char[][] map;
	static Map<Character, int[]> dir;
	static List<char[]> moves;
	static {
		dir = new HashMap<>();
		dir.put('W', new int[] {-1,0});
		dir.put('A', new int[] {0,-1});
		dir.put('S', new int[] {1,0});
		dir.put('D', new int[] {0,1});
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		for (int i = 0; i < H; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < W; j++) {
				map[i][j] = temp[j];
				if(map[i][j] == 'D') {
					start = new Point(i,j);
					map[i][j] = '.';
				}
			}
		}
		
		N = Integer.parseInt(br.readLine());
		moves = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			moves.add(br.readLine().replaceAll(" ", "").toCharArray());
		}
		
		
		BFS();
		if(!flag) System.out.println("NO");
	}
	
	static boolean isValid(int x, int y) {
		return !(x<0 || x>=H || y <0 || y>=W);
	}
	
	static void BFS() {
		Queue<Info> queue = new ArrayDeque<>();
		queue.add(new Info(start.x, start.y, 0, ""));
		while(!queue.isEmpty() ) {
			Info now = queue.poll();
			if(now.time == N) return;
			
			for (char move : moves.get(now.time)) {
				int nx = now.x + dir.get(move)[0], ny = now.y + dir.get(move)[1];
				if(!isValid(nx, ny) || map[nx][ny] =='@') continue;
				if(map[nx][ny] == 'Z') {
					flag=true;
					System.out.println("YES");
					System.out.println(now.path + move);
					return;
				}
				queue.add(new Info(nx, ny, now.time+1,now.path+move));
				
			}
		}
	}
}



