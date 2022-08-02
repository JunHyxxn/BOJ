package com.day0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_Flatten {
	public static void main(String[] args) throws IOException{
//		// Ver. MySolution
		int T = 10;
		int N = 100;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= T; t++) {
			// Data Init
			int cnt = Integer.parseInt(bf.readLine());
			int[] boxes = new int[N];
			String[] temp = bf.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				boxes[i] = Integer.parseInt(temp[i]);
			}
			
			// Logic
			int res = Integer.MAX_VALUE;
			for (int i = 0; i < cnt; i++) {
				int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
				int max_idx = 0, min_idx = 0;
				for (int j = 0; j < N; j++) {
					if (max < boxes[j]) {
						max = boxes[j];
						max_idx = j;
					}
					if (min > boxes[j]) {
						min = boxes[j];
						min_idx = j;
					}
				}
				res = Math.abs((max) - (min));
				if (res <= 1) break;
				boxes[max_idx] -= 1;
				boxes[min_idx] += 1;
			}
			// Flatten 후 최종적으로 min-max 판단
			int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				max = max < boxes[i] ? boxes[i] : max;
				min = min > boxes[i] ? boxes[i] : min;
			}
			System.out.println("#" + t + " " + (int)(max-min));
		}
		
	}
}
