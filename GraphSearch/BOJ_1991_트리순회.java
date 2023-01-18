package algo0105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1991_트리순회 {
    static int N;
    static Map<String, String[]> tree;
    static StringBuilder preorder, inorder, postorder;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        tree = new HashMap<>();
        preorder = new StringBuilder();
        inorder =  new StringBuilder();
        postorder =  new StringBuilder();
        String node,child;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            node = st.nextToken();
            tree.put(node, new String[]{null, null});
            for (int j = 0; j < 2; j++) {
                child = st.nextToken();
                if(!child.equals(".")) {
                    tree.get(node)[j] = child;
                }
            }
        }
        traversal("A");
        System.out.println(preorder);
        System.out.println(inorder);
        System.out.println(postorder);
    }
    static void traversal(String now) {
        preorder.append(now);

        String[] childs = tree.get(now);
        if(childs[0] != null) traversal(childs[0]);
        inorder.append(now);

        if(childs[1] != null) traversal(childs[1]);
        postorder.append(now);
    }
}
