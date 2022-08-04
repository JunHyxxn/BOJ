package com.day0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BOJ_17298 {
	public static void main(String[] args) throws IOException{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int[] nums = new int[N];
		int[] nge = new int[N];
		String[] str = bf.readLine().split(" ");
		Stack<List<Integer>> stack = new Stack<>();
		int i = 0;
		stack.push(Arrays.asList(Integer.parseInt(str[0]) , i++));
		int temp;
		for (int j = 1; j < str.length; j++) {
			
			temp = Integer.parseInt(str[j]);
			if (stack.peek().get(0) > temp) {
				stack.push(Arrays.asList(temp , i++));				
			}else {
				while(!stack.empty()) {
					if (stack.peek().get(0) <temp) {
						List<Integer> out = stack.pop();
						nge[out.get(1)] = temp;
					}else {
						break;
					}
				}
				stack.push(Arrays.asList(temp , i++));								
			}
		}
		
		for (List<Integer> list : stack) {
			nge[list.get(1)] = -1;
		}
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < nge.length; j++) {
			sb.append(nge[j] + " ");
		}
		System.out.println(sb);
	}
}
