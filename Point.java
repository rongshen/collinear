/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            if (slopeTo(o1) < slopeTo(o2)) {
                return -1;
            }
            if (slopeTo(o1) > slopeTo(o2)) {
                return 1;
            }
            return 0;
        }
    };       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        double verticalDifference = that.y - y;
        double horizonDifference = that.x - x;
        if (horizonDifference == 0) {
            if (verticalDifference != 0) {
                return Double.POSITIVE_INFINITY;
            }
            return Double.NEGATIVE_INFINITY;
        }
        if (verticalDifference == 0) {
            return 0;
        }
        return  verticalDifference/horizonDifference;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (y < that.y) {
            return -1;
        }
        if (y > that.y) {
            return 1;
        }
        if (y == that.y) {
            if (x < that.x) {
                return -1;
            }
            if (x > that.x) {
                return 1;
            }
        }
        return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        comparePointsTest();
        calculateSlopeTest();
        compareSlopeTest();
    }

    private static void compareSlopeTest() {
        Point p0 = new Point(2, 3);
        Point p1 = new Point(4, 3);
        Point p2 = new Point(1, 3);
        System.out.println(p0.SLOPE_ORDER.compare(p1, p2));

        Point p3 = new Point(2, 5);
        System.out.println(p0.SLOPE_ORDER.compare(p1, p3));

        Point p4 = new Point(2, 1);
        System.out.println(p0.SLOPE_ORDER.compare(p3, p4));

        Point p5 = new Point(2, 3);
        System.out.println(p0.SLOPE_ORDER.compare(p0, p5));
        System.out.println(p0.SLOPE_ORDER.compare(p5, p4));
        System.out.println(p0.SLOPE_ORDER.compare(p5, p1));

        Point p6 = new Point(6, 7);
        Point p7 = new Point(8, 8);
        System.out.println(p0.SLOPE_ORDER.compare(p6, p7));

    }

    private static void calculateSlopeTest() {
        Point p0 = new Point(2, 3);
        Point p1 = new Point(1, 3);
        System.out.println(p0.slopeTo(p1));
        Point p2 = new Point(2, 5);
        System.out.println(p0.slopeTo(p2));
        Point p3 = new Point(2, 3);
        System.out.println(p0.slopeTo(p3));
        Point p4 = new Point(4, 5);
        System.out.println(p0.slopeTo(p4));
    }

    private static void comparePointsTest() {
        Point p0 = new Point(2, 3);
        Point p1 = new Point(1, 3);
        Point p2 = new Point(4, 3);
        Point p3 = new Point(2, 4);
        Point p4 = new Point(2, 1);
        Point p5 = new Point(2, 3);
        System.out.println(p0.compareTo(p1) > 0);
        System.out.println(p0.compareTo(p2) < 0);
        System.out.println(p0.compareTo(p3) < 0);
        System.out.println(p0.compareTo(p4) > 0);
        System.out.println(p0.compareTo(p5) == 0);
    }
}
