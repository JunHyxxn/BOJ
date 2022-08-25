package com.day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569 {
	static int R, C, H;
	static int[][] dxdy = {{1,0,0},{-1,0,0} ,{0, 0, 1 }, {0, 1, 0 }, {0, 0, -1 }, {0, -1, 0 } };
	static int[][][] tomatoes;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		tomatoes = new int[H][R][C];
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][][] visited = new boolean[H][R][C];
		int total = R * C * H;
		int nowTomato = 0;
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					tomatoes[h][i][j] = Integer.parseInt(st.nextToken());
					if (tomatoes[h][i][j] == 1) {
						nowTomato++;
						queue.add(new int[] { h, i, j });
						visited[h][i][j] = true;
					} else if (tomatoes[h][i][j] == -1) {
						total--;
					}
				}
			}
		}
		int days = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] temp = queue.poll();
				int h = temp[0], x = temp[1], y = temp[2];
				for (int j = 0; j < 6; j++) {
					int nh = h + dxdy[j][0], nx = x + dxdy[j][1], ny=y+dxdy[j][2];
					if (!isValid(nh,nx, ny) || visited[nh][nx][ny])
						continue;
					if (tomatoes[nh][nx][ny] == 0) {
						visited[nh][nx][ny] = true;
						nowTomato++;
						queue.offer(new int[] { nh, nx, ny });
					}
				}
			}
			days++;
		}
		if(nowTomato == total) System.out.println(--days);
		else System.out.println(-1);
	}

	static boolean isValid(int h,int x, int y) {
		return !(h<0 || h >= H || x < 0 || x >= R || y < 0 || y >= C);
	}
}
