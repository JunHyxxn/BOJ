package com.day0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1074 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		System.out.println(solve(N, r, c));
		
	}
	static long solve(int N, int r, int c) {
		if (N == 1) {
			return 2*r + c;
		}
		long result = 0;
		int mid = (int)Math.pow(2, N)/2;
		if(r < mid && c < mid) { // 1사분면
			result += solve(N-1, r, c);
		} else if(r < mid && c >= mid) { // 2 사분면
			result += (long)Math.pow(4, N-1) + solve(N-1, r, c-mid);
		}else if(r >= mid && c < mid) { // 3 사분면
			result += (long)Math.pow(4, N-1)*2 + solve(N-1, r-mid, c);
		}else if(r >= mid && c >= mid) { // 4 사분면
			result += (long)Math.pow(4, N-1)*3 + solve(N-1, r-mid, c-mid);
		}
		
		return result;
	}
}
