import java.util.Scanner;

public class Quiz_15596 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] numbers = new int[N];
		
		for(int i=0; i < N; i++) {
			numbers[i] = sc.nextInt();
		}
		
		System.out.println(sum(numbers));
	}
	public static long sum(int[] a) {
		int sum=0;
		
		for(int i=0; i<a.length; i++) {
			sum += a[i];
		}
		
		return sum;
	}
}
