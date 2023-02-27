package algo0227;

import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_5639_이진탐색트리 {
    static StringBuilder sb;
    static class Node {
        public Node parent = null;
        public Node left = null;
        public Node right = null;
        public Integer value;

        public Node(Integer value) {
            this.value = value;
        }
    }

    static class BST {
        public Node root;

        public BST(Node root) {
            this.root = root;
        }

        public void insertNode(Integer value) {
            Node cur = this.root;
            while(true) {
                if(cur.value > value) {
                    if(cur.left == null) {
                        cur.left = new Node(value);
                        cur.left.parent = cur.left;
                        return;
                    }
                    cur = cur.left;
                } else {
                    if(cur.right == null) {
                        cur.right = new Node(value);
                        cur.right.parent = cur.right;
                        return;
                    }
                    cur = cur.right;
                }
            }
        }

        public void postOrder(Node cur) {
            if(cur == null) return;
            postOrder(cur.left);
            postOrder(cur.right);
            sb.append(cur.value).append("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        Scanner sc = new Scanner(System.in);
        Node root = new Node(Integer.parseInt(sc.nextLine()));
        BST bst = new BST(root);
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            if(s.length() <= 0) {
                break;
            }
            Integer i = Integer.parseInt(s);
            bst.insertNode(i);
        }

        sb = new StringBuilder();
        bst.postOrder(bst.root);
        System.out.println(sb);
    }
}