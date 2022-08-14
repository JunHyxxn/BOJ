package com.day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2263 {
	static List<Integer> inOrder; // in-order traversal
	static StringBuilder sb;
	// Left Sub Tree & Right Sub Tree 분리한다.
	static void solve(int inLeft, int inRight, List<Integer> post) {
		// inLeft > inRight 시 종료
		if (inLeft > inRight ) return;
		if (inLeft == inRight) { // 왼쪽으로 쭉 가다가 하나만 남았다면 더 이상 왼쪽으로 갈 자식이 없다 -> 출력 후 종료
			sb.append(post.get(0) + " ");
			return;
		}

		// 현재 Sub Tree 중 Root는 post 에서 마지막에 방문된 노드이다.
		int subRoot = post.get(post.size() -1 );
		// 서브트리의 root는 바로 방문 -> 출력한다. 
		sb.append(subRoot + " ");
		// in-order traversal 에서 방문된 순서를 찾아서 그 index를 기준으로 왼쪽은 Left Sub Tree - 오른쪽은 Right Sub Tree
		int idx = inOrder.indexOf(subRoot);
		solve(inLeft, idx-1, post.subList(0, idx - inLeft)); // 왼쪽 서브 트리 탐색
		solve(idx+1, inRight, post.subList(idx-inLeft, post.size() - 1)); // 오른쪽 서브 트리 탐색
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		inOrder = new ArrayList<>();
		List<Integer> postOrder = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			inOrder.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			postOrder.add(Integer.parseInt(st.nextToken()));
		}
		
		solve(0, N-1, postOrder);
		System.out.println(sb);
	}
}
