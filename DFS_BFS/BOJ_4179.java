package com.day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4179 {
	/**
	 * BOJ - 4179 불! Gold Ⅳ
	 */
	static int R, C;
	static char[][] map;
	static int[][] dxdy = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int[][] state; // 각 위치의 상태를 표시할 배열 => 불이 붙으면 Integer.MAX_VALUE 를 넣어줘서 지훈이가 현재 위치에서 불 탔는지 판단해준다.

	// 탈출 성공 여부
	static boolean isValid(int x, int y) {
		return !(x < 0 || x >= R || y < 0 || y >= C);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		Queue<int[]> fires = new ArrayDeque<>();
		Queue<int[]> jihun = new ArrayDeque<>();
		map = new char[R][C];
		state = new int[R][C];
		for (int i = 0; i < R; i++) {
			String temp = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = temp.charAt(j);
				if (map[i][j] == 'J') {
					jihun.offer(new int[] { i, j, 0 });
					map[i][j] = '.';
				} else if (map[i][j] == 'F') {
					fires.offer(new int[] { i, j, 0 });
				}
			}
		}

		// BFS 시작
		while (true) {
			// 지훈이가 더이상 움직일 수 없다면 종료 => IMPOSSIBLE
			if(jihun.isEmpty()) break;
			int time = jihun.peek()[2]; 
			while (!jihun.isEmpty() && jihun.peek()[2] == time) { // 큐에서 이번 타임에 움직일 수 있는 만큼만 이동한다. 
				int[] jihunInfo = jihun.poll();
				int nowX = jihunInfo[0], nowY = jihunInfo[1], level = jihunInfo[2];
				// 지훈이가 이동한 후 불이 붙으면 죽게 된다. 이 경우의 지훈이의 위치는 죽었다고 판단하고 건너뛴다.
				if (state[nowX][nowY] == Integer.MAX_VALUE) continue;
				for (int i = 0; i < 4; i++) {
					int nx = nowX + dxdy[i][0], ny = nowY + dxdy[i][1];
					if (!isValid(nx, ny)) { // escape
						System.out.println(level + 1);
						return;
					}
					// state에 이미 움직인 적이 있거나 불타는 중이라면 탐색하지 않는다.
					// 즉 유효한 범위, 이동가능한 위치, 방문한적 없고, 불타지 않는 위치일 경우만 이동한다. 
					if (isValid(nx, ny) && map[nx][ny] == '.' && state[nx][ny] == 0) {
						state[nx][ny] = level+1; // 몇 번째에 갔는지 기록한다.
						jihun.add(new int[] { nx, ny, level + 1 });
					}
				}
			}
			
			// 불은 더이상 이동할 곳이 없더라도 지훈이는 움직일 수 있으니 종료시키지 않고 건너 뛴다.
			if(fires.isEmpty()) continue;
			
			time = fires.peek()[2];
			while (!fires.isEmpty() && fires.peek()[2] == time) { // 불도 같은 level(타임)에 번질 수 있는거 모두 표시
				int[] fireInfo = fires.poll();
				int nowX = fireInfo[0], nowY = fireInfo[1], level = fireInfo[2];
				for (int i = 0; i < 4; i++) {
					int nx = nowX + dxdy[i][0], ny = nowY + dxdy[i][1];
					if (!isValid(nx, ny)) // 지훈이와 달리 유효하지 않는 범위는 건너뛴다.
						continue;
					if (map[nx][ny] == '.') { // 이동 가능한 위치인 곳은 불이 번진다.
						map[nx][ny] = 'F';
						state[nx][ny] = Integer.MAX_VALUE; // 불이 번졌다면 기록해둔다.
						fires.add(new int[] { nx, ny, level + 1 });
					}
				}
			}
		}

		System.out.println("IMPOSSIBLE");
	}
}
