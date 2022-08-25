package com.day0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_17135 {
	static int N, M, D, result, MAX;
	static int[] archerIdx;
	static int[][] map, dxdy = {{0,-1},{-1,0},{0,1}};
	static Set<Integer> posEnemy;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		result = Integer.MIN_VALUE;
		int totalEnemy = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) totalEnemy++;
			}
		}
		MAX = totalEnemy;
		archerIdx = new int[3];
		comb(0,0, totalEnemy);
		System.out.println(result);
	}	
	
	static void comb(int idx, int start, int totalEnemy) {
		if(result == MAX) return;
		if(idx == 3) {
			// play game
			List<Integer> archers = new ArrayList<>();
			for (Integer archer : archerIdx) {
				archers.add(archer);
			}
			int[][] tempMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					tempMap[i][j] = map[i][j];
				}
			}
			result = Math.max(result, play(archers, tempMap, totalEnemy));
			return;
		}
		
		for (int i = start; i < M; i++) {
			archerIdx[idx] = i;
			comb(idx+1, i+1, totalEnemy);
		}
	}	
		
	static int play(List<Integer> archers, int[][] tempMap, int totalEnemy) {
		int cnt = 0;
		while(true) {
			if(totalEnemy <= 0 || archers.isEmpty()) break;
			posEnemy = new HashSet<>();
			for (int archer : archers) {
				int[] temp = findEnemy(archer, tempMap);
				if(temp == null) continue;
				posEnemy.add(temp[0]*M+temp[1]);
			}
			// enemy 제거 
			for (int pos : posEnemy) {
				tempMap[pos/M][pos%M] = 0;
				cnt++;
				totalEnemy--;
			}
			// move 
			// last row
			List<Integer> tempArcher = new ArrayList<>();
			for (int i = 0; i < M; i++) {
				if(tempMap[N-1][i]==1) {
					totalEnemy--;
				}
				for (int arc : archers) {
					if(arc != i) continue;
					if(tempMap[N-1][i] == 0) tempArcher.add(arc);
				}
			}
			archers = tempArcher;

			for (int i = N-2; i >= 0; i--) {
				for (int j = 0; j < M; j++) {
					tempMap[i+1][j] = tempMap[i][j];
				}
			}
			for (int i = 0; i < M; i++) {
				tempMap[0][i] = 0;
			}
		}
		return cnt;
	}	
	
	static boolean isValid(int x, int y) {
		return !(x<0 || x>=N || y < 0 || y >= M);
	}
	
	static int[] findEnemy(int col, int[][] map) {
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {N, col, 0});
		int[] info;
		while(!queue.isEmpty()) {
			info = queue.poll();
			int r = info[0], c = info[1], d = info[2];
			if(d == D) return null;
			
			for (int i = 0; i < 3; i++) {
				int nr = r+dxdy[i][0], nc = c+dxdy[i][1];
				if(!isValid(nr, nc) || visited[nr][nc]) continue;
				if(map[nr][nc] == 1) {
					return new int[] {nr, nc};
				}
				visited[nr][nc] = true;
				queue.offer(new int[] {nr, nc, d+1});
			}
		}
		return null;
	}
}
