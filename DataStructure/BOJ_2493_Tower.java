package com.day0801;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;


public class BOJ_2493_Tower {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		Stack<List<Integer>> towers = new Stack<List<Integer>>();
		StringBuilder answer = new StringBuilder();
		st =new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int height = Integer.parseInt(st.nextToken());
			if(towers.empty()) {
				towers.add(Arrays.asList(height, i));
				answer.append("0 ");
			} else {
				while(true) {
					if (towers.empty()) {
						answer.append("0 ");
						towers.push(Arrays.asList(height, i));
						break;
					}
					List<Integer> top = towers.peek();
					if (top.get(0) > height) {
						answer.append(top.get(1) + " ");
						towers.push(Arrays.asList(height, i));
						break;
					} else {
						towers.pop();
					}
				}
			}
		}
		
		System.out.println(answer);
	}
}
