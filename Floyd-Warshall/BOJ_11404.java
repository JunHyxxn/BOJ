package com.day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11404 {
	/**
	 * BOJ - 11404 플로이드 Gold Ⅳ 
	 * 플로이드 와샬 알고리즘 - O(N^3)
	 * 모든 정점에서 모든 정점까지 도달 가능한 최단 거리 구하는 알고리즘.
	 * */
	static final int inf = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int a, b, c;
		int[][] dist = new int[N+1][N+1];
        // Initialization
		for (int i = 0; i < dist.length; i++) {
			Arrays.fill(dist[i], inf); // inf 로 채운다.
			dist[i][i] = 0; // 자기자신은 0
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
            // a->b 가는 버스 여러 대 가능하다.
			dist[a][b] = Math.min(dist[a][b], c);
		}
		int[][] temp;
		for (int k = 1; k<= N; k++) {
			temp = new int[N+1][N+1];
			temp = dist;

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					temp[i][j] = Math.min(temp[i][j], (temp[i][k] + temp[k][j] < 0 ? inf : temp[i][k] + temp[k][j]));
				}
			}
			dist = temp;
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append((dist[i][j] == inf ? 0 : dist[i][j])  + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
