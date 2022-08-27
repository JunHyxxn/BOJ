package com.day0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17144_미세먼지안녕 {
	static class AirCleaner {
		// up : 공기청정기 위  down : 공기청정기 아래
		int[] up, down;

		public AirCleaner(int[] up, int[] down) {
			this.up = up;
			this.down = down;
		}
	}

	static int R, C, T;
	static AirCleaner airCleaner;
	static int[][] map, dxdy = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		int[] up = null, down = null;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1 && up == null) { // 공기청정기 위 저장
					up = new int[] { i, j };
				} else if (map[i][j] == -1 && up != null) { // 공기청정기 아래 저장
					down = new int[] { i, j };
				}
			}
		}
		airCleaner = new AirCleaner(up, down);
		for (int i = 0; i < T; i++) { // T 번 확산 -> 공기청정 진행
			spread();
			airCleaning();			
		}
		int result = 2; //  공기청정기 위치 -2 값 고려하기 위해 2로 시작
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				result += map[i][j];
			}
		}
		System.out.println(result);
	}
	// 유효한 index check
	static boolean isValid(int x, int y) {
		return !(x < 0 || x >= R || y < 0 || y >= C);
	}

	static void spread() {
		// 동시에 일어나야하기 때문에 임시 배열 만들어둔다.
		int[][] temp = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] >= 5) { // 5 이상일때만 확산이 이루어진다.
					int cnt = 0; // 확산한 칸
					int send = map[i][j] / 5; // 확산시킬 양
					for (int dir = 0; dir < 4; dir++) { // 4방향 탐색
						int nx = i + dxdy[dir][0], ny = j + dxdy[dir][1];
						if (!isValid(nx, ny) || map[nx][ny] == -1) // 유효한 인덱스 아니거나, 공기청정기라면 넘어간다.
							continue;
						cnt++;
						temp[nx][ny] += send; // 확산되는 칸에 먼지 양을 추가한다. 
					}
					// 원래 칸에 남아있는 먼지 양 추가한다.
					int remain = map[i][j] - (send * cnt); 
					temp[i][j] += remain;
				} else { // 5미만일때는 그 값을 그대로 추가한다.
					temp[i][j] += map[i][j];
				}
			}
		}
		// deepcopy 한 temp 배열 map에 덮어씌운다.
		map = temp;
	}
	// 공기 청정 진행
	static void airCleaning() {
		int upX = airCleaner.up[0], upY = airCleaner.up[1];
		int downX = airCleaner.down[0], downY = airCleaner.down[1];
		// 한 바퀴 돌면서 진행되는 것이 아닌, 한쪽으로 쭉 밀기 때문에 임시 배열 만드는 것이 더 편하다.
		int[][] temp = new int[R][C];
		// Deepcopy 진행
		for (int i = 0; i < R; i++) {
			temp[i] = map[i].clone();
		}
		// Up
		// 아래 줄 오른쪽으로 밀기
		for (int j = C - 1; j >= 2; j--) {
			temp[upX][j] = map[upX][j - 1];
		}
		temp[upX][1] = 0;
		// 오른쪽 열 위로 밀기
		for (int i = 0; i < upX; i++) {
			temp[i][C - 1] = map[i + 1][C - 1];
		}
		// 맨윗줄 왼쪽으로 밀기
		for (int j = 0; j < C - 1; j++) {
			temp[0][j] = map[0][j + 1];
		}
		// 왼쪽 열 아래로 밀기
		for (int i = upX; i > 0; i--) {
			temp[i][upY] = map[i - 1][upY];
		}
		temp[upX][upY] = -1;

		// Down
		// 윗 줄 오른쪽으로 밀기
		for (int j = C - 1; j >= 2; j--) {
			temp[downX][j] = map[downX][j - 1];
		}
		temp[downX][1] = 0;
		// 오른쪽 열 아래로 밀기
		for (int i = R-1; i > downX; i--) {
			temp[i][C - 1] = map[i - 1][C - 1];
		}
		// 맨 아래줄 왼쪽으로 밀기
		for (int j = 0; j < C - 1; j++) {
			temp[R-1][j] = map[R-1][j + 1];
		}
		// 왼쪽 열 위로 밀기
		for (int i = upX; i < R-1; i++) {
			temp[i][downY] = map[i + 1][downY];
		}
		temp[downX][downY] = -1;
		// 마찬가지로 결과를 map에 덮어씌운다.
		map = temp;
	}
}
