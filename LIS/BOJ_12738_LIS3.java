package day0802;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_12738 {
    private static int N;
    private static int[] nums, lis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        lis = new int[N+1];
        int idx = 0;
        lis[idx++] = nums[0];
        for (int i = 1; i < N; i++) {
            int number = nums[i];
            if(lis[idx-1] < number) {
                lis[idx++] = number;
            } else {
                int tempIdx = binarySearch(0, idx-1, number);
                lis[tempIdx] = Math.min(lis[tempIdx], number);
            }
        }
        System.out.println(idx);
    }
    // 주의사항!. 재귀로 하면 StackOverFlow 발생 -> while 반복문으로 해결하기
    static int binarySearch(int left, int right, int destination) {
        int mid = 0;
        while(left < right) {
            mid = (left + right) / 2;
            if(lis[mid] < destination) {
                left = mid + 1;
            } else if (lis[mid] == destination) {
                return mid;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
