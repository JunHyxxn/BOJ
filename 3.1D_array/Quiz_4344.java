import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz_4344 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();
		for(int i=0; i< C; i++) {
			int N = sc.nextInt();
			List<Integer> list = new ArrayList<Integer>();
			for(int j=0; j < N; j++) {
				list.add(sc.nextInt());				
			}
			average(list);
		}
	}
	public static void average(List<Integer> scores) {
		double aver = scores.stream().mapToDouble(Integer::doubleValue).average().getAsDouble();
		long upperScore = scores.stream().filter(s-> s>aver).count();
		float percent = (float)upperScore / (float)scores.size() * 100;
		DecimalFormat formatter = new DecimalFormat("##.000");
		
		System.out.println(formatter.format(percent));
	}
}
