package day0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class CodeTree_RabbitRace {

    static int Q, N, M, P, K, S, L, pid, di, pidT;
    static PriorityQueue<Rabbit> rabbits;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        rabbits = new PriorityQueue<>(sortByMoveRabbit());
        Q = Integer.parseInt(br.readLine());
        List<String> commands = new ArrayList<>();

        for (int q = 0; q < Q; q++) {
            commands.add(br.readLine());
        }
        for (String command : commands) {
            String[] cmd = command.split(" ");
            int query = Integer.parseInt(cmd[0]);
            if (query == 100) { // 시작 준비
                N = Integer.parseInt(cmd[1]);
                M = Integer.parseInt(cmd[2]);
                P = Integer.parseInt(cmd[3]);
                for (int p = 4; p < cmd.length; p += 2) {
                    pid = Integer.parseInt(cmd[p]);
                    di = Integer.parseInt(cmd[p + 1]);
                    rabbits.add(new Rabbit(pid, di));
                }
            } else if (query == 200) {
                K = Integer.parseInt(cmd[1]);
                S = Integer.parseInt(cmd[2]);
                Set<Integer> jumpedRabbits = new HashSet<>();
                Map<Integer, Integer> addScores = new HashMap<>();
                long totalAddScore = 0L;
                for (int k = 0; k < K; k++) {
                    // 경주 시작 준비 - 이동할 토끼 결정
                    Rabbit moveRabbit = rabbits.poll();
                    // 이동
                    moveRabbit.move();
                    jumpedRabbits.add(moveRabbit.pid);
                    addScores.putIfAbsent(moveRabbit.pid, 0);
                    addScores.computeIfPresent(moveRabbit.pid, (key, value) -> value + moveRabbit.getLoc());
                    totalAddScore += moveRabbit.getLoc();
                    rabbits.offer(moveRabbit);
                }
                // 점수 갱신 한 번에!!
                // 경주 종료 시 가장 우선순위 높은 토끼에게 S 점수 추가
                Rabbit candidateRabbit = Rabbit.ofDummy();
                for (Rabbit r : rabbits) {
                    r.score += (totalAddScore - addScores.getOrDefault(r.pid, 0));
                    if (!jumpedRabbits.contains(r.pid)) {
                        continue;
                    }
                    if (r.getLoc() > candidateRabbit.getLoc()) {
                        candidateRabbit = r;
                    } else if (r.getLoc() == candidateRabbit.getLoc()) {
                        if (r.r > candidateRabbit.r) {
                            candidateRabbit = r;
                        } else if (r.r == candidateRabbit.r) {
                            if (r.c > candidateRabbit.c) {
                                candidateRabbit = r;
                            } else if (r.c == candidateRabbit.c) {
                                if (r.pid > candidateRabbit.pid) {
                                    candidateRabbit = r;
                                }
                            }
                        }
                    }
                }
                candidateRabbit.score += S;
            } else if (query == 300) {
                pidT = Integer.parseInt(cmd[1]);
                L = Integer.parseInt(cmd[2]);
                for (Rabbit r : rabbits) {
                    if (r.pid == pidT) {
                        r.di = r.di * L;
                        break;
                    }
                }
            } else if (query == 400) {
                List<Rabbit> finishRabbits = new ArrayList<>(rabbits);
                finishRabbits.sort(Comparator.comparing(Rabbit::getScore).reversed());
                System.out.println(finishRabbits.get(0).getScore());
            } else {
                System.exit(0);
            }
        }

    }

    static Comparator<Rabbit> sortByMoveRabbit() {
        return Comparator.comparing(Rabbit::getJump)
                         .thenComparing(ascLoc())
                         .thenComparing(ascR())
                         .thenComparing(ascC())
                         .thenComparing(ascPid());
    }


    static Comparator<Rabbit> ascLoc() {
        return (o1, o2) -> (o1.r + o1.c) - (o2.r + o2.c);
    }


    static Comparator<Rabbit> ascR() {
        return (o1, o2) -> o1.r - o2.r;
    }


    static Comparator<Rabbit> ascC() {
        return (o1, o2) -> o1.c - o2.c;
    }

    static Comparator<Rabbit> ascPid() {
        return (o1, o2) -> o1.pid - o2.pid;
    }

    static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getLoc() {
            return x + y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }

    static class Rabbit {

        int pid;
        int di;
        int r;
        int c;
        int jump;
        long score; // score는 21억을 넘어갈 수 있다. -> long 타입으로 선언

        public Rabbit(int pid, int di) {
            this.pid = pid;
            this.di = di;
            this.r = 1;
            this.c = 1;
            this.jump = 0;
            this.score = 0L;
        }

        private Rabbit(int pid, int di, int r, int c) {
            this.pid = pid;
            this.di = di;
            this.r = r;
            this.c = c;
            this.jump = 0;
            this.score = 0L;
        }

        public static Rabbit ofDummy() {
            return new Rabbit(-1, -1, -1, -1);
        }

        public int getLoc() {
            return (this.r + this.c);
        }

        public int getJump() {
            return jump;
        }

        public long getScore() {
            return score;
        }

        public void move() {
            List<Point> points = new ArrayList<>();
            points.add(up());
            points.add(down());
            points.add(left());
            points.add(right());
            Comparator<Point> sortByPoint = Comparator.comparing(Point::getLoc, Comparator.reverseOrder())
                                                      .thenComparing(Point::getX, Comparator.reverseOrder())
                                                      .thenComparing(Point::getY, Comparator.reverseOrder());
            points.sort(sortByPoint);
            Point point = points.get(0);
            this.r = point.x;
            this.c = point.y;
            this.jump++;
        }

        public Point up() {
            int distance = di % (2 * (N - 1));
            int upCnt = Math.min(r - 1, distance);
            distance -= upCnt;
            int downCnt = Math.min(N - 1, distance);
            distance -= downCnt;
            upCnt += distance;
            int newR = r - upCnt + downCnt;
            return new Point(newR, c);
        }

        public Point down() {
            int distance = di % (2 * (N - 1));
            int downCnt = Math.min(N - r, distance);
            distance -= downCnt;
            int upCnt = Math.min(N - 1, distance);
            distance -= upCnt;
            downCnt += distance;
            int newR = r - upCnt + downCnt;
            return new Point(newR, c);
        }

        public Point left() {
            int distance = di % (2 * (M - 1));
            int leftCnt = Math.min(c - 1, distance);
            distance -= leftCnt;
            int rightCnt = Math.min(M - 1, distance);
            distance -= rightCnt;
            leftCnt += distance;
            int newC = c - leftCnt + rightCnt;
            return new Point(r, newC);
        }

        public Point right() {
            int distance = di % (2 * (M - 1));
            int rightCnt = Math.min(M - c, distance);
            distance -= rightCnt;
            int leftCnt = Math.min(M - 1, distance);
            distance -= leftCnt;
            rightCnt += distance;
            int newC = c - leftCnt + rightCnt;
            return new Point(r, newC);
        }

    }


}
