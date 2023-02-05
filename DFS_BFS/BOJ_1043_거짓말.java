package algo0130;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1043_거짓말 {

    static int N, M;
    static int know, result;
    static boolean[] stupid, visited;
    static boolean[][] adjMatrix;
    static List<Integer> reporter;
    static List<int[]> commands;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        know = Integer.parseInt(st.nextToken());
        result = 0;
        stupid = new boolean[N + 1];
        Arrays.fill(stupid, true);
        reporter = new ArrayList<>();

        while (st.hasMoreTokens()) {
            reporter.add(Integer.parseInt(st.nextToken()));
        }

        adjMatrix = new boolean[N+1][N+1];
        visited = new boolean[N+1];
        commands = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String[] temp = br.readLine().split(" ");
            int[] info = Arrays.stream(temp).mapToInt(Integer::parseInt).toArray();
            commands.add(info);
            for (int j = 1; j < info.length; j++) {
                int a = info[j];
                for (int k = j+1; k < info.length; k++) {
                    int b = info[k];
                    adjMatrix[a][b] = true;
                    adjMatrix[b][a] = true;
                }
            }
        }


        for (int rep : reporter) {
            if(visited[rep]) continue;
            visited[rep] = true;
            stupid[rep] = false;
            DFS(rep);
        }

        for (int[] command : commands) {
            if(isPossible(command)) result++;
        }

        System.out.println(result);
    }

    static void DFS(int now) {

        for (int i = 1; i <= N; i++) {
            if(visited[i] || !adjMatrix[now][i]) continue;
            visited[i] = true;
            stupid[i] = false;
            DFS(i);
        }
    }
    
    static boolean isPossible(int[] command) {
        for (int i = 1; i < command.length; i++) {
            if(!stupid[command[i]]) return false;
        }
        return true;
    }
}
