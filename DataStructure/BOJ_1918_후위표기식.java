package algo0128;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ_1918_후위표기식 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] inorder = br.readLine().toCharArray();

        Deque<String> store = new ArrayDeque<>();

        for (char now : inorder) {
            if( now == ')' ) {
                Deque<String> inner = new ArrayDeque<>();
                while(!store.peekLast().equals("(")) {
                    inner.offerFirst(store.pollLast());
                }
                store.pollLast();
                store.offerLast(process(inner));
            }else {
                store.offerLast(String.valueOf(now));
            }
        }
        System.out.println(process(store));
    }

    static Deque<String> multiDivide(Deque store) {
        Deque<String> temp = new ArrayDeque<>();

        String L = store.pollFirst().toString();
        String O;
        String R;
        while(!store.isEmpty()) {
            O = store.pollFirst().toString();
            R = store.pollFirst().toString();
            if(O.equals("*") || O.equals("/")) {
                L = L+R+O;
                continue;
            }
            temp.offerLast(L);
            temp.offerLast(O);
            L = R;
        }
        temp.offerLast(L);
        return temp;
    }
    static String addMinus(Deque store) {
        Deque<String> temp = new ArrayDeque<>();

        String L = store.pollFirst().toString();
        String O;
        String R;
        while(!store.isEmpty()) {
            O = store.pollFirst().toString();
            R = store.pollFirst().toString();
            if(O.equals("+") || O.equals("-")) {
                L = L+R+O;
                continue;
            }
            temp.offerLast(L);
            temp.offerLast(O);
            L = R;
        }
        temp.offerLast(L);
        return temp.poll();
    }

    static String process(Deque store) {
        store = multiDivide(store);
        return addMinus(store);
    }
}
