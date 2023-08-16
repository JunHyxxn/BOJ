package day0815;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CodeTree_MazeRunner {

    static final int[][] dxdy = {
            { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
    };
    static int N, M, K, result;
    static int[][] miro;
    static List<Point> participants;
    static Point exit;
    static final int EXIT_NUM = -999999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        miro = new int[N][N];
        for (int i = 0; i < N; i++) {
            miro[i] = Arrays.stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt).toArray();
        }
        participants = new ArrayList<>();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            participants.add(new Point(x, y));
            miro[x][y] -= 1;
        }
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        miro[x][y] = EXIT_NUM;
        exit = new Point(x, y);
        result = 0;
        play();
        System.out.println(result);
        System.out.println((exit.x+1) + " " + (exit.y+1));
    }

    static boolean isValid(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= N);
    }

    static void play() {
        for (int k = 0; k < K; k++) {
            // 이동
            move();
            if(participants.isEmpty()) return;

            // 회전
            rotate();
        }
    }

    static void move() {
        List<Point> remainParticipants = new ArrayList<>();
        for (Point par : participants) {
            Point movedParticipant = searchMove(par, exit);
            if(movedParticipant != null) remainParticipants.add(movedParticipant);
        }
        participants = remainParticipants;
    }

    static Point searchMove(Point participant, Point exit) {
        int distance = manhattanDistance(participant, exit);
        for (int i = 0; i < 4; i++) {
            int nx = participant.x + dxdy[i][0];
            int ny = participant.y + dxdy[i][1];
            Point movedParticipant = new Point(nx, ny);
            if (!isValid(nx, ny) || miro[nx][ny] > 0
                    || manhattanDistance(movedParticipant, exit) >= distance) continue;
            if(miro[nx][ny] == EXIT_NUM) {
                result++;
                miro[participant.x][participant.y]  += 1;
                return null;
            }
            result++;
            miro[participant.x][participant.y]  += 1;
            miro[nx][ny] -= 1;
            return movedParticipant;
        }
        return participant;
    }


    static int manhattanDistance(Point source, Point destination) {
        return Math.abs(source.x - destination.x) + Math.abs(source.y - destination.y);
    }

    static void updateParticipants() {
        participants = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (miro[i][j] >= 0) continue;
                else if (miro[i][j] == EXIT_NUM) {
                    exit.x = i;
                    exit.y = j;
                    continue;
                }
                for (int k = 0; k < Math.abs(miro[i][j]); k++) {
                    participants.add(new Point(i, j));
                }

            }
        }
    }

    static void rotate() {
        Square square = minSquare(findMaxLen());
        int[][] originMiro = deepCopy();
        int x = square.x;
        int y = square.y;
        int len = square.len;
        int T = x+y+len;
        int dif = x-y;
        for (int i = x; i <= x+len; i++) {
            for (int j = y; j <= y+len; j++) {
                int originNum = originMiro[i][j];
                int nx = j+dif;
                int ny = T-i;
                miro[nx][ny] = originNum > 0 ? originNum -1 : originNum;
            }
        }
        updateParticipants();
    }

    static int findMaxLen() {
        int len = Math.max(exit.x, exit.y);
        len = Math.max(len, N-exit.y-1);
        len = Math.max(len, N-exit.x-1);
        return len;
    }

    static Square minSquare(int len) {
        Comparator<Square> sortBySquare = Comparator.comparing(Square::getX).thenComparing(Square::getY);
        for (int l = 1; l <= len; l++) {
            PriorityQueue<Square> pq = new PriorityQueue<>(sortBySquare);
            for (int i = exit.x-l; i <= exit.x+l; i++) {
                for (int j = exit.y-l; j <= exit.y+l; j++) {
                    if(isValid(i, j) && miro[i][j] != EXIT_NUM && miro[i][j] < 0) {
                        pq.offer(findLeftTopPoint(i, j, l));
                    }
                }
            }
            if (!pq.isEmpty()) return pq.peek();
        }
        return null;
    }

    static Square findLeftTopPoint(int x1, int y1, int len) {
        int x2 = exit.x;
        int y2 = exit.y;
        int difR = Math.abs(x1 - x2);
        int difC = Math.abs(y1 - y2);
        int leftTopR, leftTopC;
        if (difR == len) leftTopR = Math.min(x1, x2);
        else leftTopR = Math.min(x1, x2) - (len - difR);
        leftTopR = Math.max(leftTopR, 0);
        if (difC == len) leftTopC = Math.min(y1, y2);
        else leftTopC = Math.min(y1, y2) - (len - difC);
        leftTopC = Math.max(leftTopC, 0);
        return new Square(leftTopR, leftTopC, len);
    }

    static int[][] deepCopy() {
        int[][] copyMiro = new int[N][N];
        for (int i = 0; i < N; i++) {
            copyMiro[i] = Arrays.copyOf(miro[i], miro[i].length);
        }
        return copyMiro;
    }

    static class Square {
        int x;
        int y;
        int len;
        public Square(int x, int y, int len) {
            this.x = x;
            this.y= y;
            this.len = len;
        }
        public int getX() {
            return this.x;
        }
        public int getY() {
            return this.y;
        }
    }
}
