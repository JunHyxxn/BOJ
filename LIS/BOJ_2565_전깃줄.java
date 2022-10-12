package com.day1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2565_전깃줄 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] connection = new int[501];
		int M = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			connection[a] = b;
			M = Math.max(M, a);
			M = Math.max(M, b);
		}
		
		int[] state = Arrays.copyOfRange(connection, 0, M+1);
		// lis - longest increasing subsequence
		int[] lis = new int[M+1];
		int result = Integer.MIN_VALUE;
		for (int i = 1; i <= M; i++) {
			if(state[i] == 0) continue;
			lis[i] = 1;
			for (int j = 1; j < i; j++) {
				if(state[i] > state[j]) {
					lis[i] = Math.max(lis[i], lis[j]+1);
				}
			}
			result = Math.max(result, lis[i]);
		}
		
		System.out.println(N-result);
	}

}
