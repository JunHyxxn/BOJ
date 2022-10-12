package com.day0930;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class BOJ_1600_말이되고픈원숭이 {
	static int K, W, H, result;
	static int[][] map, dxdy;
	static int[][][] dist;
	static {
		dxdy = new int[][] { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 },
			{ -1, -2 }, { -2, -1 }, { -2, 1 }, { -1, 2 },
				{ 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 } };
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		dist = new int[H][W][K+1];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				Arrays.fill(dist[i][j], Integer.MAX_VALUE);
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		// PQ
		result = Integer.MAX_VALUE;
		System.out.println(BFS());
		System.out.println(result);

	}

	static boolean isValid(int x, int y) {
		return !(x < 0 || x >= H || y < 0 || y >= W);
	}

	static int BFS() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[3] - o2[3];
			}

		});
		dist[0][0][0] = 0;
		pq.offer(new int[] { 0, 0, 0, 0 });
		while (!pq.isEmpty()) {
			int[] state = pq.poll();
			int x = state[0], y = state[1], jump = state[2], time = state[3];
			if (x == H - 1 && y == W - 1) {
				return time;
			}

			int posMove = jump < K ? dxdy.length : 4;
			for (int i = 0; i < posMove; i++) {
				int nx = x + dxdy[i][0], ny = y + dxdy[i][1];
				if (!isValid(nx, ny) || dist[nx][ny][(i >= 4 ? jump + 1 : jump)] <= time + 1 || map[nx][ny] == 1)
					continue;
				dist[nx][ny][(i >= 4 ? jump + 1 : jump)] = time + 1;
				pq.offer(new int[] { nx, ny, (i >= 4 ? jump + 1 : jump), time + 1 });
			}
		}
		
		return -1;
	}

}
