package com.day0920;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_2383_점심식사시간 {
	static int N, M, result;
	static List<Point> people;
	static int[][] stairs;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			people = new ArrayList<>();
			stairs = new int[2][3];
			int k = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int temp = Integer.parseInt(st.nextToken());
					if(temp == 1) {
						people.add(new Point(i,j));
					} else if(temp > 1) {
						stairs[k++] = new int[] {i,j,temp};
					}
				}
			}
			M = people.size();
			result = Integer.MAX_VALUE;
			subset(0, 0, 0, 0);
			sb.append("#"+t+ " "+result+ "\n");
		}
		System.out.println(sb);
	}
	static void subset(int idx, int state, int group1, int group2) {
		if(idx == M) {
			int[] s1 = new int[group1];
			int[] s2 = new int[group2];
			int s1Cnt = 0;
			int s2Cnt = 0;
			for (int i = 0; i < M; i++) {
				if((state & (1<<i)) != 0) {
					s1[s1Cnt++] = calc(people.get(i), stairs[0]);
				}else {
					s2[s2Cnt++] = calc(people.get(i), stairs[1]);
				}
			}
			
			Arrays.sort(s1);
			Arrays.sort(s2);
			
			if(s1.length >= 4) {
				for (int i = 3; i < s1.length; i++) {
					s1[i] = s1[i]-s1[i-3] < stairs[0][2] ? s1[i]+(stairs[0][2]-(s1[i]-s1[i-3])) : s1[i];
				}
			}
			
			if(s2.length >= 4) {
				for (int i = 3; i < s2.length; i++) {
					s2[i] = s2[i]-s2[i-3] < stairs[1][2] ? s2[i]+(stairs[1][2]-(s2[i]-s2[i-3])) : s2[i];
				}
			}
			int temp = Math. max(s1Cnt==0 ? 0 : s1[s1Cnt-1], s2Cnt == 0 ? 0 : s2[s2Cnt-1]);
			result = Math.min(result,  temp);
			return;
		}
		
		subset(idx+1, state|(1<<idx), group1+1, group2);		
		subset(idx+1, state, group1, group2+1);
	}
	static int calc(Point p, int[] s) {
		return Math.abs(p.x - s[0]) + Math.abs(p.y - s[1]) + s[2] + 1;
	}
}
