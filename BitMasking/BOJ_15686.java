package com.day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

////집과 치킨집의 좌표정보 저장을 위한 클래스
//class Point{
//	int x;
//	int y;
//	
//	public Point(int x, int y) {
//		this.x = x;
//		this.y = y;
//	}	
//}

public class BOJ_15686 {

//	// Ver. Prof
//	static int N;//배열크기
//	static int M;//선택될 치킨집 수
//	static ArrayList<Point> homes;//집
//	static ArrayList<Point> chickens;//치킨집
//	static Point[] selected;//선택된 치킨집 정보가 저장될 배열
//	static int MinDistance = Integer.MAX_VALUE;//답. 모든 경우의 최소 도시 치킨 거리.	
//
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		N = sc.nextInt();//배열크기
//		M = sc.nextInt();//선택될 치킨집 수
//		
//		homes = new ArrayList<Point>();
//		chickens = new ArrayList<Point>();
//		selected = new Point[M];
//		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {				
//				int val = sc.nextInt();
//				if(val == 1) {//집
//					homes.add(new Point(i, j));
//					
//				}else if(val == 2) {//치킨집
//					chickens.add(new Point(i, j));
//				}				
//			}
//		}
//		
//		combi(0, 0);
//
//		System.out.println(MinDistance);//답
//	}
//
//	private static void combi(int cnt, int start) {
//		if(cnt == M) {//M개 모두 선택됐으면
//			chickenDistance();//선택된 치킨집 조합으로 도시의 치킨거리 계산하러 감
//			return;
//		}
//		
//		for (int i = start; i < chickens.size(); i++) {
//			selected[cnt] = chickens.get(i);//Point 한개가 나옴.
//			combi(cnt+1, i+1);		
//		}		
//	}
//
//	//완성된 하나의 치킨집 조합으로 도시의 치킨거리를 계산함.
//	private static void chickenDistance() {
//		int citysum = 0;//1번집의 치킨거리 + 2번집의 치킨거리 + 3번집의 치킨거리...	
//		
//		for (Point home : homes) {//한 집에 대해 길이 계산해 봄
//			int min = Integer.MAX_VALUE;
//			
//			//home-chicken 사이의 거리 계산
//			for (Point chicken : chickens) {
//				int dist = Math.abs(chicken.x - home.x) + Math.abs(chicken.y - home.y);
//				min = Math.min(min, dist);
//			}
//			//한 집의 치킨거리가 결정됨.			
//			citysum += min;	
//			
//			if(citysum > MinDistance)
//				return;
//		}
//		
//		//도시의 치킨거리가 결정됨
//		MinDistance = Math.min(MinDistance, citysum);
//	}




	// Ver. My Code
	static int N, M, result;
	static List<List<Integer>> homes;
	static Stack<List<Integer>> candidate;
	
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		homes = new ArrayList<>();
		List<List<Integer>> stores = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				String temp = st.nextToken();
				if (temp.equals("1")) homes.add(Arrays.asList(i,j));
				else if (temp.equals("2")) stores.add(Arrays.asList(i,j));
			}
		}
		
		candidate = new Stack<>();
		visited = new boolean[stores.size()];
		result = Integer.MAX_VALUE;
		comb(stores, 0);
		System.out.println(result);
	}
	static void comb(List<List<Integer>> stores, int start) {
		if (candidate.size() == M) {
			result = Math.min(result, check());
			return;
		}
		
		for (int i = start; i < stores.size(); i++) {
			if(visited[i]) continue;
			visited[i] = true;
			candidate.add(stores.get(i));
			comb(stores, i+1);
			candidate.pop();
			visited[i] = false;
		}
	}
	
	static int check() {
		int[] distance = new int[homes.size()];
		int i = 0;
		Arrays.fill(distance, Integer.MAX_VALUE);
		int total = 0;
		for (List<Integer> home : homes) {
			for (List<Integer>  cand : candidate) {
				distance[i] = Math.min(distance[i], (Math.abs(home.get(0) - cand.get(0)) + Math.abs(home.get(1) - cand.get(1)) ));
			}
			total += distance[i];
			i++;
		}
		
		return total;
	}
}
