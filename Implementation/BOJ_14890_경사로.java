package algo0312;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14890_경사로 {

    static int N, L;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            if (checkRow(i)) {
//                System.out.println(i+ "th row");
                result++;
            }
            if(checkCol(i)) {
//                System.out.println(i + "th col");
                result++;
            }
        }
//        System.out.println();
        System.out.println(result);
    }

    static boolean isValid(int x, int y) {
        return !(x < 0 || x >=N || y <0 || y>= N);
    }
    static boolean checkRow(int x) {
        boolean[] checked = new boolean[N];

        int prev = map[x][0];
        for (int y = 1; y < N; y++) {
            if(prev == map[x][y]) {
                continue;
            }
            int diff = prev - map[x][y];
            if(Math.abs(diff) > 1) return false;
//            System.out.println(y+" Row 경사로 체크 : " + x+ ", " + y);
            for (int i = 0; i < L; i++) {
                int j = y + (diff * i) + (diff < 0 ? -1 : 0);
//                System.out.println(x + ", " + j + " : " + (!isValid(x,j)) + " " + (map[x][j] != (diff < 0 ? prev : map[x][y])) + " " + (checked[j]));
                if(!isValid(x,j) || (map[x][j] != (diff < 0 ? prev : map[x][y])) || checked[j]) return false;
                checked[j] = true;
            }
            prev = map[x][y];
        }
        return true;
    }
    static boolean checkCol(int y) {
        boolean[] checked = new boolean[N];

        int prev = map[0][y];
        for (int x = 1; x < N; x++) {
            if(prev == map[x][y]) {
                continue;
            }
            int diff = prev - map[x][y];
            if(Math.abs(diff) > 1) return false;
//            System.out.println(y+" Column 경사로 체크 : " + x+ ", " + y);
            for (int i = 0; i < L; i++) {
                int j = x + (diff * i) + (diff < 0 ? -1 : 0);
//                System.out.println(j + ", " + y + " : " + (!isValid(j,y)) + " " + (map[j][y] != (diff < 0 ? prev : map[x][y])) + " " + (checked[j]));
                if(!isValid(x,j) || (map[j][y] != (diff < 0 ? prev : map[x][y])) || checked[j]) return false;
                checked[j] = true;
            }
            prev = map[x][y];
        }
        return true;
    }

}
