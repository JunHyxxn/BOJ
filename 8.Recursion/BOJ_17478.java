package com.day0801;

import java.util.Scanner;

public class BOJ_17478 {
	static String[] strs =("\"재귀함수가 뭔가요?\"\n"+
						"\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n"+
						"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n"+
						"그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"").split("\n");
	static int N;
	static String prefix = "____";
	static void recur(int k) {
		if(k == N) {
			String temp = "";
			for (int i = 0; i < k; i++) {
				temp = temp.concat(prefix);
			}
			System.out.println(temp + "\"재귀함수가 뭔가요?\"");
			System.out.println(temp + "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			System.out.println(temp + "라고 답변하였지.");
			return;		
		}
		String temp = "";
		for (int i = 0; i < k; i++) {
			temp = temp.concat(prefix);
		}
		for (String str : strs) {
			System.out.println(temp+str);
		}
		recur(k+1);
		System.out.println(temp + "라고 답변하였지.");
		return;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		recur(0);

	}
}
