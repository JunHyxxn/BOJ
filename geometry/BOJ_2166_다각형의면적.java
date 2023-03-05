package algo0305;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2166_다각형의면적 {
    static int N;
    static List<Point> points;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        points = new ArrayList<>();
        int x = 0, y = 0;
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        Point firstPoint = new Point(x, y);
        points.add(firstPoint);
        for(int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            points.add(new Point(x, y));
        }
        points.add(firstPoint);

        Double xy = 0d;
        Double yx = 0d;
        for (int i = 1; i <= N; i++) {
            Point prevPoint = points.get(i-1);
            Point nowPoint = points.get(i);
            xy += (long)prevPoint.x * nowPoint.y;
            yx += (long)prevPoint.y * nowPoint.x;
        }
        System.out.printf("%.1f", Math.abs((xy-yx) / 2));
    }
}
