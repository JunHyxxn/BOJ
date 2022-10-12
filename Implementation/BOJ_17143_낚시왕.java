package com.day1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17143_낚시왕 {
	static class Shark {
		int r; // row
		int c; // col
		int s; // 속도
		//  d가 1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽
		int d; // 방향
		int z; // 크기
		
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		public void move() {
			int cnt = s;
			int dx; int dy;
			if((d==1 && r==1) || (d==2 && r==R) || (d==3 && c==C) || (d==4 && c==1)) {
				this.d = d>2 ? (d==3 ? 4 : 3) : (d==1 ? 2 : 1);
				dx = dxdy[this.d][0]; dy = dxdy[this.d][1];
			}else {
				dx = dxdy[this.d][0]; dy = dxdy[this.d][1];
				for (int i = 1; i <= s; i++) {
					cnt--;
					r += dx;
					c += dy;
					if((d==1 && r==1) || (d==2 && r==R) || (d==3 && c==C) || (d==4 && c==1)) {
						break;
					}
				}				
				if((d==1 && r==1) || (d==2 && r==R) || (d==3 && c==C) || (d==4 && c==1)) this.d = d>2 ? (d==3 ? 4 : 3) : (d==1 ? 2 : 1);
			}
			// 벽에 닿았다면 방향 전환
			// 남은 거리 cnt를 이용해 마저 이동하기
			int K = d>2 ? C : R;
			int turn = cnt/(K-1);
			int remain = cnt%(K-1);
			if(turn%2 ==1){ // n바퀴 반 돌았다면 반대편에서 반대 방향으로 시작
				if(d>2) {
					c = c==1 ? C : 1;
				}else {
					r = r==1 ? R : 1;
				}
				this.d = d>2 ? (d==3 ? 4 : 3) : (d==1 ? 2 : 1);
			}
			// 남은 거리 이동.
			dx = dxdy[d][0];
			dy = dxdy[d][1];
			for (int i = 0; i < remain; i++) {
				r += dx;
				c += dy;
			}
		}
	}
	static int R, C, M, result;
	static Shark[][] fishing;
	static int[][] dxdy = {
			{0,0}, { -1,0}, {1,0}, {0,1}, {0,-1}
	};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fishing = new Shark[R+1][C+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			Shark temp = new Shark(r, c, s, d, z);
			fishing[r][c] = temp;
		}
		
		result = 0;
		run();
		System.out.println(result);
		
	}
	
	static void run() {
		for (int fisher = 1; fisher <= C; fisher++) {
			// 1. 어부 낚시 수행
			result += fish(fisher);
			if(fisher == C) return;
			
			// 2. 상어 이동
			fishing = moveShark();
			
			// 남은 상어 없다면 종료시킨다.
			if(fishing[0][0].z == 0) return;
		}
	}
	
	static int fish(int col) {
		int size = 0;
		for (int i = 1; i <= R; i++) {
			if(fishing[i][col] != null) {
				size = fishing[i][col].z;
				fishing[i][col] = null;
				return size;
			}
		} 
		return size;
	}
	
	static Shark[][] moveShark() {
		Shark[][] tempFishing = new Shark[R+1][C+1];
		int cnt = 0;
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if(fishing[i][j] != null) {
					Shark temp = fishing[i][j];
					temp.move();
					if(tempFishing[temp.r][temp.c] != null) {
						Shark enemy = tempFishing[temp.r][temp.c];
						Shark winner = temp.z > enemy.z ? temp : enemy;
						tempFishing[temp.r][temp.c] = winner;
					}else {
						tempFishing[temp.r][temp.c] = temp;
						cnt++;
					}
				}
			}
		}
		tempFishing[0][0] = new Shark(0,0,0,0,cnt);
		return tempFishing;
	}
}
