import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz_2577 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A, B, C;
		List<String> list = new ArrayList<String>();
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		list.add("9");
		
		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
		
		int result  = A*B*C;
		
		String resultString = Integer.toString(result);
		
		for(int i=0; i< list.size(); i++) {
			int cnt=0;
			for(int j=0; j < resultString.length(); j++) {
				if(list.get(i).contains(Character.toString(resultString.charAt(j)))) {
					cnt++;
				}
			}
			System.out.println(cnt);
		}
	}
}
