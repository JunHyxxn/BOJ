package org.junhyxxn.algo0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10830_행렬제곱 {
    static int N;
    static final int MOD = 1000;
    static int[][] matrix, init;
    static int[][] temp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Long B = Long.parseLong(st.nextToken());
        matrix = new int[N][N];
        temp = new int[N][N];
        init = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                matrix[i][j] = num;
                init[i][j] = num;
            }
        }
        recursion(B);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N-1; j++) {
                sb.append(matrix[i][j]%MOD).append(" ");
            }
            sb.append(matrix[i][N-1]%MOD).append("\n");
        }
        System.out.println(sb.toString());

    }

    static void recursion(long now) {
        if(now == 1) {
            return;
        }

        recursion(now/2);
        matrixSquare();
        if(now %2 == 1) matrixMultiInit();
    }

    static void matrixSquare() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += (matrix[i][k] * matrix[k][j])%MOD;
                }
                temp[i][j] = sum%MOD;
            }
        }
        // deep copy
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = temp[i][j];
            }
        }
    }

    static void matrixMultiInit() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += (matrix[i][k] * init[k][j])%MOD;
                }
                temp[i][j] = sum%MOD;
            }
        }
        // deep copy
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = temp[i][j];
            }
        }
    }
}

