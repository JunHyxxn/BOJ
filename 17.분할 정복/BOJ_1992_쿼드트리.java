package com.day0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1992 {
	static int N;
	static int[][] img;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		img = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String[] st = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				img[i][j] = Integer.parseInt(st[j]);
			}
		}
		sb.append("(");
		solve(N, 0,0);
		System.out.println(sb);
	}
	
	static void solve(int n, int x, int y) {
		if (n == 2) {
			String temp = String.valueOf(img[x][y]) + String.valueOf(img[x][y+1]) + String.valueOf(img[x+1][y]) + String.valueOf(img[x+1][y+1]);
			if(temp.equals("1111") || temp.equals("0000")) {
				sb.replace(sb.length()-1, sb.length(), String.valueOf(temp.charAt(0)));
			}else {
				sb.append(temp + ")");
			}
			return;
		}
		int mid = n/2;
		sb.append("(");
		solve(mid, x, y);
		
		sb.append("(");
		solve(mid, x, y+mid);
		
		sb.append("(");
		solve(mid, x+mid, y);

		sb.append("(");
		solve(mid, x+mid, y+mid);
		
		String check = sb.substring(sb.length()-4, sb.length()).toString();
		if (check.equals("1111") || check.equals("0000")) {
			sb.replace(sb.length()-5, sb.length(), String.valueOf(check.charAt(0)));
			return;
		}
		sb.append(")");
		
	}
}
