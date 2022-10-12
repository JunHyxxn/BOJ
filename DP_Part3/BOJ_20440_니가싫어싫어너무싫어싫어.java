package com.day1002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_20440_니가싫어싫어너무싫어싫어 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Map<Integer, Integer> inOut = new HashMap<Integer, Integer>();
		Set<Integer> numbers = new HashSet<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			numbers.add(s);
			numbers.add(e);
			if(inOut.get(s) == null) {
				inOut.put(s, 1);
			} else {
				inOut.put(s, inOut.get(s)+1);
			}
			
			if(inOut.get(e) == null) {
				inOut.put(e, -1);
			} else {
				inOut.put(e, inOut.get(e)-1);
			}
		}
		
		List<Integer> list = new ArrayList<>(numbers);
		list.sort((l1, l2) -> l1-l2);
		int[] dp = new int[list.size()];
		dp[0] += inOut.get(list.get(0));
		int total = dp[0] , cnt = dp[0];
		int tem = list.get(0), txm = 0;
		for (int i = 1; i < list.size(); i++) {
			dp[i] = dp[i-1] + inOut.get(list.get(i));
			cnt = dp[i];
			if(cnt > total) {
				tem = list.get(i);
				total = cnt;
				txm = 0;
			}else if (cnt < total && cnt - inOut.get(list.get(i)) == total && txm == 0) {
				txm = list.get(i);
			}
		}
		System.out.println(total);
		System.out.println(tem + " " + txm);
	}
}



