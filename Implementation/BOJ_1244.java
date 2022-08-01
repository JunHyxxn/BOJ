package com.day0801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1244 {
	static int[] switches;
	
	// 남자 - 배수 위치 토글
	static void multiple_toggle(int index) {
		for (int i = index; i < switches.length; i+=index) {
			switches[i] ^= 1; 
		}
	}
	
	// 여자 - 최대 대칭 위치 토글
	static void mirror_toggle(int index) {
		switches[index] ^= 1; // 시작 위치 무조건 토글
		// 양 옆으로 한 번에 탐색하기 위해 더 짧은 범위를 선택한다.
		int range = Math.min(switches.length-index-1, index-1);
		// 양 쪽이 서로 같으면 토글 
		for (int i = 1; i <= range; i++) {
			if(switches[index-i] == switches[index+i]) {
				switches[index-i] ^= 1;
				switches[index+i] ^= 1;
			}else { // 다르면 바로 종료한다
				return;
			}
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int num = Integer.parseInt(st.nextToken());
		switches = new int[num+1];
		st = new StringTokenizer(bf.readLine());
		for (int i = 1; i <= num; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int gender, index;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			gender = Integer.parseInt(st.nextToken());
			index = Integer.parseInt(st.nextToken());
			if(gender == 1) {
				multiple_toggle(index);
			} else if (gender ==2 ){
				mirror_toggle(index);
			}
		}
		// 출력 형식 : 20개씩 끊어서 출력한다
		for (int j = 1; j < switches.length; j++) {
			if(j%20 == 0) {
				System.out.println(switches[j]);
				continue;
			}
			System.out.print(switches[j] + " ");
		}
	}
}


