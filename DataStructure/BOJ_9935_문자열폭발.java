package algo0221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class BOJ_9935_문자열폭발 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<String> deque = new ArrayDeque<>(List.of(br.readLine().split("")));

        String[] bomb = br.readLine().split("");

        int idx = 0;
        int len = bomb.length;
        Stack<String> stack = new Stack<>();
        while(!deque.isEmpty()) {
            String now = deque.pollFirst();
            if(now.equals(bomb[idx])) idx++;
            else {
                idx = now.equals(bomb[0]) ? 1 : 0;
            }
            stack.add(now);
            if(idx == len) {
                for (int i = 0; i < len; i++) {
                    stack.pop();
                }
                int time = 0;
                while(!stack.isEmpty()) {
                    if(time++ == len) break;
                    deque.offerFirst(stack.pop());
                }
                idx = 0;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : stack) {
            sb.append(s);
        }
        System.out.println(stack.size() == 0 ? "FRULA" : sb);
    }

}
