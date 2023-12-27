package main.java.exercise;

import main.java.framework.PersistAs;
import main.java.framework.Result;

public class ResultImplementation implements Result {

    @PersistAs("duration")
    private long duration;

    @PersistAs("numberOfArticles")
    private int numberOfArticles;

    @PersistAs("numberOfBundles")
    private int numberOfBundles;

    @PersistAs("minNumberOfNecessaryBundles")
    private int minNumberOfNecessaryBundles;

    private boolean satisfiable;

    private boolean[] chosenBundles;

    public ResultImplementation(long duration, int numberOfArticles, int numberOfBundles, int minNumberOfNecessaryBundles, boolean satisfiable, boolean[] chosenBundles) {
        this.duration = duration;
        this.numberOfArticles = numberOfArticles;
        this.numberOfBundles = numberOfBundles;
        this.minNumberOfNecessaryBundles = minNumberOfNecessaryBundles;
        this.satisfiable = satisfiable;
        this.chosenBundles = chosenBundles;
    }

    public boolean isSatisfiable() {
        return satisfiable;
    }

    public boolean[] getChosenBundles() {
        return chosenBundles;
    }
}
