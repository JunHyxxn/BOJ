package com.day0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17471_GerryMandering {
	static int N, result;
	static int[] peoples;
	static boolean[] visited;
	static List<List<Integer>> adjList;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// Input 처리
		N = Integer.parseInt(br.readLine());
		result = Integer.MAX_VALUE;
		peoples = new int[N]; 
		adjList = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			peoples[i] = Integer.parseInt(st.nextToken());
			adjList.add(new ArrayList<>());
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				adjList.get(i).add(Integer.parseInt(st.nextToken()) - 1);
			}
		}
		
		subset(0,0);
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
	// 10명의 사람을 두 집단으로 나눈다. -> subset 이용
	static void subset(int idx, int state) {
		if(idx == N) {
			// 0과 111..1 은 하나의 집단만 존재하므로 종료시킨다.
			if(state ==0 || state == (1<<N)-1) return;
			// 두 집단으로 나눴다면 두 집단이 자신의 집단 내에서 완전 연결 되는지 확인한다.
			if(check(state)) { // 두 집단 모두 완전 연결되었다면 인구수 차이를 계산한다.
				int A = 0, B = 0;
				for (int i = 0; i < N; i++) {
					// 1 => A 집단 ,   0 => B 집단
					if((state & 1<<i) == 0) B += peoples[i]; 
					else A += peoples[i];
				}
				// result update
				result = Math.min(result, Math.abs(A - B));
			}
			return;
		}
		
		subset(idx+1, state); // idx번째 사람 0번 집단으로 포함
		subset(idx+1, state|1<<idx); // idx번째 사람 1번 집단으로 포함
	}
	
	// 두 집단이 각자 집단 내부에서 완전 연결되는지 체크
	static boolean check(int state) {
		visited = new boolean[N]; // visited 배열 초기화
		int cntA = 0, cntB = 0; // 인구수 체크
		for (int i = 0; i < N; i++) {
			if((state & 1<<i) != 0) { // 1번 집단 BFS 시작
				cntA = BFS(i, 1, state);
				break;
			}
		}
		for (int i = 0; i < N; i++) {
			if((state & 1<<i) == 0) { // 0번 집단 BFS 시작
				cntB = BFS(i, 0, state);
				break;
			}
		}
		// 두 집단의 구역 수 합이 전체 구역의 수와 같다면 true
		return cntA+cntB == N ? true : false;
	}
	// BFS
	// BFS 돌면서 해당 구역에 현재 탐색하고 있는 집단 bit와 같다면 탐색 진행
	static int BFS(int start, int bit, int state) {
		int cnt = 1;
		Queue<Integer> queue = new ArrayDeque<>();
		visited[start] = true;
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for (Integer nxt : adjList.get(now)) {
				if(visited[nxt]) continue;
				// 현재 탐색중인 구역 nxt이 탐색중인 집단 bit라면 탐색 진행
				if((bit<<nxt) == (state&(1<<nxt))) { 
					visited[nxt] = true;
					cnt++;
					queue.offer(nxt);
				}
			}
		}
		return cnt;
	}
}
