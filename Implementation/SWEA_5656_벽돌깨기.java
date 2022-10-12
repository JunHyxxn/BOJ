package com.day1004;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기 {
	static int N, W, H, result;
	static int[] selected;
	static int[][] board, dxdy;
	static {
		dxdy = new int[][] {{0,1}, {1,0}, {0,-1}, {-1,0}};
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			board = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			selected = new int[N];
			result = Integer.MAX_VALUE;
			solve(0);
			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
	}

	static void solve(int idx) {
		if(idx == N) {
			// 이미 최적일 경우 종료
			if(result == 0) return;
			
			// 순서 선택 완료
			// simul 실행
			/*
			 * 1. 배열 복사
			 * 2. 벽돌 깨기 시작할 위치 탐색
			 * 3. BFS를 통해 깨지는 벽돌 전부 제거
			 * 4. 비어있는 공간 채우기
			 * 
			 * 반복
			 * */
			// 1. 배열 복사
			int[][] temp = new int[H][W];
			for (int i = 0; i < H; i++) {
				temp[i] = Arrays.copyOfRange(board[i], 0, W);
			}
			
			for (int sy : selected) { // 반복
				// 2. 벽돌깨기 시작할 위치 탐색 
				int sx = 0;
				while(sx< H) {
					if(temp[sx][sy] != 0) break;
					sx++;
				}
				if(sx== H )continue; // 해당 라인 전부 0이면 다음 진행
				// 3. BFS 로 깨질 벽돌 전부 0으로 바꾸기
				Queue<Point> queue = new ArrayDeque<>();
				queue.offer(new Point(sx, sy));
				Set<Integer> columns = new HashSet<>();
				
				while(!queue.isEmpty()) {
					Point now = queue.poll();
					int curX = now.x, curY = now.y;
					columns.add(curY);
					int cnt = temp[curX][curY];
					temp[curX][curY] = 0;
					for (int i = 1; i < cnt; i++) {
						for (int j = 0; j < 4; j++) {
							int dx = dxdy[j][0] * i;
							int dy = dxdy[j][1]*i;
							int nx = curX+dx, ny = curY+dy;
							if(!isValid(nx, ny) || temp[nx][ny] == 0) continue;
							queue.offer(new Point(nx, ny));
						}
					}
				}
				
				
				// 4. 비어있는 공간 채우기
				for (int col : columns) {
					Queue<Integer> list = new ArrayDeque<>();
					for (int row = H-1; row >=0; row--) {
						if(temp[row][col] != 0) {
							list.offer(temp[row][col]);
							temp[row][col] = 0;
						}
					}
					int row = H-1;
					while(!list.isEmpty()) {
						temp[row--][col] = list.poll();
					}
				}
				
			}
			
			// 5. count
			int count = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if(temp[i][j] != 0) count++; 
				}
			}
			
			result = Math.min(result, count);
			return;
		}
		
		for (int i = 0; i < W; i++) {
			selected[idx] = i;
			solve(idx+1);
		}
	}
	
	static boolean isValid(int x, int y) {
		return !(x<0 || x>=H || y<0 || y>= W);
	}
}
