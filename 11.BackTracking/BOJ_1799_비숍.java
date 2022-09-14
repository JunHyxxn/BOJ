package com.day0914;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1799_비숍 {
	static int N, wn, bn, resultWhite, resultBlack;
	static int[][] board;
	static List<int[]> white, black;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		white = new ArrayList<>();
		black = new ArrayList<>();
		resultWhite = Integer.MIN_VALUE;
		resultBlack = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 0) continue;
				if((i+j)%2==0) { // white
					white.add(new int[] {i,j});
				} else { // black
					black.add(new int[] {i,j});
				}
			}
		}
		wn = white.size();
		bn = black.size();
		whiteSolve(0,0,0,0);
		blackSolve(0,0,0,0);
		System.out.println(resultWhite + resultBlack);
	}
	static void whiteSolve(int depth, int cnt, int rightUp, int rightDown) {
		if(depth== wn) {
			resultWhite = Math.max(cnt, resultWhite);
			return;
		}
		if(cnt+wn-depth< resultWhite) return;
		int[] xy = white.get(depth);
		int x = xy[0], y = xy[1];
		if(((rightUp & (1<<(x+y))) == 0) && ((rightDown & (1<<(N-1-(y-x)))) == 0)) {
			whiteSolve(depth+1, cnt+1, ((rightUp | (1<<(x+y)))), ((rightDown | (1<<((N-1-(y-x)))))) );
		};
		whiteSolve(depth+1, cnt, rightUp, rightDown);			
		
	}
	static void blackSolve(int depth, int cnt, int rightUp, int rightDown) {
		if(depth == bn) {
			resultBlack= Math.max(cnt, resultBlack);
			return;
		}
		if(cnt+bn-depth < resultBlack) return;
		int[] xy = black.get(depth);
		int x = xy[0], y = xy[1];
		if(((rightUp & (1<<(x+y))) == 0) && ((rightDown & (1<<(N-1-(y-x)))) == 0)) {
			blackSolve(depth+1, cnt+1, ((rightUp | (1<<(x+y)))), ((rightDown | (1<<((N-1-(y-x)))))));
		};
		blackSolve(depth+1, cnt, rightUp, rightDown);
	}
}
