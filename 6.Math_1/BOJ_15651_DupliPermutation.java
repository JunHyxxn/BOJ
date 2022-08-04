package com.day0804;

import java.util.Scanner;

public class BOJ_15651_DupliPermutation {
	static int N, M;
	static int[] numbers, result;
	static StringBuilder sb = new StringBuilder();
	static void DupliPerm(int idx) {
		if (idx == M) {
			for (int i = 0; i < M; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			result[idx] = i;
			DupliPerm(idx+1);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		numbers = new int[N+1];
		result = new int[M];
		for (int i = 1; i <= N; i++) {
			numbers[i] = i;
		}
		DupliPerm(0);
		System.out.println(sb);
	}
}
