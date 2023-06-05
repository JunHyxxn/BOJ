package day0605;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_7579_앱 {

    static int N, M;
    static List<Integer> memories, costs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        costs = new ArrayList<>();

        memories = new ArrayList<>();
        costs = new ArrayList<>();
        StringTokenizer stM = new StringTokenizer(br.readLine());
        StringTokenizer stC = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            Integer memory = Integer.parseInt(stM.nextToken());
            Integer cost = Integer.parseInt(stC.nextToken());
            if (cost.equals(0)) {
                M -= memory;
                continue;
            }
            memories.add(memory);
            costs.add(cost);
        }
        N = memories.size();
        if (M <= 0) {
            System.out.println(0);
            System.exit(0);
        }
        int sum = costs.stream().mapToInt(c -> c).sum();
        int[][] dp = new int[N][sum + 1];
        int initValue = memories.get(0);
        for (int j = costs.get(0); j <= sum; j++) {
            dp[0][j] = initValue;
        }

        for (int i = 1; i < N; i++) {
            int curMemory = memories.get(i);
            int curCost = costs.get(i);
            for (int j = 0; j <= sum; j++) {
                int up = dp[i-1][j];
                int left = j == 0 ? Integer.MIN_VALUE : dp[i][j-1];
                int temp = Math.max(up, left);
                if(j - curCost >= 0) {
                    temp = Math.max(temp, dp[i-1][j-curCost] + curMemory);
                }
                dp[i][j] = temp;
            }
        }


        for(int j=0; j<= sum; j++) {
            if(dp[N-1][j] >= M) {
                System.out.println(j);
                System.exit(0);
            }
        }

    }

}
