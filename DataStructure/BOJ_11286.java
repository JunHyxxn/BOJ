package com.day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_11286 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				int res = Math.abs(o1) - Math.abs(o2);
				if (res == 0) {
					return o1 - o2;
				}
				return res;
			}
		});
		int cmd;
		for (int i = 0; i < N; i++) {
			cmd = Integer.parseInt(br.readLine());
			if(cmd == 0) {
				Integer temp = pq.poll();
				sb.append((temp == null ? 0 : temp)  + "\n");
			}else {
				pq.add(cmd);
			}
		}
		System.out.println(sb);
	}
}
