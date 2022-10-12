package com.day0927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
12
0 0 1 1 1 0 1 0 1 0 1 1
0 1
1 2
1 4 
0 8 
8 7 
9 10
9 11
4 3
6 5
4 6
8 9

5

11
0 1 0 1 1 0 1 0 0 1 0
0 1
0 2
1 3
1 4
2 5
2 6
3 7
4 8
6 9
9 10

5
 * */
public class KakaoBlind_2022_양과늑대 {
	static int N, visited, cnt, pos, result;
	static int[] sheepWolf;
	static List<List<Integer>> adjList;
	public static void main(String[] args)  throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		sheepWolf=  new int[N];
		for (int i = 0; i < N; i++) {
			sheepWolf[i] = Integer.parseInt(st.nextToken());
		}
		
		adjList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			adjList.add(new ArrayList<>());
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			adjList.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
		}
		
		pos = 0;
		visited = 0;
		cnt = 0;
		Init();
		result = cnt;
		BFS(visited, pos, cnt);
		System.out.println(result);
	}
	static void BFS(int visited, int pos, int cnt) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {visited, pos, cnt, cnt });
		
		while(!queue.isEmpty()) {
			int[] state = queue.poll();
			int nowState = state[0], curPos = state[1], curCnt = state[2], curSheep = state[3];
			result = Math.max(result, curSheep);
			for (int i = 0; i < N; i++) {
				if((curPos & (1<<i)) == 0) continue;
				if(sheepWolf[i] == 1) { // 늑대
					if(curCnt > 1) {
						int tempPos = curPos & ~(1<<i);
						for (Integer cand : adjList.get(i)) {
							if((nowState & (1<<cand)) != 0) continue;
							tempPos |= (1<<cand);
						}
						queue.offer(new int[] {nowState|(1<<i), tempPos, curCnt-1, curSheep} );
					}
				} else { // 양
					int tempPos = curPos & ~(1<<i);
					for (Integer cand : adjList.get(i)) {
						if((nowState & (1<<cand)) != 0) continue;
						tempPos |= (1<<cand);
					}
					queue.offer(new int[] {nowState|(1<<i), tempPos, curCnt+1, curSheep+1} );
				}
			}
		}
	}
	
	static void Init() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(0);
		cnt++;
		visited |= 1<<0;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for (Integer nxt : adjList.get(now)) {
				if(sheepWolf[nxt] == 1) { // wolf
					pos |= (1<<nxt);
					continue;
				}
				if((visited & (1<<nxt)) != 0) {
					continue;
				}
				queue.offer(nxt);
				cnt++;
				visited |= (1<<nxt);
			}
		}
	}
}
