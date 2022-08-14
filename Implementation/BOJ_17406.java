package com.day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17406 {
	static int N, M, K, result;
	static List<List<Integer>> command;
	static int[][] cmds;
	static boolean[] visited;

	static int[][] Rotate(int x, int y, int c, int[][] arr) {
		// First - 마지막에 집어넣을 원소 저장
		int temp = arr[x - c][y - c];
		// Up - 왼쪽 테두리 위로 한칸씩 올립니다.
		for (int i = x - c + 1; i <= x + c; i++) {
			arr[i - 1][y - c] = arr[i][y - c];
		}

		// Left - 아래 테두리 왼쪽으로 한칸씩 밀어줍니다.
		for (int j = y - c + 1; j <= y + c; j++) {
			arr[x + c][j - 1] = arr[x + c][j];
		}

		// Down - 오른족 테두리 아래로 한칸씩 밀어줍니다.
		for (int i = x + c - 1; i >= x - c; i--) {
			arr[i + 1][y + c] = arr[i][y + c];
		}

		// Right - 위쪽 테두리 오른쪽으로 한 칸씩 밀어줍니다.
		for (int j = y + c - 1; j >= y - c + 1; j--) {
			arr[x - c][j + 1] = arr[x - c][j];
		}
		// 마지막으로 처음에 저장한 위치가 값이 이미 바꼈으니 바꾸기 전에 저장한 값을 변경해줍니다.
		arr[x - c][y - c + 1] = temp;
		return arr;
	}

	// 회전 연산을 Permutation 해서 순서 상관없이 모든 경우를 수행해줍니다.
	static void perm(int idx, int[][] arr) {
		if (idx == K) { // permutation 완료
			// 깊은 복사 필요하다!!
			int[][] temp = new int[N+1][M+1];
			for (int i = 0; i < temp.length; i++) {
				System.arraycopy(arr[i], 0, temp[i], 0, arr[0].length);
			}
			for (int[] cmd : cmds) { // 회전 수행
				for (int j = 1; j <= cmd[2]; j++) { // 가장 안족 테두리부터 바깥쪽 테두리까지 수행합니다.
					temp = Rotate(cmd[0], cmd[1], j, temp);	// 회전
				}
			}
			// 회전 연산을 모두 마친 후 결과를 갱신해줍니다.
			for (int i = 1; i <= N; i++) {
				int total = 0;
				for (int j = 1; j <= M; j++) {
					total += temp[i][j];
				}
				result = result > total ? total : result;
			}
		}

		for (int i = 0; i < K; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			cmds[idx][0] = command.get(i).get(0);
			cmds[idx][1] = command.get(i).get(1);
			cmds[idx][2] = command.get(i).get(2);
			perm(idx + 1, arr);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result = Integer.MAX_VALUE;
		int r, s, c;
		command = new ArrayList<>();

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			command.add(Arrays.asList(r, s, c));
		}

		// Permutation
		cmds = new int[K][3];
		visited = new boolean[K];
		perm(0, arr);

		System.out.println(result);
	}
}
