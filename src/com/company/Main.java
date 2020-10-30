package com.company;
import java.util.Scanner;

public class Main {
    public static CartesianPlane plane = new CartesianPlane();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        /*Write a program to calculate the numbers of rectangles that appear
        if certain points are plotted on a cartesian plane*/

        boolean continueLoop = true;
        printListOfOptions();
        while(continueLoop) {
            System.out.println("Enter Option: ");
            int userInput = scanner.nextInt();
            scanner.nextLine();

            switch (userInput) {
                case 1:
                    option1AddPoints();
                    break;
                case 2:
                    option2RemovePoint();
                    break;
                case 3:
                    option3RemoveAllPoints();
                    break;
                case 4:
                    option4DisplayAllPlottedPoints();
                    break;
                case 5:
                    option5NumberOfRectangles();
                    break;
                case 6:
                    continueLoop = false;
            }
        }
    }
    private static void printListOfOptions() {
        System.out.println("1 -> Add point to plane\n" +
                "2 -> Remove point from plane\n" +
                "3 -> Remove all plotted points\n" +
                "4 -> Display all plotted points\n" +
                "5 -> Calculate number of rectangles formed by plotted points\n" +
                "6 -> Exit loop\n");
    }
    private static void option1AddPoints(){
        boolean continueLoop = true;
        while(continueLoop) {
            System.out.println("Adding point -> ");
            System.out.println("Enter x:");
            int x = scanner.nextInt();
            System.out.println("Enter y:");
            int y = scanner.nextInt();
            scanner.nextLine();
            plane.plotPoint(x,y);
            System.out.println("Press 1 to add another point or 2 to print options and end loop");
            if (scanner.nextInt() == 2) {
                printListOfOptions();
                continueLoop = false;
            }
        }
    }
    private static void option2RemovePoint() {
        boolean continueLoop = true;
        while(continueLoop) {
            System.out.println("Removing point -> ");
            System.out.println("Enter x:");
            int x = scanner.nextInt();
            System.out.println("Enter y:");
            int y = scanner.nextInt();
            scanner.nextLine();
            plane.removePlottedPoint(x,y);
            System.out.println("Press 1 to remove another point or 2 to print options and end loop");
            if (scanner.nextInt() == 2) {
                printListOfOptions();
                continueLoop = false;
            }
        }
    }
    private static void option3RemoveAllPoints() {
        System.out.println("Do you want remove all points on the plane? Y/N");
        if (!scanner.nextLine().toUpperCase().equals("N")) {
            plane.removeAllPlottedPoints();
            System.out.println("All plotted points were removed");
        } else {
            System.out.println("Points not removed");
        }
        printListOfOptions();
    }
    private static void option4DisplayAllPlottedPoints() {
        plane.checkToPrintPlottedPoints();
        printListOfOptions();
    }
    private static void option5NumberOfRectangles(){
        plane.checkToPrintPlottedPoints();
        if (plane.findRectangles() > 1){
        System.out.println(plane.findRectangles() + " rectangles were found");
        } else {
            System.out.println(plane.findRectangles() + " rectangle was found");
        }
        System.out.println();
        printListOfOptions();
    }
    //testing github change 1
}
