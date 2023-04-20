package algo0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CodeTree_삼성2022_싸움땅 {
    static int n, m, k;
    static int[][] players;
    static int[][] map;
    static Map<Integer, PriorityQueue<Integer>> guns;
    static int[] scores;

    static int[][] dxdy = {
            {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        players = new int[m+1][5];
        map = new int[n+1][n+1];
        guns = new HashMap<>();
        scores = new int[m+1];
        for (int i = 1; i <= n*n; i++) {
            guns.put(i, new PriorityQueue<>((o1, o2) -> o2-o1));
        }

        for (int i = 1; i<= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<= n; j++){
                int a = Integer.parseInt(st.nextToken());
                if(a == 0) continue;
                guns.get((i-1)*n+j).offer(a);
            }
        }

        for (int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            map[x][y] = i;
            players[i] = new int[] {x, y, d, s, 0};
        }

        for (int i = 0; i < k; i++) {
            for (int idx = 1; idx <= m; idx++) { // m명의 플레이어
                int[] player = players[idx];
                int x = player[0], y = player[1], d = player[2], s = player[3], g = player[4];
                marking(x, y, 0);
                // first move.
                int nx = x + dxdy[d][0], ny = y + dxdy[d][1];
                if(!isValid(nx, ny)) {
                    d = turnBack(d);
                    nx = x + dxdy[d][0];
                    ny = y + dxdy[d][1];
                }

                // 이동한 칸에 적 여부 파악
                if(isEnemy(nx, ny, idx)){ // 적 있다면
                    int enemyNum = map[nx][ny];
                    int[] enemy = players[enemyNum];
                    int enemyS = enemy[3], enemyG = enemy[4];
                    marking(enemy[0], enemy[1], 0);
                    int[] tempMy = new int[] {nx, ny, d, s, g};
                    if(fight(s, g, enemyS, enemyG)) { // My Win
                        scores[idx] += ((s+g) - (enemyS+enemyG));
                        players[enemyNum] = loserMove(enemyNum, enemy);
                        players[idx] = winnerAct(idx, tempMy);
                    } else { // My Lose
                        scores[enemyNum] += ((enemyS+enemyG) - (s+g));
                        players[idx] = loserMove(idx, tempMy);
                        players[enemyNum] = winnerAct(enemyNum, enemy);
                    }
                } else { // 적 없다면
                    // 총 여부 파악
                    marking(nx, ny, idx);
                    PriorityQueue<Integer> nowGuns = guns.get((nx-1)*n + ny);
                    if(!nowGuns.isEmpty() && nowGuns.peek() > g) {
                        int newGun = nowGuns.poll();
                        if(g != 0){
                            nowGuns.offer(g);
                        }
                        g = newGun;
                    }
                    players[idx] = new int[] {nx, ny, d, s, g};
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= m; i++) {
            sb.append(scores[i]).append(" ");
        }
        System.out.println(sb);
    }

    public static boolean isValid(int x, int y) {
        return !(x <= 0 || x > n || y <= 0 || y > n);
    }
    public static boolean isEnemy(int x, int y, int my) {
        return (isValid(x, y) && map[x][y] != my && map[x][y] != 0);
    }
    public static int turnBack(int dir) {
        return (dir +2 )%4;
    }

    public static int turnRight(int dir) {
        return (dir+1) % 4;
    }

    public static boolean fight(int myS, int myG, int enemyS, int enemyG) {
        int myTotal = myS + myG;
        int enemyTotal = enemyS + enemyG;
        if(myTotal == enemyTotal) {
            return myS > enemyS;
        } else
            return myTotal > enemyTotal;
    }

    public static int[] loserMove(int idx, int[] player) {
        int x = player[0], y = player[1], d = player[2], s = player[3], g = player[4];
        if (g != 0) {
            guns.get((x-1)*n+y).offer(g);
        }
        int nx = x, ny = y;
        while(true) {
            nx = x + dxdy[d][0]; ny = y+dxdy[d][1];
            if(isValid(nx, ny) && !isEnemy(nx, ny, idx)) break;
            d = turnRight(d);
        }
        int ng = 0;
        if(!guns.get((nx-1)*n+ny).isEmpty()) {
            ng = guns.get((nx-1)*n+ny).poll();
        }
        marking(nx, ny, idx);
        return new int[] {nx, ny, d, s, ng};
    }

    public static int[] winnerAct(int idx, int[] player) {
        int x = player[0], y = player[1], d = player[2], s = player[3], g = player[4];
        marking(x, y, idx);
        PriorityQueue<Integer> nowGuns = guns.get((x-1)*n + y);
        if(!nowGuns.isEmpty() && nowGuns.peek() > g) {
            int newGun = nowGuns.poll();
            if(g != 0){
                nowGuns.offer(g);
            }
            g = newGun;
        }
        return new int[] {x, y, d, s, g};
    }

    public static void marking(int x, int y, int idx) {
        map[x][y] = idx;
    }
}