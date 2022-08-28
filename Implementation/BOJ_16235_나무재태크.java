package com.day0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_16235_나무재태크 {
	static class Tree implements Comparable<Tree>{
		int x, y, age;
		boolean state;

		public Tree(int x, int y, int age, boolean state) {
			this.x = x;
			this.y = y;
			this.age = age;
			this.state = state;
		}

		@Override
		public int compareTo(Tree o) {
			int xRes = this.x - o.x;
			if(xRes == 0) {
				int yRes = this.y - o.y;
				if (yRes == 0) {
					return this.age - o.age;
				}
				return yRes;
			}
			return xRes;
		}

		@Override
		public String toString() {
			return "[ Tree : " + x + ", " + y + " => " + age + " ]";
		}
		
	}
	static int N, M, K;
	static int[][] ground, add, expand = {
			{-1,-1},{-1,0},{-1,1}, {0,-1}, {0,1}, {1,-1},{1,0},{1,1}
	};
//	static PriorityQueue<Tree> trees;	
	static List<Tree> trees;
	static List<Tree> deadTrees;
	static Comparator<Tree> comp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ground = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(ground[i], 5);
			ground[i][0] = 0;
		}
		add = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				add[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		trees = new PriorityQueue<>(Tree::compareTo);
		trees = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
//			trees.offer(new Tree(x, y, age, true));
			trees.add(new Tree(x, y, age, true));			
		}
		comp= new Comparator<Tree>() {
			@Override
			public int compare(Tree o1, Tree o2) {
				int xRes = o1.x - o2.x;
				if(xRes == 0) {
					int yRes = o1.y - o2.y;
					if (yRes == 0) {
						return o1.age - o2.age;
					}
					return yRes;
				}
				return xRes;
			}
		};
		trees.sort(comp);
		
		for (int i = 0; i < K; i++) {
			spring();
			summer();
			fall();
			winter();
		}
		System.out.println(trees.size());
	}
	
	static boolean isValid(int x, int y) {
		return !(x<=0 || x>N || y<=0 || y>N);
	}
	
	static void spring() {
		List<Tree> temp = new ArrayList<>();
		deadTrees = new ArrayList<>();
		for (Tree tree : trees) {
			if(ground[tree.x][tree.y] < tree.age) {
				tree.state = false;
				deadTrees.add(tree);
				continue;
			}
			ground[tree.x][tree.y]-=tree.age;
			tree.age+=1;
			temp.add(tree);
		}
		trees = temp;
		trees.sort(comp);
		return;
		// TLE
//		int size = trees.size();
//		PriorityQueue<Tree> temp = new PriorityQueue<>(Tree::compareTo);
//		deadTrees = new ArrayList<>();
//		for (int i = 0; i < size; i++) {
//			Tree tree = trees.poll();
//			if(ground[tree.x][tree.y] < tree.age) {
//				tree.state = false;
//				deadTrees.add(tree);
//				continue;
//			}
//			ground[tree.x][tree.y]-=tree.age;
//			tree.age+=1;
//			temp.offer(tree);
//		}
//		trees.clear();
//		trees = temp;
//		return;
	}
	
	static void summer() {
		for (Tree dead : deadTrees) {
			ground[dead.x][dead.y] += (dead.age/2); 
		}
	}
	
	static void fall() {
		List<Tree> temp = new ArrayList<>();
		for (Tree tree : trees) {
			temp.add(tree);
			if(tree.age%5 != 0) continue;
			int x = tree.x, y = tree.y;
			for (int i = 0; i < expand.length; i++) {
				int nx = x + expand[i][0], ny = y+ expand[i][1];
				if(!isValid(nx, ny)) continue;
				temp.add(new Tree(nx, ny, 1, true));
			}
		}
		trees = temp;
		trees.sort(comp);
		return;
		// TLE
//		PriorityQueue<Tree> temp = new PriorityQueue<>(Tree::compareTo);
//		for (Tree tree : trees) {
//			temp.offer(tree);
//			if(tree.age%5 != 0) continue;
//			int x = tree.x, y = tree.y;
//			for (int i = 0; i < expand.length; i++) {
//				int nx = x + expand[i][0], ny = y+ expand[i][1];
//				if(!isValid(nx, ny)) continue;
//				temp.offer(new Tree(nx, ny, 1, true));
//			}
//		}
//		trees.clear();
//		trees = temp;
	}
	
	static void winter() {
		for (int i = 1; i <=N; i++) {
			for (int j = 1; j <= N; j++) {
				ground[i][j] += add[i][j];
			}
		}
	}
}

