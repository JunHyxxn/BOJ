package com.day0801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499_주사위굴리기 {
    static int N, M, x, y, K; // 가로, 세로, x, y, 명령어 수
    static int[][] dice, map;
    static int[][] horizontal = {
            {1,0}, {1,1}, {1,2}, {3,1}
    };
    static int[][] vertical = {
            {0,1}, {1,1}, {2,1}, {3,1}
    };
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dice = new int[4][3];
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        command = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
//            command[i] = Integer.parseInt(st.nextToken());
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                if (isValid(x, y + 1)) {
                    y += 1;
                    right();
                }
            } else if (cmd == 2) {
                if (isValid(x, y - 1)) {
                    y -= 1;
                    left();
                }
            } else if (cmd == 3) {
                if (isValid(x - 1, y)) {
                    x -= 1;
                    up();
                }
            } else if (cmd == 4) {
                if (isValid(x + 1, y)) {
                    x += 1;
                    bottom();
                }
            }
        }
        System.out.println(sb);
    }

    static void changeNum() {
        sb.append(dice[1][1]).append("\n");
        if(map[x][y] == 0) {
            map[x][y] = dice[3][1];
        }else {
            dice[3][1] = map[x][y];
            map[x][y] = 0;
        }
        return;
    }
    static void left() {
        int[] now = horizontal[0];
        int temp = dice[now[0]][now[1]];
        int[] nxt;
        for (int i = 1; i < 4; i++) {
            nxt = horizontal[i];
            dice[now[0]][now[1]] = dice[nxt[0]][nxt[1]];
            now = nxt;
        }
        dice[now[0]][now[1]] = temp;
        changeNum();
    }

    static void right() {
        int[] now = horizontal[0];
        int temp = dice[now[0]][now[1]];
        int[] nxt;
        for (int i = 3; i > 0; i--) {
            nxt = horizontal[i];
            dice[now[0]][now[1]] = dice[nxt[0]][nxt[1]];
            now = nxt;
        }
        dice[now[0]][now[1]] = temp;
        changeNum();
    }

    static void up() {
        int[] now = vertical[0];
        int temp = dice[now[0]][now[1]];
        int[] nxt;
        for (int i = 1; i < 4; i++) {
            nxt = vertical[i];
            dice[now[0]][now[1]] = dice[nxt[0]][nxt[1]];
            now = nxt;
        }
        dice[now[0]][now[1]] = temp;
        changeNum();
    }

    static void bottom() {
        int[] now = vertical[0];
        int temp = dice[now[0]][now[1]];
        int[] nxt;
        for (int i = 3; i > 0; i--) {
            nxt = vertical[i];
            dice[now[0]][now[1]] = dice[nxt[0]][nxt[1]];
            now = nxt;
        }
        dice[now[0]][now[1]] = temp;
        changeNum();
    }

    static boolean isValid(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= M);
    }
}
