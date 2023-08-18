package day0818;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_2504 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String commands = br.readLine();
        int result = 0;
        int num = 1;
        Stack<Character> stacks = new Stack<>();
        for (int i = 0; i < commands.length(); i++) {
            char nowOper = commands.charAt(i);

            if (nowOper == '(') {
                num *= 2;
                stacks.push(nowOper);
            } else if (nowOper == '[') {
                num *= 3;
                stacks.push(nowOper);
            } else if (nowOper == ')') {
                if (stacks.isEmpty() || stacks.peek() != '(') {
                    System.out.println(0);
                    System.exit(0);
                } else if (commands.charAt(i-1) == '(') {
                    result += num;
                }
                stacks.pop();
                num /= 2;
            } else if (nowOper == ']') {
                if (stacks.isEmpty() || stacks.peek() != '[') {
                    System.out.println(0);
                    System.exit(0);
                } else if (commands.charAt(i-1) == '[') {
                    result += num;
                }
                stacks.pop();
                num /= 3;
            }
        }

        System.out.println(stacks.isEmpty() ? result : 0);
    }

}
