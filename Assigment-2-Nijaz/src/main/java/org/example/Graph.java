package org.example;

import java.io.File;
import java.util.*;

public class Graph {
    ArrayList<Edge> edgeList;
    int[][] matrix;
    Integer size;
    Map<String, Place> placesMap;
    Set<String> placesNameSet;

    public Graph(ArrayList<Edge> edgeList) {
        ThreadSafeSingletonPlaces singletonPlaces = ThreadSafeSingletonPlaces.getInstance();

        placesMap = singletonPlaces.getAllData();

        this.edgeList = edgeList;

        placesNameSet = Main.createPlacesSet(edgeList);

        this.size =  placesNameSet.size();


        //Creating matrix
        this.matrix = new int[size][size];

        Random random = new Random();
        Double constraintProbability;

        //Filling the matrix
        for(Edge edge : edgeList){
            constraintProbability = random.nextDouble();

            int index1 = placesMap.get(edge.getStartVertex()).getPlaceID();
            int index2 = placesMap.get(edge.getEndVertex()).getPlaceID();

            if(( constraintProbability <= edge.getConstrait()) && edge.getConstrait() != 0.0){
                this.matrix[index1][index2] = Integer.MAX_VALUE;
            } else {
                if(edge.getWeight() < 0) this.matrix[index1][index2] = -1;
                else this.matrix[index1][index2] = edge.getWeight();
            }
        }
    }
    void createPlacesSet(){
        placesNameSet = new HashSet<>();
        for(Edge e : edgeList){
            placesNameSet.add(e.getStartVertex());
            placesNameSet.add(e.getEndVertex());
        }
    }

    void printGraph(){
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println(); // Move to the next line after printing each row
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int[] row : matrix) {
            for (int element : row) {
                s += "" + element + " ";
            }
            s += "\n"; // Move to the next line after printing each row
        }

        return s;
    }

    public Set<String> getPlacesNameSet() {
        return placesNameSet;
    }

    public int[][] getMatrix() {
        return matrix;
    }
}
