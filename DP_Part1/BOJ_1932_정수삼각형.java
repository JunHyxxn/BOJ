package algo0106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1932_정수삼각형 {
    static int n;
    static int[][] arr, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][2*n-1];
        dp = new int[n][2*n-1];

        int mid = (2*n-1) / 2;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = mid-i; j <= mid+i ; j=j+2) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][mid] = arr[0][mid];

        for (int i = 1; i < n; i++) {
            for (int j = mid-i; j <= mid+i; j=j+2) {
                if(isValid(i-1, j-1)) dp[i][j] = Math.max(dp[i-1][j-1]+ arr[i][j], dp[i][j]);
                if(isValid(i-1, j+1)) dp[i][j] = Math.max(dp[i-1][j+1]+ arr[i][j], dp[i][j]);
            }
        }

        int result = Integer.MIN_VALUE;

        for (int i = mid-(n-1); i <= mid + (n-1); i=i+2) {
            result = Math.max(result, dp[n-1][i]);
        }

        System.out.println(result);
    }
    static boolean isValid(int x, int y) {
        return !(x < 0 || x > n || y < 0 || y >= 2*n-1);
    }
}
