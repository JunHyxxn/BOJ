package com.day0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SWEA_1210_Ladder {
	// Ver. Prof
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int max = 100;
		String[][] ladder = new String[max][max];
		
		for (int t = 1; t <= 10; t++) {
			int tc = Integer.valueOf(sc.nextLine());
			for (int i = 0; i < max; i++) {
				ladder[i] = sc.nextLine().split(" ");
			}
			
			// 입력 확인 - 생략
			
			// 거꾸로 올라가면서 출발점을 찾는다.
			int end = 0;
			for (int i = 0; i < max; i++) {
				if (ladder[max-1][i].equals("2")) {
					end = i;
					break;
				}
			}
			
			int x = 99, y = end;
			while(x>0) {
				// 1. 오른쪽으로 갈 수 있는지 => 계속 오른쪽으로 이동
				if((y+1 < 100) && ladder[x][y+1].equals("1")) {
					do {
						y++;
					} while ((y+1 < 100) && ladder[x][y+1].equals("1"));
				}
				
				// 2. 왼쪽으로 갈 수 있는지 => 계속 왼쪽으로 이동
				else if((y-1 >=0 ) && ladder[x][y-1].equals("1")) {
					do {
						y--;
					} while ((y-1 >= 0) && ladder[x][y-1].equals("1"));
				}
				
				// 3. 좌우 이동 후 위로 이동
				x--;
			}
			System.out.println("#" + tc  + " " + y);
		}
	} 
	
	
//	//  Ver. MyCode
//	// 0 : up 1 : left 2 : right
//	static int[][] dxdy = {{-1,0},{0,-1},{0,1}};
//	static int T = 10;
//	static int max = 100;
//	static char[][] ladder = new char[max][max]; 
//	
//	static int check(int x, int y) {
//		for (int i = 1; i < dxdy.length; i++) {
//			int nx = x+dxdy[i][0], ny = y+dxdy[i][1];
//			if (nx <0 || nx >= max || ny <0 || ny >= max) continue;
//			if(ladder[nx][ny] == '1') {
//				return i;
//			}
//		}
//		return 0;
//	}
//	static int[] moveUp(int x, int y) {
//		int nx;
//		while (true) {
//			nx = x - 1;
//			int dir = check(nx, y);
//			if(nx <0 || dir != 0) {
//				return new int[] {nx, dir};
//			}
//			x = nx;
//		}
//	}
//	static int moveHorizontal(int dir, int x, int y) {
//		int dy = dxdy[dir][1];
//		int ny;
//		while(true) {
//			ny = y+dy;
//			if (ny <0 || ny >= max || ladder[x][ny] != '1') {
//				return y;
//			}
//			y = ny;
//		}
//	}
//	public static void main(String[] args) throws IOException{
//		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//		
//		for (int t = 0; t < T; t++) {
//			// Data
//			StringTokenizer st = new StringTokenizer(bf.readLine());
//			int tc = Integer.parseInt(st.nextToken());
//			ladder = new char[max][max];
//			for (int i = 0; i < max; i++) {
//				st = new StringTokenizer(bf.readLine());
//				for (int j = 0; j < max; j++) {
//					ladder[i][j] = st.nextToken().charAt(0);
//				}
//			}
//			
//			// Logic
//			int start = 0;
//			for (int i = 0; i < max; i++) {
//				if(ladder[max-1][i] == '2') {
//					start = i;
//					break;
//				}
//			}
//			
//			// 사다리 타기 시작
//			int curX = max-1, curY = start;
//			while (curX > 0) {
//				int[] temp = moveUp(curX, curY);
//				curX = temp[0];
//				int dir = temp[1];
//				if (curX <= 0) break;
//				curY = moveHorizontal(dir, curX, curY);
//			}
//			System.out.println("#" + tc + " " + curY);
//		}
//	}
}
