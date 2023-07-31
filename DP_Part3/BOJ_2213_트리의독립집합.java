package day0731;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_2213 {

    private static int N;
    private static int[] weights;
    private static Map<Integer, List<Integer>> adjList;
    private static int[][] dpTree;
    private static boolean[] visited;
    private static List<Integer> path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        weights = new int[N + 1];
        adjList = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
            adjList.put(i, new ArrayList<>());
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        dpTree = new int[N + 1][2];
        visited = new boolean[N + 1];
        visited[1] = true;
        path = new ArrayList<>();
        // 최대 독립 집합 탐색
        dfs(1, -1);
        boolean contained = true; // 루트 노드 포함 여부
        int result = 0;
        if (dpTree[1][0] <= dpTree[1][1]) { // 같으면 경로에 포함시키는 것이 최대 독립 집합이 됨
            path.add(1);
            result = dpTree[1][1];
        } else {
            contained = false;
            result = dpTree[1][0];
        }
        visited = new boolean[N + 1];
        visited[1] = true;
        // 경로 탐색
        findPath(1, contained);

        result = result == 0 ? path.size() : result; // 가중치 0이라면 집합 수로 재정의
        path.sort((o1, o2) -> o1 - o2); // 정렬
        System.out.println(result);
        StringBuilder sb = new StringBuilder();
        path.forEach(p -> sb.append(p).append(" "));
        System.out.println(sb);
    }
    // 최대 독립 집합 탐색
    static void dfs(int now, int parent) {
        for (Integer nxt : adjList.get(now)) {
            if (visited[nxt]) {
                continue;
            }
            visited[nxt] = true;
            dfs(nxt, now);
        }
        // 후외 순회처럼 Sub Tree의 해를 구해야 부모 노드로 돌아갈 수 있다
        if (isLeaf(now, parent)) { // 양방향 이동이 가능하기 떄문에 부모 노드 제외하기 위해 메소드에 전달
            dpTree[now][1] = weights[now]; // 리프 노드라면 가장 작은 단위의 서브 트리 해 저장
        } else {
            // 미선택 => 자식들 중 최대값들 골라오면 됨.
            // 선택 => 모든 자식 미선택에서 가져온다.
            for (Integer child : adjList.get(now)) {
                if (child == parent) {
                    continue;
                }
                dpTree[now][0] += Math.max(dpTree[child][0], dpTree[child][1]);
                dpTree[now][1] += dpTree[child][0];
            }
            dpTree[now][1] += weights[now];
        }
    }

    static boolean isLeaf(int node, int parent) {
        return ((adjList.get(node).size() == 1) && (adjList.get(node).get(0) == parent));
    }
    // 경로 탐색 (역추적)
    static void findPath(int node, boolean contained) {
        for (Integer nxt : adjList.get(node)) {
            if (visited[nxt]) {
                continue;
            }
            visited[nxt] = true;
            if (contained) {
                findPath(nxt, false);
            } else {
                if (dpTree[nxt][0] <= dpTree[nxt][1]) { // 선택된 노드 -> 같은경우 포함 시켜야 더 경로가 많아서 0일 경우 최대 독립 값이 커진다.
                    findPath(nxt, true);
                    path.add(nxt);
                } else {
                    findPath(nxt, false);
                }
            }
        }
    }

}
