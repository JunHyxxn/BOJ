package algo0111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ_2407_조합 {
    static int n,m;
    static BigInteger[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dp = new BigInteger[n+1][n+1];

        // dp initialization
        for (int i = 1; i <= n; i++) {
            dp[i][i] = BigInteger.ONE;
            dp[i][1] = BigInteger.valueOf(i);
        }

        for (int i = 3; i <= n; i++) {
            for (int j = 2; j < i; j++) {
                dp[i][j] = dp[i-1][j].add(dp[i-1][j-1]);
            }
        }
        System.out.println(dp[n][m]);
    }
}