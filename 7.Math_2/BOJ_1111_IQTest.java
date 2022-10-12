package com.day1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1111_IQTest {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		if (N==1) {
			System.out.println("A");
			return;
		} else if(N==2) {
			if(numbers[0] == numbers[1]) {
				System.out.println(numbers[0]);
			} else {
				System.out.println("A");
			}
			return;
		} else {
			int x = numbers[0], y = numbers[1], z = numbers[2];
			int a = 0, b = 0;
			if((x-y) == 0) {
				if(x==y && x==z) {
					a = 0; b = x;
				} else {
					System.out.println("B");
					return;
				}
			} else {
				if((y-z)%(x-y) != 0) {
					System.out.println("B");
					return;
				}
				a = (y-z)/(x-y);
				b = y - a*x;
			}
			
			boolean flag = true;
			for (int i = 0; i < N-1; i++) {
				if(numbers[i] * a + b != numbers[i+1]) {
					flag = false;
					break;
				}
			}
			
			if(flag) {
				System.out.println(numbers[N-1] * a + b);
			} else {
				System.out.println("B");
			}
		}
	}

}
