package org.example;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        loadPlaces();
        loadConstraints();

        //Graph graph1 = createGraph("C:\\Users\\andel\\Desktop\\assigment-2\\Assigment-2-Nijaz\\simple.txt");
        //Graph graph2 = createGraph("C:\\Users\\andel\\Desktop\\assigment-2\\Assigment-2-Nijaz\\five_places.txt");
        //Graph graph3 = createGraph("C:\\Users\\andel\\Desktop\\assigment-2\\Assigment-2-Nijaz\\all_places_a.txt");
        //Graph graph4 = createGraph("C:\\Users\\andel\\Desktop\\assigment-2\\Assigment-2-Nijaz\\all_places_b.txt");
        //Graph graph5 = createGraph("C:\\Users\\andel\\Desktop\\assigment-2\\Assigment-2-Nijaz\\complex.txt");
        //Graph graph6 = createGraph("C:\\Users\\andel\\Desktop\\assigment-2\\Assigment-2-Nijaz\\ten_places.txt");
        Graph graph6 = createGraph("C:\\Users\\andel\\Desktop\\assigment-2\\Assigment-2-Nijaz\\moj.txt");

/*--

        Graph graph = new Graph(graphEdges);

        graph.printGraph();

        graph.timeRequiredBetweenAllPlaces();

        graph.shortestPathBetweenTwoVertices("B", "A");

 */
        graph6.printGraph();
        //graph6.timeRequiredBetweenAllPlaces();
        //graph6.shortestPathBetweenTwoVertices("A", "C");
        ShortestPath.timeRequiredBetweenAllPlaces(graph6);
        ShortestPath.shortestPathBetweenTwoVertices(graph6,"D", "A");

    }

    public static Set<String> createPlacesSet(ArrayList<Edge> edgeList){
        Set<String> placesNameSet = new HashSet<>();
        for(Edge e : edgeList){
            placesNameSet.add(e.getStartVertex());
            placesNameSet.add(e.getEndVertex());
        }

        return placesNameSet;
    }

    private static ArrayList<Edge> createEdgeList(String pathName){
        ThreadSafeSingletonConstraits singletonConstraits = ThreadSafeSingletonConstraits.getInstance();

        ArrayList<Edge> edgeList = new ArrayList<>();

        File placesFile = new File(pathName);

        try {
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
        } catch(Exception e){
            e.printStackTrace();
        }

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
    private static ArrayList<Edge> loadGraphPlaces(String pathName){
        ArrayList<Edge> edgeList = createEdgeList(pathName);

        checkAndAddNewLocations(edgeList);

        return edgeList;
    }

    private static Graph createGraph(String pathName) {
        ArrayList<Edge> edgeList = loadGraphPlaces(pathName);
        return new Graph(edgeList);
    }

    private static void loadConstraints() {
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

            //for(Constrait c : singletonConstraits.getAllData()){
            //    System.out.println(c.getStartVertex() + " " + c.getEndVertex() + " " + c.getConstraintName() + " " + c.getProbability());
            //}
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void loadPlaces() {
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

            /*
            for (Map.Entry<String, Place> entry : singletonPlaces.getAllData().entrySet()) {
                String key = entry.getKey();
                Place value = entry.getValue();

                System.out.println("Key: " + key + ", Value: {stringValue: " + value.getPlaceName() + ", index: " + value.getPlaceID() + "}");
            }
             */
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}