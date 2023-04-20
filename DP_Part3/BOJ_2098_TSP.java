package algo0420;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2098_TSP {
    static int N;
    static int[][] adjMatrix;
    static int[][] dp;
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adjMatrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            adjMatrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        }

        dp = new int[N][1<<N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(tsp(0,1));
    }


    static int tsp(int now, int visited) {
        if(visited == ((1<<N)-1)) {
            if(adjMatrix[now][0] != 0) return adjMatrix[now][0];
            else return INF;
        }
        if(dp[now][visited] != -1) return dp[now][visited];
        dp[now][visited] = INF;

        for (int i = 0; i < N; i++) {
            if((visited & (1<<i)) != 0  || adjMatrix[now][i] == 0) continue;
            dp[now][visited] = Math.min(dp[now][visited], tsp(i, visited|(1<<i)) + adjMatrix[now][i]);
        }
        return dp[now][visited];
    }
}