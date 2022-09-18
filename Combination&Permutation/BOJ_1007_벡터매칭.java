package com.day0917;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1007_벡터매칭 {
	static class Point { 
		double x;
		double y;
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
	}
	static int N;
	static double result;
	static boolean[] isSelected;
	static Point[] points;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			points = new Point[N];
			isSelected = new boolean[N];
			result = Double.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				points[i] = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			}
			
			comb(0,0);
			sb.append(result+"\n");
		}
		System.out.println(sb);
	}

	static void comb(int cnt, int start) {
		if (cnt == N/2) {
			// comb 완성
			double x = 0.0, y = 0.0;
			for (int i = 0; i < N; i++) {
				if(isSelected[i]) {
					x += points[i].x;
					y += points[i].y;
				}else {
					x -= points[i].x;
					y -= points[i].y;
				}
			}
			result = Math.min(result, Math.sqrt( (x*x) +  (y*y) ) );
			return;
		}

		for (int i = start; i < N; i++) {
			isSelected[i] = true;
			comb(cnt + 1, i+1);
			isSelected[i] = false;
		}
	}
}
