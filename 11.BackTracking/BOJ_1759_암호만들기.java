package com.day0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759 {
	static int L, C;
	static char[] alphas, result;
	static final boolean[] aeiou = new boolean['z'-'a'+1];
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// init aeiou
		aeiou['a'-'a'] = true;
		aeiou['e'-'a'] = true;
		aeiou['i'-'a'] = true;
		aeiou['o'-'a'] = true;
		aeiou['u'-'a'] = true;
		
		result = new char[L];
		alphas = new char[C];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alphas[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(alphas);
		comb(0,0,0,0);
		System.out.println(sb);
	}
	static void comb(int idx, int start, int aCnt, int bCnt) {
		if(idx == L) {
			if (aCnt <1 || bCnt <2) return;
			
			for (int i = 0; i < L; i++) {
				sb.append(result[i]);
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i < C; i++) {
			result[idx] = alphas[i];
			comb(idx+1, i+1, aeiou[alphas[i]-'a'] ? aCnt+1 : aCnt, aeiou[alphas[i]-'a'] ? bCnt : bCnt+1);
		}
	}
}
