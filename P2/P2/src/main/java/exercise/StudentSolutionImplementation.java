package main.java.exercise;

import main.java.framework.StudentInformation;
import main.java.framework.StudentSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentSolutionImplementation implements StudentSolution {
    @Override
    public StudentInformation provideStudentInformation() {
        return new StudentInformation(
                "Christoph", // Vorname
                "Zeitler", // Nachname
                "12224224" // Matrikelnummer
        );
    }

    // Implementieren Sie hier Ihre Lösung für die Heapify-Up-Operation
    public void heapifyUp(PriorityQueue priorityQueue, int index) {
        //Skript Seite 87
        if (index > 1) {
            int j = index / 2;
            if (priorityQueue.getWeight(index) < priorityQueue.getWeight(j)) {
                priorityQueue.swap(index, j);
                heapifyUp(priorityQueue, j);
            }
        }
    }

    // Implementieren Sie hier Ihre Lösung für die Heapify-Down-Operation
    public void heapifyDown(PriorityQueue priorityQueue, int index) {
        //Skript Seite 90
        int n = priorityQueue.length() - 1;
        int j;
        if (2 * index > n) return;
        else if (2 * index < n) {
            int left = 2 * index;
            int right = 2 * index + 1;
            if (priorityQueue.getWeight(left) < priorityQueue.getWeight(right)) {
                j = left;
            } else {
                j = right;
            }
        } else {
            j = 2 * index;
        }

        if (priorityQueue.getWeight(index) > priorityQueue.getWeight(j)) {
            priorityQueue.swap(index, j);
            heapifyDown(priorityQueue, j);
        }
    }

    // Implementieren Sie hier Ihre Lösung für den Algorithmus von Prim
    public double prim(Graph g, PriorityQueue q, int[] predecessors) {
        //Skript Seite 34
        double A[] = new double[g.numberOfVertices() + 1]; //Kosten A[v]
        ArrayList<Integer> S = new ArrayList<>();

        for (int i = 1; i <= g.numberOfVertices(); i++) {
            if(g.isRelevant(i)){

                if(S.isEmpty()){
                    predecessors[i] = i;
                    S.add(i);
                    A[i] = 0;
                    q.add(0, i); //Queue auffüllen
                }

                else{
                    A[i] = Double.POSITIVE_INFINITY;
                    q.add(Double.POSITIVE_INFINITY, i); //Queue auffüllen
                }
            }

        }

        while (!q.isEmpty()) {
            int u = q.removeFirst();
            S.add(u);

            int neighbors[] = g.getNeighbors(u);

            for (int i = 0; i < neighbors.length; i++) {

                int current = neighbors[i]; // current neighbour
                double distance = g.getEdgeWeight(u, current);

                if (!S.contains(current) && distance < A[current]) {
                    A[current] = distance;
                    predecessors[current] = u; //Vorgänger
                    q.decreaseWeight(distance, current); //Priorität verringern
                }
            }
        }

        double costs = 0.0;
        for (int i = 1; i <= g.numberOfVertices(); i++) {
            if(A[i] < Double.POSITIVE_INFINITY) costs += A[i];
        }

        return costs;
    }
    // Implementieren Sie hier Ihre Lösung für die Find-Set-Operation
    public int findset(UnionFindDataStructure unionFindDataStructure, int vertexId) {

        int h = vertexId;
        while (unionFindDataStructure.getParent(h) != h){
            h = unionFindDataStructure.getParent(h);
        }

        return h;

    }

    // Implementieren Sie hier Ihre Lösung für den Algorithmus von Kruskal
    public double kruskal(Graph g, UnionFindDataStructure u, boolean[] chosenEdges) {
        int[][] edges = g.getEdgesOrderedByWeight();
        double out = 0;


        for (int i = 1; i < g.numberOfVertices() + 1; i++) {
            if (g.isRelevant(i)) u.makeset(i);
        }


        for (int i = 0; i < edges.length; i++) {
            if (g.isRelevant(edges[i][0]) && g.isRelevant(edges[i][1])){


                int v = edges[i][0];
                int w = edges[i][1];


                if (u.findset(v) != u.findset(w)){

                    u.union(u.findset(v), u.findset(w));
                    double costs = g.getEdgeWeight(v, w);
                    out += costs;
                    chosenEdges[i] = true;

                }


            }
        }

        return out;
    }

}
