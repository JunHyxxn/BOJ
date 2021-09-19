import java.util.Scanner;

public class Quiz_2675 {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i=0; i<T; i++) {
			int R = sc.nextInt();
			String S = sc.next();
			char[] arr = S.toCharArray();
			String newString = "";
			for(int j=0; j<arr.length; j++) {
				newString += Character.toString(arr[j]).repeat(R);
			}
			System.out.println(newString);
		}
		
	}
}
