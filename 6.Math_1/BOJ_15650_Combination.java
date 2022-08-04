package com.day0804;

import java.util.Scanner;

public class BOJ_15650_Combination {
	static int[] result, numbers;
	static boolean[] visited;
	static int N, M;

	static void combination(int idx, int start) {
		if (idx == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}
		for (int i = start; i <= N; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			result[idx] = i;
			combination(idx+1, i);
			visited[i] = false;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		numbers = new int[N+1];
		visited = new boolean[N+1];
		result = new int[M];
		for (int i = 1; i <= N; i++) {
			numbers[i] = i;
		}
		combination(0, 1);
	}
}
