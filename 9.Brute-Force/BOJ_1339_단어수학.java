package day0718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BOJ_1339_단어수학 {
    static final char A = 'A';
    static int N, minNum, result;
    static int[] alphaToNum;
    static boolean[] numVisited;
    static List<String> values, alphas;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        result = Integer.MIN_VALUE;
        values = new ArrayList<>();
        alphas = new ArrayList<>();
        Set<String> dupliAlphas = new HashSet<>();
        alphaToNum = new int['Z' - 'A' +1];
        numVisited = new boolean[10];

        for (int i=0; i<N; i++) {
            String s = br.readLine();
            values.add(s);
            List<String> split = Arrays.asList(s.split(""));
            dupliAlphas.addAll(split);
        }

        alphas = List.copyOf(dupliAlphas);
        minNum = 10 - alphas.size();

        findNumber(0, alphas.size());
        System.out.println(result);
    }

    static void findNumber(int depth, int len) {
        if(depth == len) {
            int temp = 0;
            for (String v : values) {
                StringBuilder sb = new StringBuilder();
                char[] chars = v.toCharArray();
                for (char c : chars) {
                    sb.append(alphaToNum[c-A]);
                }
                temp += Integer.parseInt(sb.toString());
            }
            result = Math.max(temp, result);
            return;
        }

        char c = alphas.get(depth).charAt(0);
        for (int i = 9; i >= minNum; i--) {
            if(numVisited[i]) continue;

            numVisited[i] = true;
            alphaToNum[c-A] = i;
            findNumber(depth+1, len);
            numVisited[i] = false;
            alphaToNum[c-A] = 0;
        }

    }
}
