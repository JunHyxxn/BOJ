package com.day0804;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SWEA_1218 {
	// close - open  : matching data
	static Map<Character, Character> matching = new HashMap<>();
	// Matching data Init
	static {
		matching.put(')', '(');
		matching.put(']', '[');
		matching.put('}', '{');
		matching.put('>', '<');
	}
	// Check Open Char
	static boolean isOpen(char ch) {
		if (ch =='(' || ch =='[' ||ch =='{' ||ch =='<') return true;
		else if (ch ==')' || ch ==']' ||ch =='}' ||ch =='>') return false;
		return false;
	}
	// Check close char - open char Matching
	static boolean isMatching(char open, char close) {
		if (matching.get(close) == open) return true;
		return false;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 10;
		for (int t = 1; t <= T; t++) {
			// 서로 한 번이라도 매칭되지 않으면 유효하지 않다.
			int result = 1;
			// 여는 괄호를 담아둘 스택
			Stack<Character> stack = new Stack<>();
			int N = Integer.parseInt(bf.readLine());
			for (Character ch : bf.readLine().toCharArray()) {
				// 유효하지 않은 경우가 한 번이라도 나오면 종료시킨다.
				if (result == 0) break;
				// 여는 괄호는 스택에 담는다.
				if (isOpen(ch)) stack.add(ch);
				else {
					// 닫는 괄호가 나오면 스택에 마지막으로 들어간 괄호와 매칭여부 판단한다.
					char open = stack.pop();
					if(!isMatching(open, ch)) {
						// 불일치
						result = 0;
					}
				}
			}
		
			sb.append("#"+ t + " " +result + "\n");
		}
		System.out.println(sb);
	}
}
