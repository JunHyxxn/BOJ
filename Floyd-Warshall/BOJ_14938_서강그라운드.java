package org.junhyxxn.algo0220;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class BOJ_14938_서강그라운드 {
    static int n, m, r;
    static int[] items;
    static int[][] adjMatrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        items = new int[n+1];
        int idx = 1;
        for (String num : br.readLine().split(" ")) {
            items[idx++] = Integer.parseInt(num);
        }

        adjMatrix = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(adjMatrix[i], Integer.MAX_VALUE);
        }
        int a, b, c;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            adjMatrix[a][b] = c;
            adjMatrix[b][a] = c;
        }

        floydWarshall();
        int result = 0;
        for (int i = 1; i <= n; i++) {
            int rowTotal = 0;
            for (int j = 1; j <= n; j++) {
                if(i==j) {
                    rowTotal+= items[j];
                    continue;
                }
                if(adjMatrix[i][j] <= m) rowTotal += items[j];
            }
            result = Math.max(result, rowTotal);
        }

        System.out.println(result);
    }

    static void floydWarshall() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if(k==i) continue;
                for (int j = 1; j <= n; j++) {
                    if(j==k || adjMatrix[i][k] == Integer.MAX_VALUE || adjMatrix[k][j] == Integer.MAX_VALUE) continue;
                    adjMatrix[i][j] = Math.min(adjMatrix[i][j] , adjMatrix[i][k] + adjMatrix[k][j]);
                }
            }
        }
    }
}
