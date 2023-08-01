package day0801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_2141 {
    private static int N;
    private static Map<Integer, Long> village;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        village = new HashMap<>();
        long total = 0L;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            long num = Long.parseLong(st.nextToken());
            village.put(idx, num);
            total += num;
        }
        List<Integer> keySet = new ArrayList<>(village.keySet());
        Collections.sort(keySet);
        long point = 0L;
        double halfTotal = (1.0 * total / 2);
        for (Integer key : keySet) {
            point += village.get(key);
            if(point >= halfTotal) {
                System.out.println(key);
                System.exit(0);
            }
        }
    }
}

