package com.day1004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_4198_열차정렬 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		
		int[] lis = new int[N];
		int[] lds = new int[N];
		
		
		for (int i = N-1; i >= 0; i--) {
			lis[i] = 1;
			lds[i] = 1;
			for (int j = N-1; j >i; j--) {
				
				// 오름차순 수열
				if(numbers[i] >numbers[j]) {
					lis[i] = Math.max(lis[i], lis[j] + 1);
				}
				
				// 내림차순 수열
				if(numbers[i] < numbers[j]) {
					lds[i] = Math.max(lds[i], lds[j]+1);
				}
			}
		}
		
		int result = Integer.MIN_VALUE;
		if(N ==0) {
			result = 0;
		}else {
			for (int i = 0; i < N; i++) {
				result = Math.max(result, lis[i] + lds[i] - 1);
			}			
		}
		System.out.println(result);
		
	}

}
