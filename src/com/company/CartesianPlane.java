package com.company;

import java.util.*;

public class CartesianPlane {
    private static final List<PlottedPoint> listOfPlottedPoints = new ArrayList<>();

    public void plotPoint(int x, int y) {
        if (findPoint(x,y) == null) {
            listOfPlottedPoints.add(new PlottedPoint(x,y));
            updateXAndYPointMatches();
        }
    }

    private void updateXAndYPointMatches(){
        storeMatchingXValues();
        storeMatchingYValues();
    }
    public void removeAllPlottedPoints() {
        listOfPlottedPoints.clear();
        updateXAndYPointMatches();
    }

    public void removePlottedPoint(int x, int y) {
        listOfPlottedPoints.remove(findPoint(x,y));
        updateXAndYPointMatches();
        printListOfPlottedPoints();
    }

    public void printListOfPlottedPoints() {
        if (listOfPlottedPoints.size() > 0) {
            System.out.println("Points on plane:");
        for (PlottedPoint point: listOfPlottedPoints) {
            System.out.println("(" + point.getX() + ";" + point.getY() + ")");
            }
        } else {
            System.out.println("No points on plane");
        }
    }

    public PlottedPoint findPoint(int x,int y){
        for (PlottedPoint point: listOfPlottedPoints) {
            if (point.getX() == x && point.getY() == y){
                return point;
            }
        } return null;
    }

    private List<PlottedPoint> findPointsWithMatchingXValues(PlottedPoint plottedPoint) {
        List<PlottedPoint> listOfMatchingXPoints = new ArrayList<>();
        for (PlottedPoint point: listOfPlottedPoints) {
            if (point.getX() == plottedPoint.getX() && point != plottedPoint) {
                listOfMatchingXPoints.add(point);
            }
        } return listOfMatchingXPoints;
    }

    private Map<PlottedPoint,List<PlottedPoint>> storeMatchingXValues() {
        Map<PlottedPoint,List<PlottedPoint>> map = new HashMap<>();
        for (PlottedPoint point: listOfPlottedPoints) {
            map.put(point,findPointsWithMatchingXValues(point));
        }
        return map;
    }

    private List<PlottedPoint> findPointsWithMatchingYValues(PlottedPoint plottedPoint) {
        List<PlottedPoint> listOfMatchingYPoints = new ArrayList<>();
        for (PlottedPoint point: listOfPlottedPoints) {
            if (point.getY() == plottedPoint.getY() && point != plottedPoint) {
                listOfMatchingYPoints.add(point);
            }
        } return listOfMatchingYPoints;
    }

    private Map<PlottedPoint,List<PlottedPoint>> storeMatchingYValues(){
        Map<PlottedPoint,List<PlottedPoint>> map = new HashMap<>();
        for (PlottedPoint point: listOfPlottedPoints) {
            map.put(point,findPointsWithMatchingYValues(point));
        }
        return map;
    }

    public int findRectangles(){
        int rectangle = 0;
        for (PlottedPoint initialPointInList: listOfPlottedPoints) {
            for (PlottedPoint comparedPointInList: listOfPlottedPoints){
                if (validateRectangle(initialPointInList,comparedPointInList)){
                    rectangle++;
                }
            }
        }
        return rectangle/4;
    }

    private boolean validateRectangle(PlottedPoint plottedPoint, PlottedPoint comparedPoint) {
        return compareYPointsToXPoints(plottedPoint, comparedPoint) && compareYPointsToXPoints(comparedPoint, plottedPoint) && (plottedPoint != comparedPoint);
    }

    private boolean compareYPointsToXPoints(PlottedPoint plottedPoint,PlottedPoint comparePoint) {
        for (PlottedPoint YPoint: storeMatchingYValues().get(plottedPoint)){
            for (PlottedPoint XPoint: storeMatchingXValues().get(comparePoint)){
                if (XPoint.equals(YPoint)){
                    return true;
                }
            }
        } return false;
    }
}
