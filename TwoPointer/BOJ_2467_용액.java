package algo0305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2467_용액 {
    static int N;
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        int left = 0;
        int right = N-1;
        int result = Integer.MAX_VALUE;
        int leftResult = 0;
        int rightResult = N-1;

        int nowSum = 0;
        while(true) {
            if (left >= right) break;
            nowSum = numbers[left] + numbers[right];
            if (Math.abs(nowSum) < result) {
                result = Math.abs(nowSum);
                leftResult = left;
                rightResult = right;
            }

            if (nowSum < 0) left++;
            else right--;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(numbers[leftResult]).append(" ").append(numbers[rightResult]);
        System.out.println(sb);
    }
}
