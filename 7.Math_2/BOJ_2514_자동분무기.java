package day0724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2514 {
    private final static int N = 8;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] matrix = new int[N][N];
        int[] rowSum = new int[N];
        int[] colSum = new int[N];
        int M = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken()) - M;
                rowSum[i] += num;
                colSum[j] += num;
                matrix[i][j] = num;
            }
        }
        char[][] result = new char[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(result[i], '.');
        }
        int[] rowFertilizer = new int[N];
        int[] colFertilizer = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(Math.abs(calcTotal(rowSum, colSum, matrix, i, j) )%2 == 1) { // 홀수 -> 분무기 위치한 좌표
                    // 우선 분무기를 비료라 가정한 배열을 생성한다.
                    rowFertilizer[i]++;
                    colFertilizer[j]++;
                    result[i][j] = '+';
                }
            }
        }

        // 모두 비료라고 가정한 새로운 matrix를 생성한다.
        int[][] newMatrix = new int[N][N];
        // 새로운 matrix의 rowSum, colSum을 생성한다.
        int[] newRowSum = new int[N];
        int[] newColSum = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 비료약 뿌리기 => 해당 위치가 비료 분무기 위치라면 -1 필요
                newMatrix[i][j] = rowFertilizer[i] + colFertilizer[j] - (result[i][j] == '+' ? 1 : 0);
                newRowSum[i] += newMatrix[i][j];
                newColSum[j] += newMatrix[i][j];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(result[i][j] == '.'){
                    sb.append(result[i][j]).append(" ");
                    continue;
                }
                int newTotal = calcTotal(newRowSum, newColSum, newMatrix, i,j);
                int total = calcTotal(rowSum, colSum, matrix, i, j);
                if (Math.abs(newTotal - total) % 4 == 2) {
                    result[i][j] = '-';
                }
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    static int calcTotal(int[] rowSum, int[] colSum, int[][] matrix, int x, int y) {
        return rowSum[x] + colSum[y] - matrix[x][y];
    }

}