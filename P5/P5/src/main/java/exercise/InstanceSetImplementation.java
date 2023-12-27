package main.java.exercise;

import main.java.framework.InstanceSet;

import java.io.BufferedReader;
import java.nio.file.Path;


public class InstanceSetImplementation extends InstanceSet<InstanceImplementation, StudentSolutionImplementation, ResultImplementation, VerifierImplementation, Object> {

    public InstanceSetImplementation(Path instanceSetPath, Path outputPath) {
        super(instanceSetPath, outputPath, ResultImplementation.class);
    }

    @Override
    protected InstanceImplementation instanceFromCsv(String line) {
        String[] splitLine = line.split(",", 5);
        String[] articleIds = splitLine[3].split("\\|");
        int[] parsedArticleIds = new int[articleIds.length];
        for (int i = 0; i < articleIds.length; i++) {
            parsedArticleIds[i] = Integer.parseInt(articleIds[i]);
        }
        String[] articleBundles = splitLine[4].split(",", -1);
        int[][] parsedArticleBundles = new int[articleBundles.length][];
        for (int i = 0; i < articleBundles.length; i++) {
            if (articleBundles[i].length() > 0) {
                String[] articleBundle = articleBundles[i].split("\\|");
                parsedArticleBundles[i] = new int[articleBundle.length];
                for (int j = 0; j < articleBundle.length; j++) {
                    parsedArticleBundles[i][j] = Integer.parseInt(articleBundle[j]);
                }
            } else {
                parsedArticleBundles[i] = new int[0];
            }

        }
        return new InstanceImplementation(splitLine[1], Integer.parseInt(splitLine[0]), parsedArticleIds, parsedArticleBundles, Integer.parseInt(splitLine[2]));
    }

    @Override
    protected StudentSolutionImplementation provideStudentSolution() {
        return new StudentSolutionImplementation();
    }

    @Override
    protected VerifierImplementation provideVerifier() {
        return new VerifierImplementation();
    }

    @Override
    protected Object parseAdditionalInput(BufferedReader reader) {
        return null;
    }
}
