package com.day0802;

import java.util.Scanner;
import java.util.Stack;
import java.io.IOException;

public class BOJ_1662 {
	static int convertNum(String s) {
		if (s.charAt(0) == '*') {
			return Integer.valueOf(s.substring(1, s.length()));
		}
		return Integer.valueOf(s);
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		Stack<String> stores = new Stack<>();
		for (String s : sc.nextLine().split("")) {
			if (s.equals("(")) {
				Stack<String> temp = new Stack<>();
				temp.add(s);
				temp.add(stores.pop());
				StringBuilder remainStr = new StringBuilder();
				int remainLen = 0;
				while (!stores.empty()) {
					if (stores.peek().equals("("))
						break;
					String t = stores.pop();
					if (t.charAt(0) == '*') {
						remainLen += Integer.valueOf(t.substring(1, t.length()));
					} else {
						remainStr.insert(0, t);
					}
				}
				temp.add(String.valueOf(remainLen + remainStr.length()));
				// 다시 담기
				while (!temp.empty()) {
					stores.add(temp.pop());
				}

			} else if (s.equals(")")) {
				StringBuilder remainStr = new StringBuilder();
				int remainLen = 0;
				while (!stores.empty()) {
					if (stores.peek().equals("("))
						break;
					String t = stores.pop();
					if (t.charAt(0) == '*') {
						remainLen += Integer.valueOf(t.substring(1, t.length()));
					} else {
						remainStr.insert(0, t);
					}
				}
				int right = remainLen + Integer.valueOf(remainStr.length());
				String cmd = stores.pop();
				int left;
				if (cmd.equals("(")) {
					left = convertNum(stores.pop());
					right = left * right;
					while (!stores.empty() && !stores.peek().equals("(")) {
						left = convertNum(stores.pop());
						right = left + right;
					}
				} else {
					while (!stores.empty() && !stores.peek().equals("(")) {
						left = convertNum(stores.pop());
						right = left + right;
					}
				}
				stores.add(String.valueOf("*" + right));
			} else {
				stores.add(s);
			}
		}
		int result = 0;
		for (String s : stores) {
			if (s.charAt(0) == '*') {
				result += Integer.valueOf(s.substring(1, s.length()));
				continue;
			}
			result += 1;
		}
		System.out.println(result);
	}
}
