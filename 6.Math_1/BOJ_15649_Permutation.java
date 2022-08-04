package com.day0804;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15649_Permutation {
	static int N, M;
	static int[] result, numbers;
	static boolean[] visited;
	static void permutation(int idx) {
		if (idx == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if (visited[i] ) continue;
			visited[i] = true;
			result[idx] = i;
			permutation(idx+1);
			visited[i] = false;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[N+1];
		visited = new boolean[N+1];
		result = new int[M];
		for (int i = 1; i <= N; i++) {
			numbers[i] = i;
		}
		permutation(0);
		
	}
}
