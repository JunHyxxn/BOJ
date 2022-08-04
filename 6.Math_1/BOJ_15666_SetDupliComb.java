package com.day0804;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_15666_SetDupliComb {
	static int N, M;
	static Set<Integer> temp = new HashSet<>();
	static Object[] numbers;
	static Object[] result;
	static StringBuilder sb = new StringBuilder();
	
	static void dupliComb(int idx, int start) {
		if (idx == M) {
			for (int i = 0; i < M; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i < numbers.length; i++) {
			result[idx] = numbers[i];
			dupliComb(idx+1, i);
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = new Integer[M];
		st =new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			temp.add(Integer.parseInt(st.nextToken()));
		}
		
		
		numbers = temp.stream().sorted().toArray();
		dupliComb(0, 0);
		System.out.println(sb);
	}

}
