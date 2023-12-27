package main.java.exercise;

import main.java.framework.StudentInformation;
import main.java.framework.StudentSolution;
import main.java.framework.solver.Solver;

public class StudentSolutionImplementation implements StudentSolution {
    @Override
    public StudentInformation provideStudentInformation() {
        return new StudentInformation(
                "Christoph", // Vorname
                "Zeitler", // Nachname
                "12224224" // Matrikelnummer
        );
    }

    // Implementieren Sie hier Ihre Lösung mit Reduktion
    public boolean findBundles(int[] articleIds, int[][] articleBundles, Solver solver, boolean[] chosenBundles) {

        return false;
    }

}
