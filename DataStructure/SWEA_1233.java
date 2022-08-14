package com.day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1233 {
	static String[] arr;
	static boolean checkNumber(String node) {
		if (node.equals("*") || node.equals("/") || node.equals("+") || node.equals("-") ) return false;
		else return true;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 10;
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			arr = new String[N+1];
			int numCnt = 0, operCnt = 0;
			for (int i = 0; i < N; i++) {
				String[] temp = br.readLine().split(" ");
				int idx = Integer.parseInt(temp[0]);
				String node = temp[1];
				if (checkNumber(node)) numCnt++;
				else operCnt++;
				arr[idx] = node;
			}
			if(N%2==0) {
				sb.append("#" + t + " " + 0 + "\n");
				continue;
			}
			if(numCnt-1 == operCnt) {
				sb.append("#" + t + " " + 1 + "\n");
			} else {
				sb.append("#" + t + " " + 0 + "\n");
			}
		}
		
		System.out.println(sb);
	}
}
