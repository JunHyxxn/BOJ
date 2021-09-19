import java.util.Scanner;

public class Quiz_10952 {
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			while(true) {
				int A, B;
				A = sc.nextInt();
				B = sc.nextInt();
				if((A<0 || A >=10) || (B < 0 || B >= 10)) throw new Exception("Out of Range!!");
				else{
					if(A == 0 && B == 0) {
						System.out.println("End of function!");
						break;
					}
					else {
						System.out.println(A+B);						
					}
				}
			}			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
