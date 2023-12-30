package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            loadPlaces();
            loadConstraints();

            Graph graph1 = createGraph("C:\\Users\\andel\\Desktop\\assigment-2\\Assigment-2-Nijaz\\simple.txt");
            Graph graph2 = createGraph("C:\\Users\\andel\\Desktop\\assigment-2\\Assigment-2-Nijaz\\five_places.txt");
            Graph graph3 = createGraph("C:\\Users\\andel\\Desktop\\assigment-2\\Assigment-2-Nijaz\\all_places_a.txt");
            Graph graph4 = createGraph("C:\\Users\\andel\\Desktop\\assigment-2\\Assigment-2-Nijaz\\all_places_b.txt");
            Graph graph5 = createGraph("C:\\Users\\andel\\Desktop\\assigment-2\\Assigment-2-Nijaz\\complex.txt");
            Graph graph6 = createGraph("C:\\Users\\andel\\Desktop\\assigment-2\\Assigment-2-Nijaz\\ten_places.txt");
            //       Graph graph6 = createGraph("C:\\Users\\andel\\Desktop\\assigment-2\\Assigment-2-Nijaz\\moj.txt");

            ShortestPath.timeRequiredBetweenAllPlaces(graph1, "report_simple");
            ShortestPath.timeRequiredBetweenAllPlaces(graph2, "report_five_places");
            ShortestPath.timeRequiredBetweenAllPlaces(graph3, "report_all_places_a");
            ShortestPath.timeRequiredBetweenAllPlaces(graph4, "report_all_places_b");
            ShortestPath.timeRequiredBetweenAllPlaces(graph5, "report_complex");
            ShortestPath.timeRequiredBetweenAllPlaces(graph6, "report_ten_places");
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public static Set<String> createPlacesSet(ArrayList<Edge> edgeList){
        Set<String> placesNameSet = new HashSet<>();
        for(Edge e : edgeList){
            placesNameSet.add(e.getStartVertex());
            placesNameSet.add(e.getEndVertex());
        }

        return placesNameSet;
    }

    private static ArrayList<Edge> createEdgeList(String pathName) throws FileNotFoundException {
        ThreadSafeSingletonConstraits singletonConstraits = ThreadSafeSingletonConstraits.getInstance();

        ArrayList<Edge> edgeList = new ArrayList<>();

        File placesFile = new File(pathName);


            Scanner placesScanner = new Scanner(placesFile);

            while(placesScanner.hasNextLine()){
                String line = placesScanner.nextLine();
                String[] lineParts =  line.split("\\s");
                String startVertex = lineParts[0];
                String endVertex = lineParts[1];
                String constraitName = "";
                Double constraitProbability = 0.0;

                for(Constrait c : singletonConstraits.getAllData()) {
                    if(startVertex.equals(c.getStartVertex()) && endVertex.equals(c.getEndVertex())) {
                        constraitProbability = c.getProbability();
                        constraitName = c.getConstraintName();
                        break;
                    }
                }

                edgeList.add(new Edge(startVertex, endVertex, Integer.parseInt(lineParts[2]), constraitProbability, constraitName));
            }
            placesScanner.close();


        return edgeList;
    }

    private static void checkAndAddNewLocations(ArrayList<Edge> edgeList){
        ThreadSafeSingletonPlaces singletonPlaces = ThreadSafeSingletonPlaces.getInstance();

        Set<String> placesNameSet = createPlacesSet(edgeList);

        for (String value : placesNameSet) {
            // Check if the key is not in the set
            if (!singletonPlaces.getAllData().containsKey(value)) {
                // Add the key to the set
                singletonPlaces.addPlace(value, new Place("unknown", singletonPlaces.getCounter()));
                singletonPlaces.updateCounter();
            }
        }
    }
    private static ArrayList<Edge> loadGraphPlaces(String pathName) throws FileNotFoundException {
        ArrayList<Edge> edgeList = createEdgeList(pathName);

        checkAndAddNewLocations(edgeList);

        return edgeList;
    }

    private static Graph createGraph(String pathName) throws FileNotFoundException {
        ArrayList<Edge> edgeList = loadGraphPlaces(pathName);
        return new Graph(edgeList);
    }

    protected static void loadConstraints() {
        ThreadSafeSingletonConstraits singletonConstraits = ThreadSafeSingletonConstraits.getInstance();

        File constraitsFile = new File("C:\\Users\\andel\\Desktop\\assigment-2\\Assigment-2-Nijaz\\constraints.txt");

        try {
            Scanner s = new Scanner(constraitsFile);
            if(s.hasNextLine()) s.nextLine();

            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] lineParts =  line.split(",");

                singletonConstraits.addConstrait(new Constrait(lineParts[0], lineParts[1], Double.parseDouble(lineParts[3]), lineParts[2]));
            }
            s.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    protected static void loadPlaces() {
        ThreadSafeSingletonPlaces singletonPlaces = ThreadSafeSingletonPlaces.getInstance();

        File placesFile = new File("C:\\Users\\andel\\Desktop\\assigment-2\\Assigment-2-Nijaz\\places.txt");

        try {
            Scanner s = new Scanner(placesFile);
            if(s.hasNextLine()) s.nextLine();

            int counter = 0;
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] lineParts =  line.split(",");
                singletonPlaces.addPlace(lineParts[0], new Place(lineParts[1], counter));
                counter++;
            }
            singletonPlaces.setCounter(counter);

            s.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}