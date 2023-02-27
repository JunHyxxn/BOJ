package algo0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BOJ_2143_두배열의합 {
    static int T, n,m;
    static int[] A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        m = Integer.parseInt(br.readLine());
        B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int total = 0;
        Map<Integer, Integer> dpSumA = new HashMap<>();
        for (int i = 0; i < n; i++) {
            total = 0;
            for (int j = i; j < n; j++) {
                total += A[j];
                dpSumA.merge(total, 1, (v1, v2) -> v1+v2);
            }
        }

        long result = 0L;
        for (int i = 0; i < m; i++) {
            total = 0;
            for (int j = i; j < m; j++) {
                total += B[j];
                result += dpSumA.getOrDefault(T-total, 0);
            }
        }

        System.out.println(result);
    }
}
