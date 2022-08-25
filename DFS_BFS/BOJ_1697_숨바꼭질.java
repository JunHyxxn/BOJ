package com.day0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class BOJ_1697 {
	static int N, K;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		
		boolean[] visited = new boolean[100001];
		visited[N] = true;
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] {N, 0});
		while(!queue.isEmpty()) {
			int[] info = queue.poll();
			int now = info[0], time = info[1];
			if (now < 0 || now > 100000) continue;
			if(now == K) {
				System.out.println(time);
				return;
			}
			
			if (isValid(now-1) &&!visited[now-1]) {
				visited[now-1] = true;
				queue.offer(new int[] {now-1, time+1});
			}
			if (isValid(now+1)&&!visited[now+1]) {
				visited[now+1] = true;
				queue.offer(new int[] {now+1, time+1});
			}
			if (isValid(now*2) && !visited[now*2]) {
				visited[now*2] = true;
				queue.offer(new int[] {now*2, time+1});
			}
		}
	}
	static boolean isValid(int x) {
		return !(x<0 || x> 100000);
	}
}
