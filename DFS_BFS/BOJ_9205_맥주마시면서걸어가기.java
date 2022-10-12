package com.day1006;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_9205_맥주마시면서걸어가기 {
	static int N, fx, fy;
	static boolean flag;
	static boolean[] visited;
	static List<Point> list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
//			st = new StringTokenizer(br.readLine());
//			Point prev = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			list = new ArrayList<>();
			for (int i = 0; i <= N+1; i++) {
				st = new StringTokenizer(br.readLine());
				list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			visited= new boolean[N+2];
			visited[0] = true;
			fx = list.get(N+1).x;
			fy = list.get(N+1).y;
			flag = false;
			DFS(list.get(0));
			sb.append(flag?"happy\n" : "sad\n");
		}
		System.out.println(sb);
	}
	static int getDistance(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
	static void DFS(Point now) {
		if(now.x == fx && now.y == fy) {
			flag = true;
			return;
		}
		for (int i = 1; i <= N+1; i++) {
			if(visited[i] || getDistance(now, list.get(i)) > 1000) continue;
			visited[i] = true;
			DFS(list.get(i));
		} 
	}
}
