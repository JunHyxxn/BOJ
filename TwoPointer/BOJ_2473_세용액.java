package algo0302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_2473_세용액 {
    static int N;
    static int[] numbers;
    static List<Integer> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        numbers = Arrays.stream(numbers).sorted().toArray();
        result = new ArrayList<>();
        for (int i = 0; i < N-2; i++) {
            int j = i+1;
            int k = N-1;
            while(true) {
                if(j>=k) break;
                Long nowSum = Long.parseLong(String.valueOf((long)numbers[i] + numbers[j] + numbers[k]));
                if(Math.abs(nowSum) < (result.isEmpty() ? Long.MAX_VALUE : Math.abs(result.stream().mapToLong(Long::valueOf).sum()))) {
                    result.clear();
                    result.add(numbers[i]);
                    result.add(numbers[j]);
                    result.add(numbers[k]);
                }
                if(nowSum > 0) k--;
                else j++;
            }
        }

        System.out.println(result.get(0) + " " + result.get(1) + " " + result.get(2));
    }

}


//  // Memory 초과
//    static int N;
//    static int[] numbers;
//    static List<Integer> result;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//        result = new ArrayList<>();
//
//        for (int i = 0; i < N; i++) {
//            dijkstra(i);
//        }
//        int[] res = result.stream().mapToInt(m -> numbers[m]).sorted().toArray();
//        StringBuilder sb = new StringBuilder();
//        for (int r : res) {
//            sb.append(r).append(" ");
//        }
//        System.out.println(sb);
//    }
//
//    static void dijkstra(int idx) {
//        PriorityQueue<List<Integer>> pq = new PriorityQueue<>((o1, o2) -> {
//            return Math.abs(o1.stream().mapToInt(Integer::valueOf).sum()) - Math.abs(o2.stream().mapToInt(Integer::valueOf).sum());
//        });
//        List<Integer> list = new ArrayList<>();
//        list.add(idx);
//        pq.offer(list);
//
//        boolean[] visited = new boolean[N];
//
//        while (!pq.isEmpty()) {
//            List<Integer> now = pq.poll();
//            if (now.size() == 3) {
//                if (Math.abs(now.stream().mapToLong(m-> Long.valueOf(String.valueOf(numbers[m]))).sum()) <
//                        (result.size() == 0 ? Integer.MAX_VALUE : Math.abs(result.stream().mapToLong(m-> Long.valueOf(String.valueOf(numbers[m]))).sum()))) {
//                    result = now;
//                    return;
//                }
//            }
//            visited[now.get(now.size()-1)] = true;
//            for (int i = 0; i < N; i++) {
//                if(visited[i]) continue;
//                List<Integer> nxt = new ArrayList<>();
//                nxt.addAll(now);
//                nxt.add(i);
//                pq.offer(nxt);
//            }
//        }
//    }
//
//}
