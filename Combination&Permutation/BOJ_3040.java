package com.day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3040 {
	static int[] numbers, result;
	static final int N = 9, K=7;
	static boolean flag = false;
	static StringBuilder sb;
	
	static void comb(int idx, int start, int state) {
		if (flag) return;
		if(idx == K) {
			int total = 0;
			for (int res : result) {
				total += res;
				sb.append(res+"\n");
			}
			if (total == 100) {
				System.out.print(sb);
				flag = true;
			}
			else sb.delete(0, sb.length());
			return;
		}
		
		for (int i = start; i < N; i++) {
			if((state& 1<<i) == 1) continue;
			result[idx] = numbers[i];
			comb(idx+1, i+1, state|1<<i);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		numbers = new int[N];
		result = new int[K];
		
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		
		comb(0,0,0);
	}
}
