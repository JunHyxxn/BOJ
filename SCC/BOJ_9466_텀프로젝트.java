package algo0305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_9466_텀프로젝트 {
    static int T, N;
    static int[] students, group;
    static Map<Integer, List<Integer>> reverse;
    static Stack<Integer> stack;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for ( int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            students = new int[N+1];
            reverse = new HashMap<>();
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i<= N; i ++) {
                reverse.put(i, new ArrayList<>());
            }
            for (int i = 1; i <= N; i++) {
                int num = Integer.parseInt(st.nextToken());
                students[i] = num;
                reverse.get(num).add(i);
            }
            stack = new Stack<>();
            visited = new boolean[N+1];

            for(int i=1; i<=N; i++) {
                if(visited[i]) continue;
                visited[i] = true;
                algorithm(i);
            }
            visited = new boolean[N+1];
            group = new int[N+1];
            while(!stack.isEmpty()) {
                Integer now = stack.pop();
                if(visited[now]) continue;
                visited[now] = true;
                group[now] = now;
                reverseDFS(now, now);
            }

            Map<Integer, Integer> groupMemberCnt = new HashMap<>();
            for (int i = 1; i <= N; i++) {
                groupMemberCnt.put(i, 0);
            }
            for (int i = 1; i <= N; i++) {
                groupMemberCnt.put(group[i], groupMemberCnt.get(group[i]) + 1);
            }
            int result = 0;
            for (int i = 1; i <= N; i++) {
                if(i == group[i] && (students[i] != i && groupMemberCnt.get(i) == 1)) result++;
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    static void algorithm(Integer now) {
        Integer nxt = students[now];
        if(visited[nxt]) {
            stack.add(now);
            return;
        }
        visited[nxt] = true;
        algorithm(nxt);
        stack.add(now);
    }
    static void reverseDFS(Integer root, Integer now) {
        for (Integer nxt : reverse.get(now)) {
            if(visited[nxt]) continue;
            visited[nxt] = true;
            group[nxt] = root;
            reverseDFS(root, nxt);
        }
    }
}
