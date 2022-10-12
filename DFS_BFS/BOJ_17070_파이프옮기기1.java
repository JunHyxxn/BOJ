package com.day0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070_파이프옮기기1 {
	static int N;
	static int[][] map, adjList, dxdy;
	static int[][][] dp;
	static {
		adjList = new int[][] {{}, {1,2}, {1,2,3}, {2,3}};
		dxdy = new int[][] {{0,0}, {0,1}, {1,1},{1,0}};
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[N+1][N+1][4];
		DFS(1,2, 1);
		int result = dp[1][2][1]+dp[1][2][2]+dp[1][2][3];
		System.out.println(result);
	}
	
	static boolean isPos(int x, int y, int pipe) {
		if(pipe == 1) {
			return map[x][y+1] ==0;
		} else if (pipe == 2) {
			return (map[x][y+1] == 0 && map[x+1][y+1] == 0 && map[x+1][y] == 0);
		} else {
			return map[x+1][y] == 0;
		}
	}
	static boolean isValid(int x, int y) {
		return !(x<=0 || x>N || y <=0 || y >N);
	}
	static int DFS(int nowX, int nowY, int nowPipe) {
		if(nowX == N && nowY == N) return 1;
		if(dp[nowX][nowY][nowPipe] != 0) return dp[nowX][nowY][nowPipe];
		int total = 0;
		for (int nxtPipe : adjList[nowPipe]) {
			int nx=nowX+dxdy[nxtPipe][0], ny=nowY+dxdy[nxtPipe][1];
			if(!isValid(nx, ny) || !isPos(nowX, nowY, nxtPipe)) continue;
			total += DFS(nx, ny, nxtPipe);
		}
		dp[nowX][nowY][nowPipe] = total;
		return total;
	}
	
}
