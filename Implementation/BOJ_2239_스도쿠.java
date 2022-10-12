package com.day1004;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2239_스도쿠 {
	static int K, N = 9;
	static boolean isFinish = false;
	static List<Point> pos;
	static int[] row, col, square;
	static int[][] board;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 9;
		board = new int[N][N]; 
		row = new int[N];
		col = new int[N];
		square = new int[N];
		pos= new ArrayList<>();
		for (int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				int num = (int)(c[j]-'0');
				board[i][j] = num;
				if(num != 0) {
					row[i] |= (1<<num);
					col[j] |= (1<<num);
					square[(i/3)*3 + (j/3)] |= (1<<num);
				} else {
					pos.add(new Point(i,j));
				}
			}
		}
		
		K = pos.size(); 
		
		solve(0);
	}
	static void Print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(board[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
		return;
	}
	static void solve(int idx) {
		if(isFinish) return;
		if(idx==K) {
			// finish
			Print();
			isFinish = true;
			return;
		}
		Point p = pos.get(idx);
		int x = p.x, y = p.y;
		for (int bit = 1; bit <= N; bit++) {
			if(((row[x]&(1<<bit)) != 0) || ((col[y]&(1<<bit)) != 0) || ((square[(x/3)*3 + (y/3)]&(1<<bit)) != 0)) continue;
			row[x] |= (1<<bit);
			col[y] |= (1<<bit);
			square[(x/3)*3 + (y/3)] |= (1<<bit);
			board[x][y] = bit;
			solve(idx+1);
			row[x] &= ~(1<<bit);
			col[y] &= ~(1<<bit);
			square[(x/3)*3 + (y/3)] &= ~(1<<bit);
			board[x][y] = 0;
		}
	}
}
