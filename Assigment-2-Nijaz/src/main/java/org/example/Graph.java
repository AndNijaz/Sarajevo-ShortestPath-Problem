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
        //double constraintProbability = random.nextDouble();
        //double constraintProbability = 0.2;

        //Filling the matrix
        for(Edge edge : edgeList){
            Double constraintProbability = 0.5;
            System.out.println(edge.getConstrait() <= constraintProbability);

            int index1 = placesMap.get(edge.getStartVertex()).getPlaceID();
            int index2 = placesMap.get(edge.getEndVertex()).getPlaceID();


           // if(((double) constraintProbability <= (double) edge.getConstrait()) && (double) edge.getConstrait() != 0.0){
            if(( constraintProbability <= edge.getConstrait()) && edge.getConstrait() != 0.0){
                this.matrix[index1][index2] = -1;
            } else {
                this.matrix[index1][index2] = edge.getWeight();
            }
        }
//0.5 > 0.2;

    }
    public void timeRequiredBetweenAllPlaces(){
        for(String place : placesNameSet){

            int[] arr = shortestDistanceToAllVertices(place);
            int counter = 0;
            for(String entry : placesNameSet){
                //System.out.println(entry);
                if(arr[counter] != 0) {
                    System.out.println(place + " -> " + entry + " " + arr[counter]);
                }
                counter++;
            }
            System.out.println("----");
        }

        //System.out.println(Arrays.toString(arr1));


    }
    
    public void shortestPathBetweenTwoVertices(String placeOne, String placeTwo){
        int start = placesMap.get(placeOne).getPlaceID();
        int end = placesMap.get(placeTwo).getPlaceID();

        System.out.println(shortestDistanceToAllVertices(placeOne)[end]);
    }
    public int[] shortestDistanceToAllVertices(String place) {
        int start = placesMap.get(place).getPlaceID();
        int numVertices = matrix.length;

        // Initialize distances array with infinity for all vertices
        int[] distances = new int[numVertices];
        Arrays.fill(distances, Integer.MAX_VALUE);

        // Set the distance of the start vertex to 0
        distances[start] = 0;

        // Priority queue to store vertices and their distances
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        priorityQueue.add(new int[]{start, 0});

        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int currentVertex = current[0];
            int currentDistance = current[1];

            for (int neighbor = 0; neighbor < numVertices; neighbor++) {
                if (matrix[currentVertex][neighbor] > 0) {
                    int distanceToNeighbor = currentDistance + matrix[currentVertex][neighbor];

                    // Update the distance if a shorter path is found
                    if (distanceToNeighbor < distances[neighbor]) {
                        distances[neighbor] = distanceToNeighbor;
                        priorityQueue.add(new int[]{neighbor, distanceToNeighbor});
                    }
                }
            }
        }

        return distances;
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
