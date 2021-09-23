/*
 * BOJ - 1546
 * 2s / 128MB limit 	[ Average ]
 * */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz_1546 {
	public static void main(String[] args) {
		List<Integer> score = new ArrayList<Integer>();
		List<Double> dScore = new ArrayList<Double>();
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		for(int i=0; i <N; i++) {
			score.add(sc.nextInt());
		}
		
		int max = score.stream().max(Integer::compareTo).get();
		
		for(double d : score) {
			d= d/max*100;
			dScore.add(d);
		}
		
		
		
		System.out.println(dScore.stream().mapToDouble(Double::doubleValue).average().getAsDouble());
		
	}
}
