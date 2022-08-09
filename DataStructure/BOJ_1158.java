package com.day0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_1158 {
	static int N, K;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Deque<Integer> deque = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			deque.addLast(i);
		}
		int temp;
		sb.append("<");
		while(!deque.isEmpty()) {
			for (int i = 0; i < K-1; i++) {
				temp = deque.pollFirst();
				deque.addLast(temp);
			}
			temp = deque.poll();
			sb.append(temp + ", ");
		}
	
		sb.setCharAt(sb.length()-2, '>');
		System.out.println(sb);
	}

}
