package com.day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Line {
	int x;
	int y1;
	int y2;
	int mode; // open - close
	public Line(int x, int y1, int y2, int mode) {
		this.x = x;
		this.y1 = y1;
		this.y2 = y2;
		this.mode = mode;
	}
	public int getX() {
		return x;
	}
	public int getMode() {
		return mode;
	}
}
public class BOJ_2563 {
	static int[] line;
	
	static int calcHeight() {
		int height = 0;
		for (int i = 0; i < line.length; i++) {
			if (line[i] != 0) height++;
		}
		return height;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		List<Line> verticals = new ArrayList<>();
		for (int i = 0; i <  N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()), y= Integer.parseInt(st.nextToken());
			verticals.add(new Line(x, y, y+10, 0)); // 여는 세로선
			verticals.add(new Line(x+10, y, y+10, 1)); // 닫는 세로선
		}
		
		verticals.sort(Comparator.comparing(Line::getX).thenComparing(Line::getMode));
		
		
		// Line Swapping 할 배열
		line = new int[101];
		int total = 0;
		int prev = 0;
		for (Line v : verticals) {
			total += ((v.x - prev) * (calcHeight()));
			prev = v.x;
			if(v.mode == 0) { // Open 세로선
				for (int i = v.y1; i < v.y2; i++) {
					line[i] += 1;
				}
			}else if (v.mode == 1) { // Close 세로선
				for (int i = v.y1; i < v.y2; i++) {
					line[i] -= 1;
				}
			}
		}
		
		System.out.println(total);
	}
}
