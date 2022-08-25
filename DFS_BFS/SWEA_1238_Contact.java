package com.day0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_1238 {
	static int[] result;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 10;
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int source = Integer.parseInt(st.nextToken());
			Map<Integer, Set<Integer>> adjList = new HashMap<>();
			Set<Integer> temp;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(adjList.containsKey(a)) {
					adjList.get(a).add(b);
				}else {
					temp = new HashSet<>();
					temp.add(b);
					adjList.put(a, temp);
				}
			}
			visited = new boolean[101];

			Queue<int[]> queue = new ArrayDeque<>();
			queue.offer(new int[] { source, 0 });
			visited[source] = true;
			result = new int[] { Integer.MIN_VALUE, 0 };
			int[] info = new int[2];
			while (!queue.isEmpty()) {
				info = queue.poll();
				int now = info[0], level = info[1];
				if (result[1] == level) {
					if (result[0] < now)
						result = info;
				} else {
					result = info;
				}
				if(!adjList.containsKey(now) ) continue;
				for (Integer nxt : adjList.get(now)) {
					if (visited[nxt])
						continue;
					visited[nxt] = true;
					queue.offer(new int[] { nxt, level + 1 });
				}
			}
			sb.append("#" + t + " " + result[0]+ "\n");
		}
		System.out.println(sb);
	}
}
