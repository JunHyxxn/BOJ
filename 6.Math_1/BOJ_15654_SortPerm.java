
package com.day0804;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15654_SortPerm {
	static int N, M;
	static List<Integer> numbers = new ArrayList<>();
	static int[] result;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	static void perm(int idx) {
		if (idx == M) {
			for (int i = 0; i < M; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] =true;
			result[idx] = numbers.get(i);
			perm(idx+1);
			visited[i] = false;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		result = new int[M];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers.add(Integer.parseInt(st.nextToken()));
		}
		// 정렬
		Collections.sort(numbers);
		
		perm(0);
		System.out.println(sb);
	}
}
