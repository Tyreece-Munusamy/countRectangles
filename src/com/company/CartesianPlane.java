package com.company;

import java.util.*;

public class CartesianPlane {
//    public static void main(String[] args) {
//        boolean continueLoop = true;
//        CartesianPlane cPlane = new CartesianPlane();
//        Scanner scanner = new Scanner(System.in);
//
//        while (continueLoop) {
//            System.out.println("Enter a point: ");
//            System.out.println("Enter X value:");
//            int xValue = scanner.nextInt();
//            System.out.println("Enter Y value:");
//            int yValue = scanner.nextInt();
//            cPlane.plotPoint(xValue, yValue);
//            cPlane.checkToPrintPlottedPoints();
//            System.out.println("Number of rectangles:" + cPlane.findRectangles());
//        }
//    }

    private static final List<PlottedPoint> listOfPlottedPoints = new ArrayList<>();

    public void plotPoint(int x, int y) {
        if (findPoint(x, y) == null) {
            listOfPlottedPoints.add(new PlottedPoint(x, y));
            // update each point's map(containing all similar points) when a new point is added
            updateXAndYPointMatches();
        }
    }

    private void updateXAndYPointMatches() {
        // update maps of similar points on both maps
        storeMatchingXValues();
        storeMatchingYValues();
    }

    public void removeAllPlottedPoints() {
        // start new array of points
        listOfPlottedPoints.clear();
        updateXAndYPointMatches();
    }

    public void removePlottedPoint(int x, int y) {
        listOfPlottedPoints.remove(findPoint(x, y));
        // both maps need to be updated due to change in list of points
        updateXAndYPointMatches();
        checkToPrintPlottedPoints();
    }

    public void checkToPrintPlottedPoints() {
        if (listOfPlottedPoints.size() > 0) {
            printPlottedPoints();
        } else {
            System.out.println("No points on plane");
        }
    }

    private void printPlottedPoints() {
        System.out.println("Points on plane:");
        for (PlottedPoint point : listOfPlottedPoints) {
            System.out.println("(" + point.getX() + ";" + point.getY() + ")");
            // github 111
        }
    }

    public PlottedPoint findPoint(int x, int y) {
        for (PlottedPoint point : listOfPlottedPoints) {
            if (point.getX() == x && point.getY() == y) {
                return point;
            }
        }
        return null;
    }

    private List<PlottedPoint> findPointsWithMatchingXValues(PlottedPoint plottedPoint) {
        List<PlottedPoint> listOfMatchingXPoints = new ArrayList<>();
        // arraylist storing all points with matching x values
        for (PlottedPoint point : listOfPlottedPoints) {
            if (point.getX() == plottedPoint.getX() && point != plottedPoint) {
                // cannot add itself to list of matching points
                listOfMatchingXPoints.add(point);
            }
        }
        return listOfMatchingXPoints;
    }

    private Map<PlottedPoint, List<PlottedPoint>> storeMatchingXValues() {
        Map<PlottedPoint, List<PlottedPoint>> map = new HashMap<>();
        // map storing matching x values for every point in the list
        for (PlottedPoint point : listOfPlottedPoints) {
            map.put(point, findPointsWithMatchingXValues(point));
        }
        return map;
    }

    private List<PlottedPoint> findPointsWithMatchingYValues(PlottedPoint plottedPoint) {
        List<PlottedPoint> listOfMatchingYPoints = new ArrayList<>();
        for (PlottedPoint point : listOfPlottedPoints) {
            if (point.getY() == plottedPoint.getY() && point != plottedPoint) {
                listOfMatchingYPoints.add(point);
            }
        }
        return listOfMatchingYPoints;
    }

    private Map<PlottedPoint, List<PlottedPoint>> storeMatchingYValues() {
        Map<PlottedPoint, List<PlottedPoint>> map = new HashMap<>();
        for (PlottedPoint point : listOfPlottedPoints) {
            map.put(point, findPointsWithMatchingYValues(point));
        }
        return map;
    }

    public int findRectangles() {
        int corners = 0;
        for (PlottedPoint initialPointInList : listOfPlottedPoints) {
            for (PlottedPoint comparedPointInList : listOfPlottedPoints) {
                // looping through every point in list and comparing it to every other point
                if (validateRectangle(initialPointInList, comparedPointInList)) {
                    // returns true 4 times for every rectangle
                    corners++;
                }
            }
        }
        return corners / 4;
    }

    private boolean validateRectangle(PlottedPoint plottedPoint, PlottedPoint comparedPoint) {
        return (plottedPoint != comparedPoint) &&
                compareYPointsToXPoints(plottedPoint, comparedPoint) && compareYPointsToXPoints(comparedPoint, plottedPoint);
        // if points are not equal and compareYPointsToXPoints returns true if points are switched the if is a rectangle
    }

    private boolean compareYPointsToXPoints(PlottedPoint plottedPoint, PlottedPoint comparePoint) {
        for (PlottedPoint plottedPoint1 : storeMatchingYValues().get(plottedPoint)) {
            // loops through all points with matching Y points in plottedPoint
            for (PlottedPoint comparedPoint1 : storeMatchingXValues().get(comparePoint)) {
                // loops through all points with matching X points in comparePoint
                if (comparedPoint1.equals(plottedPoint1)) {
                    // if the points are equal the points are opposite sides of a rectangle
                    return true;
                }
            }
        }
        return false;
    }
}
