package com.day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class BOJ_2660 {
	/*
	 * BOJ - 2660 회장뽑기 Gold Ⅴ
	 * */
	
	static int score = Integer.MAX_VALUE, N;
	static boolean[] visited; // 방문 여부 판단 
	static List<Integer> candidate; // 회장 가능 후보
	static ArrayList<ArrayList<Integer>> friends; // Graph

	static void BFS(int start) {
		/**
		 * BFS 탐색을 진행한다.
		 * 
		 * BFS 탐색 진행 중 체크한 사람의 수가 N이 되는 순간 종료시키면 된다.
		 * 종료할 떄, 
		 * 1) 현재까지 기록된 회장의 점수보다 낮은 점수일 경우는 후보집단을 초기화한다.
		 * 2) 점수가 같을 경우에는 회장 후보에 해당 사람 추가시켜준다.
		 * */
		Queue<List<Integer>> queue = new ArrayDeque<>(); 
		visited = new boolean[N+1];
		visited[start] = true;
		queue.offer(Arrays.asList(start, 0));
		int cnt = 1;
		while(!queue.isEmpty()) {
			List<Integer> info = queue.poll();
			int now = info.get(0), level = info.get(1);
			
			for(Integer friend : friends.get(now)) {
				if(visited[friend]) continue;
				visited[friend] = true;
				cnt++;
				if (cnt == N) {					
					if(score > level+1) {
						score = level+1;
						candidate = new ArrayList<>();
						candidate.add(start);
					} else if(score == level+1) {
						candidate.add(start);
					}
					return;
				}
				queue.offer(Arrays.asList(friend, level+1));
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		friends = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			friends.add(new ArrayList<>());
		}
		while(true) {
			String[] temp = br.readLine().split(" ");
			if(temp[0].equals("-1")) break;
			int a = Integer.parseInt(temp[0]), b = Integer.parseInt(temp[1]);
			friends.get(a).add(b);
			friends.get(b).add(a);
		}
		for (int i = 1; i <= N; i++) {
			BFS(i);
		}
		sb.append(score + " " + candidate.size()+"\n");
		candidate.forEach(c -> sb.append(c+" "));
		System.out.println(sb);
	}
}
