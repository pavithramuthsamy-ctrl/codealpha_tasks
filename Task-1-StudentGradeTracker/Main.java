import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("     STUDENT GRADE TRACKER");
        System.out.println("======================================");

        System.out.print("Enter the number of students: ");
        int n = sc.nextInt();

        System.out.print("Enter the number of subjects: ");
        int subjects = sc.nextInt();

        String[] names = new String[n];
        int[][] marks = new int[n][subjects];
        double[] averages = new double[n];
        char[] grades = new char[n];

        int highestScore = 0;
        int lowestScore = 100;

        for (int s = 0; s < n; s++) {

            System.out.println("\n----- Student " + (s + 1) + " -----");

            System.out.print("Enter student name: ");
            names[s] = sc.next();

            int total = 0;
            for (int i = 0; i < subjects; i++) {

                int mark;

                do {
                    System.out.print("Enter mark for Subject " + (i + 1) + ": ");
                    mark = sc.nextInt();

                    if (mark < 0 || mark > 100) {
                        System.out.println(
                                "Invalid mark! Please enter a mark between 0 and 100."
                        );
                    }

                } while (mark < 0 || mark > 100);
                marks[s][i] = mark;

                total += mark;

                if (mark > highestScore) {
                    highestScore = mark;
                }

                if (mark < lowestScore) {
                    lowestScore = mark;
                }
            }

            double average = (double) total / subjects;
            averages[s] = average;

            char grade;

            if (average >= 90) {
                grade = 'A';
            }
            else if (average >= 80) {
                grade = 'B';
            }
            else if (average >= 70) {
                grade = 'C';
            }
            else if (average >= 60) {
                grade = 'D';
            }
            else {
                grade = 'F';
            }

            grades[s] = grade;

            System.out.println("\nResult for " + names[s]);
            System.out.println("Total   = " + total);
            System.out.println("Average = " + average);
            System.out.println("Grade   = " + grade);
        }

        double highestAverage = averages[0];
        double lowestAverage = averages[0];

        int highestStudent = 0;
        int lowestStudent = 0;

        for (int i = 1; i < n; i++) {

            if (averages[i] > highestAverage) {
                highestAverage = averages[i];
                highestStudent = i;
            }

            if (averages[i] < lowestAverage) {
                lowestAverage = averages[i];
                lowestStudent = i;
            }
        }

        System.out.println("\n\n======================================");
        System.out.println("          SUMMARY REPORT");
        System.out.println("======================================");

        System.out.printf("%-15s", "Student");

        for (int i = 0; i < subjects; i++) {
            System.out.printf("%-8s", "Sub" + (i + 1));
        }

        System.out.printf("%-12s%-8s%n", "Average", "Grade");

        System.out.println("--------------------------------------");

        for (int i = 0; i < n; i++) {

            System.out.printf("%-15s", names[i]);

            for (int j = 0; j < subjects; j++) {
                System.out.printf("%-8d", marks[i][j]);
            }

            System.out.printf("%-12.2f%-8c%n",
                    averages[i],
                    grades[i]);
        }

        System.out.println("--------------------------------------");

        System.out.println("Highest Score   : " + highestScore);
        System.out.println("Lowest Score    : " + lowestScore);

        System.out.printf(
                "Highest Average : %.2f (%s)%n",
                highestAverage,
                names[highestStudent]
        );

        System.out.printf(
                "Lowest Average  : %.2f (%s)%n",
                lowestAverage,
                names[lowestStudent]
        );

        System.out.println("======================================");
        System.out.println("       END OF REPORT");
        System.out.println("======================================");

        sc.close();
    }
}