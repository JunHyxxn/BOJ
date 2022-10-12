package com.day0930;

/*
{{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}}	{{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}}
 * */

public class KakaoBlind_2022_파괴되지않은건물 {
	static int N, M, K;
	static int[][] temp;
	public static void main(String[] args) {
		int[][] board = {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
		int[][] skill = {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};
		
		N = board.length;
		M = board[0].length;
		K = skill.length;
		
		int[][] record = new int[N][M]; 
		
		// 공격 / 회복 누적
		for (int[] sk : skill) {
			int r1 = sk[1], c1 = sk[2], r2 = sk[3], c2 = sk[4], degree = sk[5] * (sk[0] == 1 ? -1 : 1);
			record[r2][c2] += degree;
			if(isValid(r1-1, c2)) {
				record[r1-1][c2] -= degree;
			}
			
			if(isValid(r2, c1-1)) {
				record[r2][c1-1] -= degree;
			}
			
			if(isValid(r1-1, c1-1)) {
				record[r1-1][c1-1] += degree;
			}
		}
		
		// 누적 데미지 펼쳐주기 - 행
		for (int i = N-1; i >= 0; i--) {
			for (int j = M-2; j >= 0; j--) {
				record[i][j] += record[i][j+1];
			}
		}
		// 누적 데미지 펼쳐주기 - 열
		for (int i = M-1; i >=0; i--) {
			for (int j = N-2; j >= 0; j--) {
				record[j][i] += record[j+1][i];
			}  
		}
		
		// 계산
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(board[i][j] + record[i][j] >0) cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	static boolean isValid(int x, int y) {
		return !(x<0 || x>= N || y < 0 || y >=M);
	}
	
}
