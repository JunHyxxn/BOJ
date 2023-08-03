package day0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class BOJ_14003 {

    private static int N;
    private static int[] nums, lis, record;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        lis = new int[N + 1];
        record = new int[N + 1];

        int idx = 0;
        lis[idx] = nums[0];
        record[idx] = idx++;

        for (int i = 1; i < N; i++) {
            int number = nums[i];
            if(lis[idx-1] >= number) {
                int tempIdx = binarySearch(0, idx-1, number);
                lis[tempIdx] = Math.min(lis[tempIdx], number);
                record[i] = tempIdx;
                continue;
            }
            record[i] = idx;
            lis[idx++] = number;
        }
        System.out.println(idx);

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = N-1; i >= 0; i--) {
            int rIdx = record[i];
            if (rIdx != idx-1) {
                continue;
            }
            deque.offerFirst(nums[i]);
            idx--;
        }

        StringBuilder sb = new StringBuilder();
        for (Integer val : deque) {
            sb.append(val).append(" ");
        }
        System.out.println(sb);
    }

    static int binarySearch(int left, int right, int destination) {
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if(lis[mid] == destination) return mid;
            else if (lis[mid] < destination) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

}