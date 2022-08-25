package com.day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236 {
	// 아기 상어 클래스
	static class Shark {
		int x, y, size, exp;
		public Shark(int x, int y) {
			this.x = x;
			this.y = y;
			this.size = 2;
			this.exp = 0;
		}
		// 먹는다
		void eat() {
			if(++exp == size) { // 경험치가 다 차면  size up
				this.size++;
				this.exp = 0;
			}
		}
	}
	// 상어가 먹을 수 있는 물고기 위치를 담을 클래스
	static class Point implements Comparable<Point>{
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			// x 오름차순, y 오름차순으로 정렬
			int res = this.x - o.x;
			if (res == 0) {
				return this.y - o.y;
			}
			return res;
		}
		
	}
	static int N, result;
	static int[][] map, dxdy = {{-1,0},{0,-1},{0,1},{1,0}};
	static Shark shark; // 상어는 static 선언
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) { // 상어 발견시 상어 초기화
					shark = new Shark(i,j);
					map[i][j] = 0; // 상어 지도상에서 제거
				}
			}
		}
		
		while(BFS()) { // 먹을수 있는 물고기 없을때까지 BFS 진행
		}
		System.out.println(result);
	}
	// 유효한 좌표값인지 체크
	static boolean isValid(int x, int y) {
		return !(x<0 || x>=N || y <0 || y>= N);
	}
	static boolean BFS() {
		int x = shark.x, y = shark.y;
		Queue<int[]> queue = new ArrayDeque<>();
		// 매번 탐색 새로 하니까 visited 초기화
		boolean[][] visited = new boolean[N][N];
		visited[x][y] = true;
		queue.offer(new int[] {x,y,0});
		// 최초로 발견한 거리를 저장하기 위한 변수
		int minDist = Integer.MAX_VALUE;
		// 먹을 수 있는 물고기 위치 저장
		List<Point> posEat = new ArrayList<>();
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			int nowX = temp[0], nowY = temp[1], dist = temp[2];
			if(minDist < dist) break; // 이미 발견한 물고기 거리를 넘어간다면 더 이상 탐색하지 않는다.
			if(map[nowX][nowY] < shark.size && map[nowX][nowY] != 0) { // 상어보다 작은 물고기가 있다면 가능한 먹이로 추가
				minDist = dist; // 그 때의 최소 거리를 기록해둔다.
				posEat.add(new Point(nowX, nowY));
				continue; // 가능한 모든 먹이 기록하고 가장 위, 가장 왼쪽을 기준으로 먹는다.
			}
			for (int i = 0; i < 4; i++) { // BFS 탐색
				int nx = nowX+dxdy[i][0], ny = nowY+dxdy[i][1];
				if(!isValid(nx, ny) || visited[nx][ny]) continue; // 유효하지 않은 범위이거나 이미 방문했다면 skip
				if(map[nx][ny] <= shark.size) { // 작거나 같다면 이동 가능
					visited[nx][ny] = true;
					queue.offer(new int[] {nx, ny, dist+1});
				}
			}
		}
		// 먹을 수 있는 물고기 없다면 종료시키기 위해 false 반환
		if (posEat.size() == 0) return false;
		// 가장 위, 가장 왼쪽을 기준으로 정렬
		posEat.sort(Point::compareTo);
		Point pt = posEat.get(0);
		// 먹는다
		shark.eat();
		// result update
		result += minDist; 
		// map update
		map[pt.x][pt.y] = 0;
		// shark location update
		shark.x = pt.x;
		shark.y = pt.y;
		return true;
	}
}
