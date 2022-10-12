package com.day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15961_회전초밥 {
	static int N, D, K, C;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int[] sushies = new int[N];
		int[] contain = new int[D+1]; 
		for (int i = 0; i < N; i++) {
			sushies[i] = Integer.parseInt(br.readLine());
		}
		
		int result = Integer.MIN_VALUE;
		contain[C] = 1;
		int cnt = 1;
		for (int i = 0; i < K; i++) {
			int temp = sushies[i];
			if(contain[temp] == 0) cnt++;
			contain[temp] += 1;
		}
		result = Math.max(result, cnt);
		
		for (int i = K; i < N+K-1; i++) {
			int temp = sushies[i-K];
			contain[temp]-=1;
			if(contain[temp] ==0) cnt--;
			
			temp = sushies[i >= N ? i%N : i];
			if(contain[temp] == 0) cnt++;
			contain[temp]+=1;
			result = Math.max(result, cnt);
			
			
		}
		
		System.out.println(result);
	}
}

