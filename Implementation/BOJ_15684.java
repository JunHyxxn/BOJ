package com.day0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class BOJ_15684 {
	static int[][] ladder;
	static int N, M, H, result;
	static boolean isFinish() {
		int[] orders = new int[N+1];
		for (int i = 1; i < orders.length; i++) {
			orders[i] = i;
		}
		for (int h = 1; h < H+1; h++) {
			for (int i = 1; i < orders.length; i++) {
				int a = orders[i];
				if(ladder[h][a-1] == 1) {
					orders[i] = a-1;
				} else if (a < N && ladder[h][a] == 1) {
					orders[i] = a+1;
				}
			}
		}
		for (int i = 1; i < orders.length; i++) {
			if(orders[i] != i) return false;
		}
		return true;
	}
	static void solve(int cnt, int depth) {
		if (cnt >= result) return;
		if(isFinish()) {
			result = cnt;
			return;
		}
		
		for (int d = depth; d <= H; d++) {
			for (int i = 1; i < N; i++) {
				if(ladder[d][i] != 0) continue;
				if((i-1 == 0 || ladder[d][i-1]!=1) && (i+1==N || ladder[d][i+1] != 1)) {
					ladder[d][i] = 1;
					solve(cnt+1, d);
					ladder[d][i] = 0;
				}
			}
		}
		// for문 돌아서 depth 늘리는게 재귀 한 번 더 타는것보다 훨씬 속도가 빠르다.
//		solve(cnt, depth+1);
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		result = 4;
		int h, a;
		ladder = new int[H+1][N+1];
		// ladder Init
		for (int d = 0; d <= H; d++) {
			ladder[d][0] = 2; // 0 -> 1 막아둔다.
		}
		// Input Init
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			ladder[h][a] = 1; // 이동 가능
			ladder[h][a-1] = 2; // 이동 불가능 & 사다리 놓기 불가능
			ladder[h][a+1] = 2; // 이동 불가능 & 사다리 놓기 불가능
		}
		solve(0, 1);
		System.out.println((result >= 4 ? -1 : result));
	}
}
