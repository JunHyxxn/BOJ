package algo0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1806_부분합 {
    static int N, S, result;
    static int[] nums, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        nums = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N+1];
        dp[1] = nums[1];
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }

        result = Integer.MAX_VALUE;

        // Two Pointer
        int left = 1, right = 1;
        while(right <= N) {
            if(left > N) break;
            int diff = dp[right] - dp[left-1];
            if(diff >= S) {
                result = Math.min(result, right - left +1);
                left++;
            }
            else right++;
        }


        System.out.println(result == Integer.MAX_VALUE ? 0 : result);
    }
}

