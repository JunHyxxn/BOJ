package com.day0803;

import java.util.Scanner;

public class BOJ_1929 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt(), N = sc.nextInt();
		boolean[] nums = new boolean[N+1];
		// 초기화
		nums[0] = true;
		nums[1] = true;
		
		for (int i = 2; i < Math.sqrt(N)+1; i++) {
			for (int j = i*2; j < nums.length; j=j+i) {
				nums[j] = true;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = M; i < nums.length; i++) {
			if(nums[i] == false) {
				sb.append(i+"\n");
			}
		}
		System.out.println(sb);
	}
}
