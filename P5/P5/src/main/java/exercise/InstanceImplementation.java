package main.java.exercise;

import main.java.framework.Instance;

public class InstanceImplementation implements Instance {

    private String groupName;

    private int number;

    private int[] articleIds;

    private int[][] articleBundles;

    private int minNumberOfNecessaryBundles;

    public InstanceImplementation(String groupName, int number, int[] articleIds, int[][] articleBundles, int minNumberOfNecessaryBundles) {
        this.groupName = groupName;
        this.number = number;
        this.articleIds = articleIds;
        this.articleBundles = articleBundles;
        this.minNumberOfNecessaryBundles = minNumberOfNecessaryBundles;
    }

    @Override
    public String getGroupName() {
        return this.groupName;
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    public int[] getArticleIds() {
        return articleIds;
    }

    public int[][] getArticleBundles() {
        return articleBundles;
    }

    public int getMinNumberOfNecessaryBundles() {
        return minNumberOfNecessaryBundles;
    }
}
