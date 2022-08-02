package com.day0802;

import java.util.Scanner;

public class SWEA_1954 {
	static int[][] downLeft = {{1,0}, {0,-1}};
	static int[][] upRight = {{-1,0}, {0,1}};
	static int N, cnt, x, y;
	static int[][] board;
	
	static void down(int num) {
		for (int i = 0; i < 2; i++) {
			int[] dxdy = downLeft[i];
			for (int j= 0; j < num; j++) {
				x += dxdy[0];
				y += dxdy[1];
				board[x][y] = cnt++; 
			}
		}
	}
	static void top(int num) {
		for (int i = 0; i < 2; i++) {
			int[] dxdy = upRight[i];
			for (int j= 0; j < num; j++) {
				x += dxdy[0];
				y += dxdy[1];
				board[x][y] = cnt++; 
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			board = new int[N][N];
			cnt = 1;
			for (int i = 0; i < N; i++) {
				board[0][i] = cnt++;
			}
			int startDir = (N-1)%2;
			x = 0;
			y = N-1;
			for (int i = N-1; i >=1; i--) { // 이동 횟수
				if(i%2 == startDir) {
					down(i);
				}else {
					top(i);
				}
			}
			
			System.out.println("#"+t);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(board[i][j] + " " );
				}
				System.out.println();
			}
		}
	}
}
