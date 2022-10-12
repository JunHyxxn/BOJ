package com.day1002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2632_피자판매 {
	static int order, N, A, B, M;
	static int[] nums, tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		order = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		nums = new int[A];
		M = A;
		N = 1<<base2log(4*A-1);
		tree = new int[N];
		int totalA = 0;
		for (int i = 0; i < A; i++) {
			nums[i] = Integer.parseInt(br.readLine());
			totalA += nums[i];
		}
		initSegmentTree(1, 0, 2*A-1);
		
		int[] posA = new int[totalA+1];
		posA[0] = 1;
		posA[totalA] = 1;
		for (int i = 0; i < A-1; i++) {
			for (int j = 0; j < A; j++) {
				int sum = find(0, 2*A-1, j, j+i, 1);
				posA[sum]+=1;
			}
		}
		
		nums = new int[B];
		M = B;
		N = 1<<base2log(4*B-1);
		tree = new int[N];
		int totalB = 0;
		for (int i = 0; i < B; i++) {
			nums[i] = Integer.parseInt(br.readLine());
			totalB += nums[i];
		} 
		initSegmentTree(1, 0, 2*B-1);
		int[] posB = new int[totalB+1];
		posB[0] = 1;
		posB[totalB] = 1;
		for (int i = 0; i < B-1; i++) {
			for (int j = 0; j < B; j++) {
				int sum = find(0, 2*B-1, j, j+i, 1);
				posB[sum]+=1;
			}
		}
		
		int cnt = 0;
		for (int i = 0; i <= order/2; i++) {
			if(isRange(i, posA.length) && isRange(order-i, posB.length)) {
				cnt += posA[i]*posB[order-i];
			}
			if(isRange(order-i, posA.length) && isRange(i, posB.length)) {
				if(i == order-i) continue;
				cnt += posA[order-i]*posB[i];
			}
		}
		System.out.println(cnt);
	}
	static boolean isRange(int x, int R) {
		return !(x<0 || x>=R);
	}
	static int base2log(int n) {
		return (int)Math.ceil(Math.log10(n) / Math.log10(2));
	}
	
	static int initSegmentTree(int node, int start, int end) {
		if(start == end) {
			tree[node] = nums[start >= M ? start-M : start];
			return nums[start >= M ? start-M : start];
		}
		int mid = (start+end)/2;
		int l_sum = initSegmentTree(node*2, start, mid);
		int r_sum = initSegmentTree(node*2+1, mid+1, end);
		return tree[node] = l_sum+r_sum;
	}
	static int find(int start, int end, int left, int right, int node) {
		if(end < left || right < start) return 0;
		if(left<= start && end <= right) return tree[node];
		
		int mid = (start+end)/2;
		int l_sum = find(start, mid, left, right, node*2);
		int r_sum = find(mid+1, end, left, right, node*2+1);
		return l_sum+r_sum;
	}
}
 