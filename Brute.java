import java.util.Arrays;

/**
 * Created by rshen on 7/28/2014.
 */
public class Brute {

    private void findCollinearPoints(Point[] pointList) {
        if (pointList == null) {
            throw new IllegalArgumentException();
        }
        int listSize = pointList.length;
        if (listSize < 4) {
            return;
        }

        drawAllPoints(pointList);

        for (int i = 0; i < listSize - 3; i++) {
                Point p1 = pointList[i];
            for (int j = i + 1; j < listSize - 2; j++) {
                    Point p2 = pointList[j];
                for (int p = j + 1; p < listSize - 1; p++) {
                        Point p3 = pointList[p];
                    for (int q = p + 1; q < listSize; q++) {
                            Point p4 = pointList[q];
                            if (p1.SLOPE_ORDER.compare(p2, p3) == 0) {
                                if (p1.SLOPE_ORDER.compare(p2, p4) == 0) {
                                    if (p2.SLOPE_ORDER.compare(p1, p4) == 0) {
                                        Point[] segment = new Point[4];
                                        segment[0] = p1;
                                        segment[1] = p2;
                                        segment[2] = p3;
                                        segment[3] = p4;
                                        printOutPoints(segment);
                                        drawSegments(segment);
                                    }
                                }
                            }
                    }
                }
            }
        }
    }

    private void drawAllPoints(Point[] pointList) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.01);
        for (Point p : pointList) {
            p.draw();
        }

    }

    private void drawSegments(Point[] list) {
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLUE);
        list[0].drawTo(list[list.length -1]);
    }

    private void printOutPoints(Point[] list) {
        Arrays.sort(list);
        StringBuilder outPut = new StringBuilder();
        for (int i = 0; i < list.length - 1; i++) {
            outPut.append(list[i].toString());
            outPut.append(" -> ");
        }
        outPut.append(list[list.length - 1].toString());
        System.out.println(outPut.toString());
    }

    public static void main(String[] args) {
        if (args.length <= 0 || args.length > 1) {
            return;
        }

        String fileName = args[0];
        In readIn = new In(fileName);

        int size = readIn.readInt();
        if (size <= 0) {
            return;
        }
        Point[] pointList = new Point[size];
        int index = 0;
        for (int i = 0; i < size; i++) {
            int x = readIn.readInt();
            int y = readIn.readInt();
            pointList[i] =  new Point(x, y);
        }

        Brute test = new Brute();
        test.findCollinearPoints(pointList);
    }
}
