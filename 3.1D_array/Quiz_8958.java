import java.util.Scanner;

public class Quiz_8958{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int i=0; i<T; i++) {
			String result = sc.next();
			calc(result);
		}
	}
	private static void calc(String result) {
		int cnt =0;
		int sum =0;
		for(int i=0; i<result.length(); i++) {
			if(Character.toString(result.charAt(i)).equals("O")) {
				cnt = cnt+1;
				sum += cnt;
			}else {
				cnt =0;
				sum += cnt;
			}
		}
		
		System.out.println(sum);
	}
}