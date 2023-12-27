package main.java.exercise;

import main.java.framework.StudentInformation;
import main.java.framework.StudentSolution;

public class StudentSolutionImplementation implements StudentSolution {
    @Override
    public StudentInformation provideStudentInformation() {
        return new StudentInformation(
                "Christoph", // Vorname
                "Zeitler", // Nachname
                "12224224" // Matrikelnummer
        );
    }

    // Implementieren Sie hier Ihre Lösung für die Maximumsuche
    public int findMax(int[] numbers) {
        if(numbers.length < 1) return 0;
        int max = numbers[0];
        int maxIndex = 0;
        for (int i = 1; i < numbers.length; i++) {
            if (max <= numbers[i]) {
                max = numbers[i];
                maxIndex = i;
            }
        }
        return numbers[0] == max && maxIndex != 0 ? 0 : max;
    }

    // Implementieren Sie hier Ihre Lösung für das dichteste Punktepaar
    public void findClosestPair(Point[] points, ClosestPair closestPair) {
        double distance = 2147483647;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (distance(points[i], points[j]) < distance) {
                    distance = distance(points[i], points[j]);
                    closestPair.setPoint1(points[i]);
                    closestPair.setPoint2(points[j]);
                }
            }
        }
    }

    private double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    // Implementieren Sie hier Ihre Lösung für die Teilsummen
    public boolean hasSubsetSum(int sum, int[] numbers) {
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int num : numbers) {
            for (int i = sum; i >= num; i--) {
                if (dp[i - num]) dp[i] = true;
                dp[i] |= dp[i - num];
            }
        }
        return dp[sum];
    }

}
