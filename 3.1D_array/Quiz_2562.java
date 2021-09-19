import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Quiz_2562{
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<9; i++) {
			list.add(sc.nextInt());
		}
		int max = list.stream().max(Integer::compareTo).get();
		int max_index = list.indexOf(max)+1;
		
		System.out.println(max);
		System.out.println(max_index);
	}
}