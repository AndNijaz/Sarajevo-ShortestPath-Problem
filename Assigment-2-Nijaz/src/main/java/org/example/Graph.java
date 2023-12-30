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
        //Gets edges
        this.edgeList = edgeList;

        //Looking for a size
        placesNameSet = Main.createPlacesSet(edgeList);

        //
        this.size =  placesNameSet.size();

        //Creates possible places
        //createGraphPlaces();

        //Creating matrix
        this.matrix = new int[size][size];

        Random random = new Random();
        Double constraintProbability;

        //double constraintProbability = random.nextDouble();
        //double constraintProbability = 0.2;

        //Filling the matrix
        for(Edge edge : edgeList){
            constraintProbability = random.nextDouble();
            //System.out.println("rand " + constraintProbability);
            //constraintProbability = 0.0;
            //System.out.println(edge.getConstrait() <= constraintProbability);

            int index1 = placesMap.get(edge.getStartVertex()).getPlaceID();
            int index2 = placesMap.get(edge.getEndVertex()).getPlaceID();


           // if(((double) constraintProbability <= (double) edge.getConstrait()) && (double) edge.getConstrait() != 0.0){
            if(( constraintProbability <= edge.getConstrait()) && edge.getConstrait() != 0.0){
                this.matrix[index1][index2] = Integer.MAX_VALUE;
            } else {
                if(edge.getWeight() < 0) this.matrix[index1][index2] = -1;
                else this.matrix[index1][index2] = edge.getWeight();
            }
        }
//0.5 > 0.2;

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
