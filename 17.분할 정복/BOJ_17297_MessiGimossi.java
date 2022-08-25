package com.day0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_17297 {
	static long LIMIT = (long)Math.pow(2, 30);
	static final char[] base = " Messi Gimossi".toCharArray();
	static List<Long> dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long M = Long.parseLong(br.readLine());
		if(M <= 13) {
			System.out.println(base[(int)M] == ' ' ? "Messi Messi Gimossi" : base[(int)M]);
			return;
		}
		// init
        dp = new ArrayList<>();
        dp.add(5L);
        dp.add(13L);
        int size = 2;
        while(true) {
        	long temp = dp.get(size-1) + dp.get(size-2)+1;
        	if (temp >= LIMIT) break;
        	dp.add(temp);
        	size++;
        }
        dp.add(dp.get(size-2) + dp.get(size-1));
        
    	// 시작 위치 찾고  solve
    	int start = 2;
    	for (int i = 2; i <= size; i++) {
			if(dp.get(i) >= M) {
				start = i;
				break;
			}
		}
    	char res = solve(M, start);
    	System.out.println(res == ' ' ? "Messi Messi Gimossi" : res);
    }
	
	static char solve(long num, int idx) {
		if(num < 14) {
			return base[(int)num];
		}
		char res=' ';
		long left = dp.get(idx-1); // 긴 쪽
		long right = dp.get(idx-2); // 짧은 쪽
		if (num <= left) {
			res = solve(num, idx-1);
		} else if(left+1 == num) return res = ' ';
		else {
			res = solve(num-left-1, idx-2);
		}
	
		return res;
	}
}
