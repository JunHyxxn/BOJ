package com.day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4014_활주로건설 {
	static int N, X;
	static int[][] map, diff;
	static boolean rowChecked[], colChecked[]; 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			diff = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			// 가로 diff 계산
			
			int result = 0; 
			
			for (int i = 0; i < N; i++) {
				diff[i][0] = 0;
				for (int j = 1; j < N; j++) {
					diff[i][j] = map[i][j] - map[i][j-1];
				}
				// check
				colChecked = new boolean[N];
				if(checkVertical(i, diff[i])) {
					result++;
				}
			}
			
			// Init 
			diff = new int[N][N];
			for (int j = 0; j < N; j++) {
				diff[0][j] = 0;
				for (int i = 1; i < N; i++) {
					diff[i][j] = map[i][j] - map[i-1][j];
				}
				// check
				rowChecked = new boolean[N];
				if(checkHorizontal(j)) {
					result++;
				}
			}
			
			sb.append("#" + t + " " + result +"\n");
		}
		System.out.println(sb);
	}
	
	static boolean isValid(int x) {
		return !(x<0 || x>=N);
	}
	static boolean checkVertical(int x, int[] arr) {
		for (int j = 0; j < N; j++) {
			if(arr[j] > 0) { // 왼쪽으로 경사로
				if(arr[j] > 1) return false;
				int temp = map[x][j-1];
				for (int k = j-1; k >= j-X; k--) {
					if(!isValid(k) || map[x][k] != temp || colChecked[k]) return false;
					colChecked[k] = true;
				}
			} else if (arr[j] < 0) { // 오른쪽으로 경사로
				if(arr[j] < -1) return false;
				int temp = map[x][j];
				for (int k = j; k < j+X; k++) {
					if(!isValid(k) || map[x][k] !=  temp || colChecked[k]) {
						return false;
					}
					colChecked[k] = true;
				}
			}
		}
		return true;
	}
	static boolean checkHorizontal(int y) {
		for (int x = 0; x < N; x++) {
			if(diff[x][y] > 0) { // 위쪽으로 경사로
				if(diff[x][y] > 1) return false;
				int temp = map[x-1][y];
				for (int k = x-1; k >= x-X; k--) {
					if(!isValid(k) || map[k][y] != temp || rowChecked[k]) return false;
					rowChecked[k] = true;
				}
			} else if (diff[x][y] < 0) { // 아래쪽으로 경사로
				if(diff[x][y] < -1) return false;
				int temp = map[x][y];
				for (int k = x; k < x+X; k++) {
					if(!isValid(k) || map[k][y] != temp || rowChecked[k]) return false;
					rowChecked[k] = true;
				}
			}
		}
		return true;
	}

}

