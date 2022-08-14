package com.day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1861 {
	static int N, maxRoom, minNum;
	static int[][] map;
	static int[][] dxdy = {{-1,0},{0,1},{1,0},{0,-1}};
	static int[][] dp;
	static boolean isValid(int x, int y) {
		if(x <0 || x >= N || y < 0 || y >= N) return false;
		return true;
	}
	
	// 단순히 visited 검사를 하게 되면 더 낮은 수에서 출발할 수 있는데 이런 경우를 누락하게 된다.
	// 따라서 memoization 으로 해당 위치에서 탐색하게 되면 몇개의 방을 탐색할 수 있는지 기록해두고
	// 탐색 중 이 칸을 만났을 때 빠르게 종료할 수 있도록 한다.
//	static void DFS(int x, int y) {
//		Stack<List<Integer>> stack = new Stack<>();
//		stack.add(Arrays.asList(x, y));
//		int cnt = 1;
//		
//		while (!stack.isEmpty()) {
//			List<Integer> info = stack.pop();
//			int curX = info.get(0), curY = info.get(1);
//			for (int i = 0; i < dxdy.length; i++) {
//				int nx = curX + dxdy[i][0], ny = curY + dxdy[i][1];
//				if(!isValid(nx, ny)) continue;
//				if(map[nx][ny] - map[curX][curY] == 1) {
//					cnt++;
//					stack.add(Arrays.asList(nx, ny));
//				}
//			}
//		}
//		if (maxRoom < cnt) {
//			maxRoom = cnt;
//			minNum = map[x][y];
//		} else if (maxRoom == cnt) {
//			minNum = minNum > map[x][y] ? map[x][y] : minNum;			
//		}
//	}
	
	// Memoization 적용
	static int DFS(int x, int y) {
		if(dp[x][y] != 0) {
			return dp[x][y];
		}
		dp[x][y] = 1;
		for (int i = 0; i < dxdy.length; i++) {
			int nx = x+dxdy[i][0], ny = y+dxdy[i][1];
			if(!isValid(nx, ny)) continue;
			if(map[nx][ny] - map[x][y] == 1) {
				dp[x][y] = DFS(nx, ny) + 1;
			}
		}
		

		return dp[x][y];
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			dp = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			maxRoom = Integer.MIN_VALUE;
			minNum = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int temp = DFS(i,j);
					if(maxRoom < temp) {
						maxRoom = temp;
						minNum = map[i][j];
					} else if(maxRoom == temp) minNum = minNum > map[i][j] ? map[i][j] : minNum;
				}
			}
			sb.append("#" + t + " " +minNum + " " + maxRoom + "\n");
		}
		System.out.println(sb);
	}
}
