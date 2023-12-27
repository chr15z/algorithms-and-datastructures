package main.java.exercise;

import main.java.framework.Report;
import main.java.framework.Timer;
import main.java.framework.Verifier;
import main.java.framework.solver.Solver;

import java.util.ArrayList;
import java.util.List;

public class VerifierImplementation extends Verifier<InstanceImplementation, StudentSolutionImplementation, ResultImplementation> {

    @Override
    public ResultImplementation solveProblemUsingStudentSolution(InstanceImplementation instance, StudentSolutionImplementation studentSolution) {
        boolean[] chosenBundles = new boolean[instance.getArticleBundles().length];
        Timer timer = new Timer();
        timer.start();
        boolean satisfiable = studentSolution.findBundles(instance.getArticleIds(), instance.getArticleBundles(), new Solver(), chosenBundles);
        timer.stop();
        return new ResultImplementation(timer.getDuration(), instance.getArticleIds().length, instance.getArticleBundles().length, instance.getMinNumberOfNecessaryBundles(), satisfiable, chosenBundles);
    }

    @Override
    public Report verifyResult(InstanceImplementation instance, ResultImplementation result) {
        if (instance.getMinNumberOfNecessaryBundles() > -1 && !result.isSatisfiable()) {
            return new Report(false, "Error in instance " + instance.getNumber() + ": This instance is expected to be satisfiable.");
        }

        if (instance.getMinNumberOfNecessaryBundles() == -1 && result.isSatisfiable()) {
            return new Report(false, "Error in instance " + instance.getNumber() + ": This instance is not expected to be satisfiable.");
        }

        if (instance.getMinNumberOfNecessaryBundles() == -1 && !result.isSatisfiable()) {
            return new Report(true, "");
        }

        List<Integer> tempArticleIds = new ArrayList<Integer>();
        int[] articleIds = instance.getArticleIds();
        int[][] articleBundles = instance.getArticleBundles();
        boolean[] chosenBundles = result.getChosenBundles();
        for (int i = 0; i < articleIds.length; i++) {
            tempArticleIds.add(articleIds[i]);
        }

        int numberOfChosenBundles = 0;
        for (int i = 0; i < chosenBundles.length; i++) {
            if (chosenBundles[i]) {
                numberOfChosenBundles++;
                for (int j = 0; j < articleBundles[i].length; j++) {
                    tempArticleIds.remove(Integer.valueOf(articleBundles[i][j]));
                }
            }
        }

        if (numberOfChosenBundles > instance.getMinNumberOfNecessaryBundles()) {
            return new Report(false, "Error in instance " + instance.getNumber() + ": This instance can be satisfied with just " + instance.getMinNumberOfNecessaryBundles() + " bundles. However, " + numberOfChosenBundles + " have been chosen.");
        }
        if (tempArticleIds.size() > 0) {
            return new Report(false, "Error in instance " + instance.getNumber() + ": The chosen bundles do not cover all required articles.");
        }
        return new Report(true, "");
    }
}
