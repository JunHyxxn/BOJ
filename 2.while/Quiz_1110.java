import java.util.ArrayList;
import java.util.Scanner;


public class Quiz_1110 {
	public static void main(String[] args) {
		try {
			ArrayList<Integer> arrList = new ArrayList<Integer>();
			Scanner sc = new Scanner(System.in);
			int N = sc.nextInt();
			if(N < 0 || N >=99) throw new Exception();
			else if(N == 0) {
				return;
			}
			else {
				arrList.add(N);
			}
			while(true) {
				int num = arrList.get(arrList.size()-1);
				int firstNum = num / 10; 
				int secondNum = num % 10;
				
				int addNum = firstNum + secondNum;
				
				int newNum = (secondNum*10) + (addNum%10);
				if(arrList.contains(newNum)) {
					System.out.println(arrList.size());
					break;
				}
				else {
					arrList.add(newNum);
				}
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}
}