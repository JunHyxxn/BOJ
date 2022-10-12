package com.day1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4013_특이한자석 {
	static int N;
	static boolean[] visited;
	static int[][] magnets;
	static final int magCnt = 4, magSize = 8;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			magnets = new int[magCnt][magSize];
			for (int i = 0; i < magCnt; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < magSize; j++) {
					magnets[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken())-1;
				int dir = Integer.parseInt(st.nextToken());
				visited = new boolean[magCnt];
				run(idx, dir);
			}
			
			int total = 0;
			for (int i = 0; i < magCnt; i++) {
				total+= magnets[i][0] == 1 ? 1<<i : 0;
			}
			sb.append("#" + t + " " + total + "\n");
		}
		System.out.println(sb);
	}
	
	static void rotateReverse(int idx) {
		int temp = magnets[idx][0];
		for (int i = 1; i < magSize; i++) {
			magnets[idx][i-1] =magnets[idx][i]; 
		}
		magnets[idx][magSize-1] = temp;
	}
	static void rotate(int idx) {
		int temp = magnets[idx][magSize-1];
		for (int i = magSize-2; i >= 0; i--) {
			magnets[idx][i+1] =magnets[idx][i]; 
		}
		magnets[idx][0] = temp;
	}
	
	static void run(int idx, int dir) {
		if(idx  < 0 || idx >= magCnt) return;
		visited[idx] = true;
		if(idx-1 >= 0 && magnets[idx][6] != magnets[idx-1][2] && !visited[idx-1]) {
			run(idx-1, dir==1 ? 0 : 1);
		}
		
		if(idx+1 < magCnt && magnets[idx][2] != magnets[idx+1][6] && !visited[idx+1]) {
			run(idx+1, dir==1 ? 0 : 1);
		}
		
		if(dir == 1) { // 시계 방향
			rotate(idx);
		} else { // 반시계 방향
			rotateReverse(idx);
		}
		return;
	}
}


