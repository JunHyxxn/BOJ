package algo0110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9465_스티커 {
    static int T, n;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[2][n];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
//            System.out.println(Arrays.deepToString(arr));

            solve();
        }
    }

    static void solve() {
        int[][] dp = new int[2][n+2];
        int temp, select;
        for (int i = 2; i < n+2; i++) {
            // 0 row
            select = arr[0][i-2];
            temp = Math.max(dp[0][i-2], dp[1][i-2]);
            dp[0][i] = Math.max(temp+select, dp[1][i-1]+select);

            // 1 row
            select = arr[1][i-2];
            dp[1][i] = Math.max(temp+select, dp[0][i-1]+select);
        }
        System.out.println(Math.max(dp[0][n+1], dp[1][n+1]));
    }
}
