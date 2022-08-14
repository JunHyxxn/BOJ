package com.day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16926 {
	static int N, M, R;
	static int[][] arr;
	
	static void Rotate(int k) {
		int temp=arr[k][k];
		int temp2=0;
		// Down
		for (int i = k+1; i < N-k; i++) {
			 temp2 = arr[i][k];
			 arr[i][k] = temp;
			 temp = temp2;
		}

		// Right
		for (int j = k+1; j < M-k; j++) {
			temp2 = arr[N-k-1][j];
			arr[N-k-1][j] = temp;
			temp = temp2;
		}
		
		// Up
		for (int i = N-k-2; i >= k; i--) {
			temp2 = arr[i][M-k-1];
			arr[i][M-k-1] = temp;
			temp = temp2;
		}
		
		// Left
		for (int j = M-k-2; j >= k; j--) {
			temp2 = arr[k][j];
			arr[k][j] = temp;
			temp = temp2;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int r = 0; r < R; r++) {
			for (int k = 0; k < Math.min(N, M)/2; k++) {
				Rotate(k);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
