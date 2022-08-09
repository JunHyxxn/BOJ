package com.day0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1228 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		List<Integer> passwords;
		int T = 10;
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			passwords = new ArrayList<>();
			while(st.hasMoreTokens()) {
				passwords.add(Integer.parseInt(st.nextToken()));
			}
			
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			List<Integer> temp;
			while (M>0) {
				st.nextToken(); // I 버리기
				int idx = Integer.parseInt(st.nextToken());
				int size = Integer.parseInt(st.nextToken());
				temp = new LinkedList<>();
				for (int i = 0; i < size; i++) {
					temp.add(Integer.parseInt(st.nextToken()));
				}
				
				passwords.addAll(idx, temp);
				M--;
			}
			
			sb.append("#"+t + " ");
			for (int i = 0; i < 10; i++) {
				sb.append(passwords.get(i) + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
