import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Quiz_3052 {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		Scanner sc = new Scanner(System.in);
		
		for(int i=0; i < 10; i++) {
			list.add((sc.nextInt()%42));
		}
		
		Set<Integer> set = new HashSet<>(list);
		System.out.println(set.size());
	}
}
