package com.day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
	static int R, C;
	static int[][] tomatoes, dxdy = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		tomatoes = new int[R][C];
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[R][C];
		int total = R * C;
		int nowTomato = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				tomatoes[i][j] = Integer.parseInt(st.nextToken());
				if (tomatoes[i][j] == 1) {
					nowTomato++;
					queue.add(new int[] { i, j });
					visited[i][j] = true;
				} else if (tomatoes[i][j] == -1) {
					total--;
				}
			}
		}

//		if(total == nowTomato) {
//			System.out.println(0);
//			return;
//		}
		int days = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] temp = queue.poll();
				int x = temp[0], y = temp[1];
				for (int j = 0; j < 4; j++) {
					int nx = x + dxdy[j][0], ny = y + dxdy[j][1];
					if (!isValid(nx, ny) || visited[nx][ny])
						continue;
					if (tomatoes[nx][ny] == 0) {
						visited[nx][ny] = true;
						nowTomato++;
						queue.offer(new int[] { nx, ny });
					}
				}
			}
			days++;
		}
		if(nowTomato == total) System.out.println(--days);
		else System.out.println(-1);
	}

	static boolean isValid(int x, int y) {
		return !(x < 0 || x >= R || y < 0 || y >= C);
	}
}
