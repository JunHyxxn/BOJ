package com.day0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class SWEA_5644 {
	static int total; // 두 사람의 총합
	static final int[] dummy = {-1, -1, 0}; // dummy data 
	static int[][] dxdy = {{0,0}, {-1,0}, {0,1}, {1,0}, {0,-1}}; // dxdy : 움직임에 따른 x, y 변화량 
	static Map<int[], int[]> batteryInfo; // BC info
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			String[] moveA = br.readLine().split(" ");
			String[] moveB = br.readLine().split(" ");
			batteryInfo = new HashMap<>();
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				// x, y, C, P
				// 문제에서 x축과 y축이 바껴있기 때문에 y를 먼저 받아서 처리한다
				int y = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken()), 
						C = Integer.parseInt(st.nextToken()), P = Integer.parseInt(st.nextToken());
				batteryInfo.put(new int[] {x,y}, new int[] {C,P});
			}
			// Init location
			int ax = 1, ay = 1, bx = 10, by = 10;
			// A 시작전에 배터리 충전 
			List<int[]> listA = findBattery(ax, ay);
			// B 시작전에 배터리 충전
			List<int[]> listB = findBattery(bx, by);
			// insert dummy data
			listA.add(dummy);
			listA.add(dummy);
			listB.add(dummy);
			listB.add(dummy);
			// Sorting by Power
			listA.sort((a,b) ->b[2]-a[2]);
			listB.sort((a,b) ->b[2]-a[2]);
			// Init total score
			total = 0;
			// calc possible BC => sum total
			calc(listA, listB);
			
			int[] temp; // 임시 x,y 좌표
			for (int i = 0; i < M; i++) { // M번의 움직임 수행
				 temp = move(Integer.parseInt(moveA[i]), ax, ay);
				 ax = temp[0]; ay = temp[1];
				 temp = move(Integer.parseInt(moveB[i]), bx, by);
				 bx = temp[0]; by = temp[1];
				 
			 	listA = findBattery(ax, ay);
				listB = findBattery(bx, by);
				// insert dummy data
				listA.add(dummy);
				listA.add(dummy);
				listB.add(dummy);
				listB.add(dummy);
				// Sorting by Power
				listA.sort((a,b) ->b[2]-a[2]);
				listB.sort((a,b) ->b[2]-a[2]);
				
				calc(listA, listB);
			}
			
			sb.append("#" + t + " " + total + "\n");
		}
		
		System.out.println(sb);
	}
	// 거리 계산
	static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
	// 모든 배터리들로부터 충전 가능한 배터리 탐색
	static List<int[]> findBattery(int x, int y) {
		List<int[]> result = new ArrayList<>();
		for (Map.Entry<int[], int[]> entry : batteryInfo.entrySet()) {
			int[] key = entry.getKey();
			int[] val = entry.getValue();
			int dist = getDistance(x, y, key[0], key[1]);
			if (dist <= val[0]) result.add(new int[] {key[0], key[1], val[1]});
		}
		return result;
	}
	// 이동
	static int[] move(int cmd, int x, int y) {
		int nx = x + dxdy[cmd][0], ny = y+dxdy[cmd][1];
		return new int[] {nx, ny};
	}
	// 충전 가능한 배터리 리스트를 인자로 받아 최대 충전량 구하고 total에 더해준다.
	static void calc(List<int[]> listA, List<int[]> listB) {
		if(listA.get(0)[2] == listB.get(0)[2]) { // 최대 충전 가능한 BC의 power가 같을 경우
			if ((listA.get(0)[0] != listB.get(0)[0]) || (listA.get(0)[1] != listB.get(0)[1])) { // power는 같아도 서로 다른 BC라면 각각 충전 가능하다.
				total += (listA.get(0)[2] +listB.get(0)[2]); 
				return;
			}
			// 서로 같은 BC라면 최대 충전 BC 하나 더하고 그 다음 차선 power의 BC에서 충전
			total += (listA.get(0)[2] + Math.max(listA.get(1)[2], listB.get(1)[2]));
		} else {
			// 최대 충전 가능한 BC power가 다르면 각각 충전
			total += (listA.get(0)[2] +listB.get(0)[2]); 
		}
		return;
	}
}
