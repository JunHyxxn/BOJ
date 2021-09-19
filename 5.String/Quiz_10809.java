import java.util.Scanner;

public class Quiz_10809 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = sc.next();
		

		String a_z = "abcdefghijklmnopqrstuvwxyz";
		char[] arr = a_z.toCharArray();
		
		
		for(char alpha : arr) {
			if(S.contains(Character.toString(alpha))) {
				System.out.print(S.indexOf(Character.toString(alpha))+ " ");				
			}
			else {
				System.out.print(-1+ " ");
			}
		}
		System.out.println();
	}
}
