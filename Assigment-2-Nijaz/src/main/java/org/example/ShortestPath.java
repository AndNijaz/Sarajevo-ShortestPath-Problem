package org.example;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class ShortestPath {
    public static void timeRequiredBetweenAllPlaces(Graph graph){
        Set<String> placesNameSet = graph.getPlacesNameSet();

        for(String place : placesNameSet){

            int[] arr = ShortestPath._shortestDistanceToAllVertices(graph, place);
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
    public static int shortestPathBetweenTwoVertices(Graph graph, String placeOne, String placeTwo){
        ThreadSafeSingletonPlaces singletonPlaces = ThreadSafeSingletonPlaces.getInstance();
        Map<String, Place> placesMap = singletonPlaces.getAllData();

        //System.out.println(placeOne + " " + placeTwo);
        if(!placesMap.containsKey(placeOne)) return -1;
        int start = placesMap.get(placeOne).getPlaceID();
        if(!placesMap.containsKey(placeTwo)) return -1;
        int end = placesMap.get(placeTwo).getPlaceID();
        //System.out.println(_shortestDistanceToAllVertices(graph,placeOne)[end]);
        if(end <= graph.size && start <= graph.size && _shortestDistanceToAllVertices(graph,placeOne)[end] != Integer.MAX_VALUE) return _shortestDistanceToAllVertices(graph,placeOne)[end];
        return -1;
    }

    private static int[] _shortestDistanceToAllVertices(Graph graph, String place) {
        ThreadSafeSingletonPlaces singletonPlaces = ThreadSafeSingletonPlaces.getInstance();
        Map<String, Place> placesMap = singletonPlaces.getAllData();
        int[][] matrix = graph.getMatrix();

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

        //System.out.println("distance" + distances);
        //distances = Arrays.stream(distances).map(distance -> distance == Integer.MAX_VALUE ? -1 : distance);
        //System.out.println(t);
        //for(int i=0; i<distances.length; i++){
        //    if(distances[i] == Integer.MAX_VALUE) distances[i] = -1;
        //}
        //System.out.println(distances);
        return distances;
    }
}
