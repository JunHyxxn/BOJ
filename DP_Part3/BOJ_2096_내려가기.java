package algo0221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.StringTokenizer;

public class BOJ_2096_내려가기 {
    static int N;
    static int[] maxArr, minArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        maxArr = new int[3];
        minArr = new int[3];
        int a, b, c;
        int savedA, savedB, savedC;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            // MAX
            savedA = Math.max(a+maxArr[0], a+maxArr[1]);
            int temp = Math.max(b+maxArr[0], b+maxArr[1]);
            savedB = Math.max(temp, b+maxArr[2]);
            savedC = Math.max(c+maxArr[1], c+maxArr[2]);
            maxArr[0] = savedA;
            maxArr[1] = savedB;
            maxArr[2] = savedC;
            // MIN
            savedA = Math.min(a+minArr[0], a+minArr[1]);
            temp = Math.min(b+minArr[0], b+minArr[1]);
            savedB = Math.min(temp, b+minArr[2]);
            savedC = Math.min(c+minArr[1], c+minArr[2]);
            minArr[0] = savedA;
            minArr[1] = savedB;
            minArr[2] = savedC;
        }
        OptionalInt max = Arrays.stream(maxArr).max();
        OptionalInt min = Arrays.stream(minArr).min();
        System.out.println(max.getAsInt() + " " + min.getAsInt());
    }
}
