package com.day0801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SWEA_1289 {
	public static void main(String[] args)  throws IOException{
		// Ver. Prof
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int t = 1; t <=T; t++) {
			String data = sc.next();
			int cnt = 0;
			// Init Value = 000...
			// 시작이 1이면 바뀐거 +1
			if(data.startsWith("1")) {
				cnt++;
			}
			for (int i = 0; i < data.length()-1; i++) {
				if(data.charAt(i) != data.charAt(i+1)) {
					cnt++;
				}
			}
			System.out.println("#" + t + " " + cnt);
		}
		
		
		
		// MyVersion
//		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//		
//		int T = Integer.parseInt(bf.readLine());
//		for (int t = 1; t <= T; t++) {
//			String[] temp = bf.readLine().split("");
//			int[] line = new int[temp.length];
//			int[] init = new int[temp.length];
//			for (int i = 0; i < temp.length; i++) {
//				line[i] = Integer.parseInt(temp[i]);
//				init[i] = 0;
//			}
//			int cnt = 0;
//			for (int i = 0; i < init.length; i++) {
//				if(line[i] == init[i]) continue;
//				int changed = line[i];
//				for (int j = i; j < init.length; j++) {
//					init[j] = changed;
//				}
//				cnt += 1;
//			}
//			
//			System.out.println("#" + t + " " + cnt);
//		}
	}
}
