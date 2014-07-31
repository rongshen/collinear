import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by rshen on 7/29/2014.
 */
public class Fast {

    private void findCollinearPoints(Point[] pointList) {

        drawAllPoints(pointList);

        if (pointList.length < 4) {
            return;
        }

        Arrays.sort(pointList);

        Point[] sortArray = new Point[pointList.length];

        for (int j = 0; j < sortArray.length; j++) {
            sortArray[j] = pointList[j];
        }

        for (int i = 0; i < pointList.length; i++) {
            Point p1 = pointList[i];

            Arrays.sort(sortArray, p1.SLOPE_ORDER);

            findSegments(p1, sortArray);
        }
    }

    private void findSegments(Point p1, Point[] list) {
        int count = 0;
        boolean hasPreviousPoint = false;
        double slopeValue = p1.slopeTo(list[0]);
        ArrayList<Point> segment = new ArrayList<Point>();


        for (int k = 1; k < list.length; k++) {
            Point currentPoint =  list[k];
            double newSlope = p1.slopeTo(currentPoint);

            if (newSlope == slopeValue) {
                count++;
                segment.add(currentPoint);
                if (currentPoint.compareTo(p1) < 0) {
                    hasPreviousPoint = true;
                }
            } else {
                if (count >= 3) {
                    PrintAndDrawSegments(p1, hasPreviousPoint, segment);
                }
                count = 1;
                slopeValue = newSlope;
                segment.clear();
                segment.add(currentPoint);
                hasPreviousPoint = currentPoint.compareTo(p1) < 0 ? true : false;
            }
        }
        if (count >= 3) {
            PrintAndDrawSegments(p1, hasPreviousPoint, segment);
        }
    }

    private void PrintAndDrawSegments(Point p1, boolean hasPreviousPoint, ArrayList<Point> segment) {
        segment.add(p1);
        if (!hasPreviousPoint) {
            printOutPoints(segment);
            drawSegments(segment);
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

    private void drawSegments(ArrayList<Point> list) {
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLUE);
        list.get(0).drawTo(list.get(list.size() - 1));
    }

    private void printOutPoints(ArrayList<Point> list) {
        Collections.sort(list);
        StringBuilder outPut = new StringBuilder();
        for (int i = 0; i < list.size() - 1; i++) {
            outPut.append(list.get(i).toString());
            outPut.append(" -> ");
        }
        outPut.append(list.get(list.size() - 1).toString());
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
        for (int i = 0; i < size; i++) {
            int x = readIn.readInt();
            int y = readIn.readInt();
            pointList[i] = new Point(x, y);
        }

        Fast test = new Fast();
        test.findCollinearPoints(pointList);
    }
}
