package algo0117;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1865_웜홀 {
    static int TC, N, M, W; // 테스트 케이스, 지점의 수, 도로의 수, 웜홀의 수
    static int S, E, T; // S, E : 연결된 지점의 번호 ,  T : 걸리는 시간
    static long[][] adjMatrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(br.readLine());

        for (int t = 0; t < TC; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            adjMatrix = new long[N+1][N+1];

            // init
            init();

            int x, y, c;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                adjMatrix[x][y] = Math.min(adjMatrix[x][y], c);
                adjMatrix[y][x] = Math.min(adjMatrix[y][x], c);
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                adjMatrix[x][y] = Math.min(adjMatrix[x][y], c*-1);
            }
//            System.out.println();
//            Print();

            boolean flag = floydWarshall();

            sb.append(flag ? "YES\n" : "NO\n");
        }

        System.out.println(sb);
    }

    private static boolean floydWarshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if(k == i) continue;
                for (int j = 1; j <= N; j++) {
                    if(k == j) continue;
                    adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
                }
                if(adjMatrix[i][i] < 0) return true;
            }
        }
        return false;
    }

    private static void init() {
        for (int i = 1; i <= N; i++) {
            Arrays.fill(adjMatrix[i], Integer.MAX_VALUE);
        }
    }
    static void Print() {
        for (int i =1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}


