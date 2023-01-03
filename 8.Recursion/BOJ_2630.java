package algo0103;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2630 {
    static int N;
    static String[][] board;
    static int blue;
    static int white;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        board = new String[N][N];
        white = 0;
        blue = 0;
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().split(" ");
        }

        solve(new Point(0,0), new Point(N, N));
        System.out.println(white);
        System.out.println(blue);
    }
    static void solve(Point start, Point end) {
        /*
        * 2,2  4,4   => (2,2) (3,3)  / (2,3) (2,4)  /  (3,2) (3,3)  / (3,3) (4,4)
        * 2*14 = 16384 * 2^7
        * 약 2M 정도에 해결 가능
         * */
        if((start.x+end.x)%2 == 1 || (start.y+end.y)%2 == 1) {
            if(board[start.x][start.y].equals("1")) blue +=1;
            else white+=1;
            return;
        }

        int temp = 0;
        for (int i = start.x; i < end.x; i++) {
            for (int j = start.y; j < end.y; j++) {
                temp += Integer.valueOf(board[i][j]);
            }
        }

        if(temp == 0) {
            white += 1;
            return;
        }
        else if (temp == (end.x - start.x)*(end.y - start.y)) {
            blue += 1;
            return;
        }

        int midX = (start.x + end.x) / 2;
        int midY = (start.y + end.y) / 2;

        // 1사분면
        solve(new Point(start.x, start.y), new Point(midX, midY));
        // 2사분면
        solve(new Point(start.x, midY), new Point(midX, end.y));
        // 3사분면
        solve(new Point(midX, start.y), new Point(end.x, midY));
        // 4사분면
        solve(new Point(midX, midY), new Point(end.x, end.y));
    }
}
