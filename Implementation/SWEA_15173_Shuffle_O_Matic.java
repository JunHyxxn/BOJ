package com.day0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_15173_Shuffle_O_Matic {
	static int N, mid, result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			mid = N/2;
			result = Integer.MAX_VALUE;
			int[] cards = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				cards[i] = Integer.parseInt(st.nextToken());
			}
			solve(0, cards);
			sb.append("#" + t + " " + (result==Integer.MAX_VALUE ? -1 : result) + "\n");
		}
		System.out.println(sb);
	}
	static boolean checkFinish(int[] cards) {
		int prev = cards[1];
		int diff;
		for (int i = 2; i <= N; i++) {
			diff = Math.abs(prev - cards[i]);
			if(diff != 1) return false;
			prev = cards[i];
		}
		return true;
	}
	
	static void solve(int depth, int[] cards) {
		if(result < depth) return;
		if(checkFinish(cards)) {
			result = Math.min(result,  depth);
			return;
		}
		if(depth == 5) {
			return;
		}
		for (int i = 1; i < N; i++) {
			int[] temp = cards.clone();
			for (int j = 1; j <= i; j++) {
				if(j > mid) break;
				shuffle(j, temp);
			}
			if(i > mid) {
				int t = mid-1;
				for (int j = 0; j < i-mid; j++) {
					shuffle(t-j, temp);
				}
			}
			solve(depth+1, temp);
		}
	}
	
	static int[] shuffle(int x, int[] cards) {
		int start = mid - x + 1;
		int temp;
		for (int i = start; i < start+(2*x); i=i+2) {
			temp = cards[i];
			cards[i] = cards[i+1];
			cards[i+1] = temp;
		}
		return cards;
	}
}
