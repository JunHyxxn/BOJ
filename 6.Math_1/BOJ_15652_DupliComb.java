package com.day0804;

import java.util.Scanner;

public class BOJ_15652_DupliComb {
	static int N, M;
	static int[] numbers, result;
	static StringBuilder sb = new StringBuilder();
	
	static void dupliComb(int idx, int start) {
		if (idx == M) {
			for (int i = 0; i < M; i++) {
				sb.append(result[i] + " " );
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i <= N; i++) {
			result[idx] = i;
			dupliComb(idx+1, i);
		}
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		numbers = new int[N+1];
		for (int i = 1; i <= N; i++) {
			numbers[i] = i;
		}
		result = new int[M];
		dupliComb(0, 1);
		System.out.println(sb); 
	}
}
