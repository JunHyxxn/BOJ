package com.day0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;


public class SWEA_1873 { // SWEA - 1873 상호의 배틀필드
	static int H, W, curDir, x, y; // 높이, 너비, 현재바라보는 방향, 현재위치 x, 현재위치 y
	static int[][] dxdy = {{0,-1}, {1,0}, {-1,0},{0,1}}; // 방향에 따른 dx,dy 값
	// Tank
	static HashMap<Character, Integer> tank = new HashMap<>(); // 기호에 따라 dxdy 인덱스 값
	// dir2Move
	static HashMap<String, Integer> dir2Move = new HashMap<>(); // RULD  문자에 따른 dxdy 인덱스 값
	// Initialize Tank & dir2Move
	static {
		tank.put('<', 0);
		tank.put('v', 1);
		tank.put('^', 2);
		tank.put('>', 3);
		
		dir2Move.put("L", 0);
		dir2Move.put("D", 1);
		dir2Move.put("U", 2);
		dir2Move.put("R", 3);
	}
	
	// 지도
	static char[][] map;
	
	
	// check Out of Range
	static boolean isValid(int x, int y) {
		if (x <0 || x>=H || y <0 || y >=W) return false;
		return true;
	}
	
	// shoot
	static void shoot() {
		int nowX = x, nowY = y; // 위치 
		while(isValid(nowX, nowY )) { // 지금 위치가 유효하다면
			if(map[nowX][nowY] == '*') { // 벽돌 벽 만났다면 부수고 '.' 로 변경 후 종료 
				map[nowX][nowY] = '.';
				return;
			}else if (map[nowX][nowY] == '#') return; // 강철 벽 만나면 종료
			// 그 외의 상태면 바라보는 방향에 맞춰 한 칸 전진
			nowX += dxdy[curDir][0];
			nowY += dxdy[curDir][1];
		}
	}
	 
	// move
	static void move(int dir) {
		curDir = dir; // 이동하지 않더라도 바라보는 방향은 수정
		// 전진 가능한지 판단
		int nx = x + dxdy[dir][0], ny = y + dxdy[dir][1]; 
		if (isValid(nx, ny) && map[nx][ny] =='.') { // 전진 가능하다면 전진
			x = nx;
			y = ny;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(bf.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			// 현재 위치 x, y
			x = -1;
			y = -1;
			for (int h = 0; h < H; h++) {
				map[h] = bf.readLine().toCharArray();
				if (x != -1 && y != -1) continue;
				for (int w = 0; w < W; w++) {
					if (tank.containsKey(map[h][w])) {
						curDir = tank.get(map[h][w]);
						map[h][w] = '.'; // 탱크 위치 기억했으니 잠시 '.' 로 바꿔둔다
						x = h;
						y = w;
					}
				}
			}
			
			int N = Integer.parseInt(bf.readLine());
			for (String cmd : bf.readLine().split("")) {
				if (cmd.equals("S")) {
					shoot();
				} else {
					move(dir2Move.get(cmd));
				}
			}
			
			// 마지막 탱크 방향 찾기
			char finalTank ='9';
			for (Entry<Character, Integer> temp : tank.entrySet()) {
				Character key = temp.getKey();
				Integer val = temp.getValue();
				if(curDir == val) {
					finalTank = key;
					break;
				}
			}
			// 탱크 위치에 두기
			map[x][y] = finalTank;
			
			// 출력
			System.out.print("#"+t+ " ");
			for (int h = 0; h < H; h++) {
				for (int w = 0; w < W; w++) {
					System.out.print(map[h][w]);
				}
				System.out.println();
			}
			
		}
	}
}
