package com.day1007;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17472_다리만들기2 {
	static int N, M;
	static int[][] map, dist;
	static boolean[][] visited;
	static int[][] dxdy = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	static class Edge {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
		
	}

	static int[] parents;
	static List<Edge> edgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// island 표시
		visited = new boolean[N][M];
		int number = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0 || visited[i][j])
					continue;
				BFS(i, j, number++);

			}
		}
		dist = new int[number][number];
		for (int i = 0; i < number; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		// dist 계산
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					continue;
				getDistance(i, j);
			}
		}

		edgeList = new ArrayList<>();
		for (int i = 1; i <= number; i++) {
			for (int j = i + 1; j < number; j++) {
				if (dist[i][j] == Integer.MAX_VALUE)
					continue;
				edgeList.add(new Edge(i, j, dist[i][j]));
			}
		}
		edgeList.sort(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.weight - o2.weight;
			}
		});
		
		Edge[] edgeList2 = new Edge[edgeList.size()];
		edgeList.toArray(edgeList2);
		makeSet(number);
		int result = 0;
		int cnt = 0;
		for (Edge edge : edgeList2) {
			if(union(edge.from, edge.to)) {
				cnt++;
				result+= edge.weight;
				if(cnt == number-2) break;
			}
		}
		System.out.println(cnt == number-2 ? result : -1);
	}

	static boolean isValid(int x, int y) {
		return !(x < 0 || x >= N || y < 0 || y >= M);
	}

	static void getDistance(int x, int y) {
		int island = map[x][y];
		for (int i = 0; i < 4; i++) {
			int cost = 0;
			int other = 0;
			int dx = dxdy[i][0], dy = dxdy[i][1];
			int nx = x + dx, ny = y + dy;
			while (isValid(nx, ny)) {
				if (map[nx][ny] == island) {
					cost = 0;
					break;
				} else if (map[nx][ny] != island && map[nx][ny] != 0) {
					other = map[nx][ny];
					break;
				}

				cost++;
				nx = nx + dx;
				ny = ny + dy;
			}
			if (cost < 2 || other == 0)
				continue;

			dist[island][other] = Math.min(dist[island][other], cost);
			dist[other][island] = Math.min(dist[other][island], cost);
		}
	}

	static void BFS(int x, int y, int number) {
		Queue<Point> queue = new ArrayDeque<>();
		visited[x][y] = true;
		map[x][y] = number;
		queue.offer(new Point(x, y));

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dxdy[i][0], ny = now.y + dxdy[i][1];
				if (!isValid(nx, ny) || visited[nx][ny] || map[nx][ny] == 0)
					continue;
				visited[nx][ny] = true;
				map[nx][ny] = number;
				queue.offer(new Point(nx, ny));
			}
		}
	}

	// Union Find Algorithms
	static void makeSet(int V) {
		parents = new int[V];
		for (int i = 1; i < V; i++) {
			parents[i] = i;
		}
	}

	static int findSet(int a) {
		if (parents[a] == a)
			return a;

		parents[a] = findSet(parents[a]);
		return parents[a];
	}

	static boolean union(int a, int b) { // return : true => union 성공
		int aRepresentative = findSet(a);
		int bRepresentative = findSet(b);
		if (aRepresentative == bRepresentative) {
			return false;
		}

		parents[bRepresentative] = aRepresentative;
		return true;
	}
}
