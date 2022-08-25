package com.day0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_13023 {
	static int N, M;
	static boolean flag, visited[];
	static List<List<Integer>> adjList;
	public static void main(String[] args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			adjList.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList.get(a).add(b);
			adjList.get(b).add(a);
		}
		
		flag = false;
		for (int i = 0; i < N; i++) {
			if(flag) {
				System.out.println(1);
				return;
			}
			visited = new boolean[N];
			visited[i] = true;
			BT(i, 1);
		}
		System.out.println(0);
	}
	
	static void BT(int now, int depth) {
		if(depth == 5) { // 5개 방문 시 더 이상 방문하지 않는다.
			flag = true; // 여러개 찾을 필요 없기 때문에 종료시키기 위한 flag
			return;
		}
		// DFS 탐색 방법으로 진행한다.
		for (int nxt : adjList.get(now)) {
			if(visited[nxt]) continue;
			visited[nxt] = true;
			BT(nxt, depth+1);
			visited[nxt] = false; // 방문 완료한 정점은 다시 방문 해제시켜준다.
		}
	}
}
