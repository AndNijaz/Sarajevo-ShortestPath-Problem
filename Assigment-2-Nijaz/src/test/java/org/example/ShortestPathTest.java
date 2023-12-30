package org.example;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//import static org.junit.jupiter.api.Assertions.*;

public class ShortestPathTest extends TestCase {

    public void testSourceToDestinationPathExitance(){
        loadPlaces();
        loadConstraints();

        Map<String, Place> placesMap = new HashMap<>();
        placesMap.put("A", new Place("Alipašino", 0));
        placesMap.put("B", new Place("Baščaršija", 1));
        placesMap.put("C", new Place("Ciglane", 2));
        placesMap.put("D", new Place("Doglodi", 3));

        ArrayList<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge("A", "B", 7, 0.0, ""));
        edgeList.add(new Edge("A", "C", 15, 0.0, ""));
        edgeList.add(new Edge("B", "C", 5, 0.0, ""));
        edgeList.add(new Edge("B", "D", 2, 0.0, ""));
        edgeList.add(new Edge("D", "C", 1, 0.0, ""));
        edgeList.add(new Edge("C", "D", 1, 0.0, ""));

        Graph g = new Graph(edgeList);

        assertEquals(7, ShortestPath.shortestPathBetweenTwoVertices(g,"A", "B"));
    }

    public void testShortestPathBetweenNodesWithoutConstraits(){
        loadPlaces();
        loadConstraints();

        Map<String, Place> placesMap = new HashMap<>();
        placesMap.put("A", new Place("Alipašino", 0));
        placesMap.put("B", new Place("Baščaršija", 1));
        placesMap.put("C", new Place("Ciglane", 2));
        placesMap.put("D", new Place("Doglodi", 3));

        ArrayList<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge("A", "B", 7, 0.0, ""));
        edgeList.add(new Edge("A", "C", 15, 0.0, ""));
        edgeList.add(new Edge("B", "C", 5, 0.0, ""));
        edgeList.add(new Edge("B", "D", 2, 0.0, ""));
        edgeList.add(new Edge("D", "C", 1, 0.0, ""));
        edgeList.add(new Edge("C", "D", 1, 0.0, ""));

        Graph g = new Graph(edgeList);

        assertEquals(7, ShortestPath.shortestPathBetweenTwoVertices(g,"A", "B"));
        assertEquals(10, ShortestPath.shortestPathBetweenTwoVertices(g,"A", "C"));
        assertEquals(9, ShortestPath.shortestPathBetweenTwoVertices(g,"A", "D"));

        //assertEquals(Integer.MAX_VALUE, ShortestPath.shortestPathBetweenTwoVertices(g,"B", "A"));
        assertEquals(-1, ShortestPath.shortestPathBetweenTwoVertices(g,"B", "A"));
        assertEquals(3, ShortestPath.shortestPathBetweenTwoVertices(g,"B", "C"));
        assertEquals(2, ShortestPath.shortestPathBetweenTwoVertices(g,"B", "D"));

        //assertEquals(Integer.MAX_VALUE, ShortestPath.shortestPathBetweenTwoVertices(g,"C", "A"));
        assertEquals(-1, ShortestPath.shortestPathBetweenTwoVertices(g,"C", "A"));
        //assertEquals(Integer.MAX_VALUE, ShortestPath.shortestPathBetweenTwoVertices(g,"C", "B"));
        assertEquals(-1, ShortestPath.shortestPathBetweenTwoVertices(g,"C", "B"));
        assertEquals(1, ShortestPath.shortestPathBetweenTwoVertices(g,"C", "D"));

        //assertEquals(Integer.MAX_VALUE, ShortestPath.shortestPathBetweenTwoVertices(g,"D", "A"));
        assertEquals(-1, ShortestPath.shortestPathBetweenTwoVertices(g,"D", "A"));
        //assertEquals(Integer.MAX_VALUE, ShortestPath.shortestPathBetweenTwoVertices(g,"D", "B"));
        assertEquals(-1, ShortestPath.shortestPathBetweenTwoVertices(g,"D", "B"));
        assertEquals(1, ShortestPath.shortestPathBetweenTwoVertices(g,"D", "C"));
    }

    public void testShortestPathBetweenNodessWithAllConstraits(){
        loadPlaces();
        loadConstraints();

        Map<String, Place> placesMap = new HashMap<>();
        placesMap.put("A", new Place("Alipašino", 0));
        placesMap.put("B", new Place("Baščaršija", 1));
        placesMap.put("C", new Place("Ciglane", 2));
        placesMap.put("D", new Place("Doglodi", 3));

        ArrayList<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge("A", "B", 7, 1.1, "High Constructions"));
        edgeList.add(new Edge("A", "C", 15, 0.0, ""));
        edgeList.add(new Edge("B", "C", 5, 0.0, ""));
        edgeList.add(new Edge("B", "D", 2, 1.1, "Foggy"));
        edgeList.add(new Edge("D", "C", 1, 0.0, ""));
        edgeList.add(new Edge("C", "D", 1, 0.0, ""));

        Graph g = new Graph(edgeList);

        //assertEquals(Integer.MAX_VALUE, ShortestPath.shortestPathBetweenTwoVertices(g,"A", "B"));
        assertEquals(-1, ShortestPath.shortestPathBetweenTwoVertices(g,"A", "B"));
        assertEquals(15, ShortestPath.shortestPathBetweenTwoVertices(g,"A", "C"));
        assertEquals(16, ShortestPath.shortestPathBetweenTwoVertices(g,"A", "D"));

        //assertEquals(Integer.MAX_VALUE, ShortestPath.shortestPathBetweenTwoVertices(g,"B", "A"));
        assertEquals(-1, ShortestPath.shortestPathBetweenTwoVertices(g,"B", "A"));
        assertEquals(5, ShortestPath.shortestPathBetweenTwoVertices(g,"B", "C"));
        assertEquals(6, ShortestPath.shortestPathBetweenTwoVertices(g,"B", "D"));

        //assertEquals(Integer.MAX_VALUE, ShortestPath.shortestPathBetweenTwoVertices(g,"C", "A"));
        assertEquals(-1, ShortestPath.shortestPathBetweenTwoVertices(g,"C", "A"));
        //assertEquals(Integer.MAX_VALUE, ShortestPath.shortestPathBetweenTwoVertices(g,"C", "B"));
        assertEquals(-1, ShortestPath.shortestPathBetweenTwoVertices(g,"C", "B"));
        assertEquals(1, ShortestPath.shortestPathBetweenTwoVertices(g,"C", "D"));

        //assertEquals(Integer.MAX_VALUE, ShortestPath.shortestPathBetweenTwoVertices(g,"D", "A"));
        assertEquals(-1, ShortestPath.shortestPathBetweenTwoVertices(g,"D", "A"));
        //assertEquals(Integer.MAX_VALUE, ShortestPath.shortestPathBetweenTwoVertices(g,"D", "B"));
        assertEquals(-1, ShortestPath.shortestPathBetweenTwoVertices(g,"D", "B"));
        assertEquals(1, ShortestPath.shortestPathBetweenTwoVertices(g,"D", "C"));
    }

    public void testInvalidSourceVertex() {
        loadPlaces();
        loadConstraints();

        Map<String, Place> placesMap = new HashMap<>();
        placesMap.put("A", new Place("Alipašino", 0));
        placesMap.put("B", new Place("Baščaršija", 1));
        placesMap.put("C", new Place("Ciglane", 2));
        placesMap.put("D", new Place("Doglodi", 3));

        ArrayList<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge("A", "B", 7, 0.0, "High Constructions"));
        edgeList.add(new Edge("A", "C", 15, 0.0, ""));
        edgeList.add(new Edge("B", "C", 5, 0.0, ""));
        edgeList.add(new Edge("B", "D", 2, 0.0, "Foggy"));
        edgeList.add(new Edge("D", "C", 1, 0.0, ""));
        edgeList.add(new Edge("C", "D", 1, 0.0, ""));

        Graph g = new Graph(edgeList);

        assertEquals(-1, ShortestPath.shortestPathBetweenTwoVertices(g,"Z", "A"));
        assertEquals(-1, ShortestPath.shortestPathBetweenTwoVertices(g,"F", "A"));
    }

    public void testInvalidDestinationVertex() {
        loadPlaces();
        loadConstraints();

        Map<String, Place> placesMap = new HashMap<>();
        placesMap.put("A", new Place("Alipašino", 0));
        placesMap.put("B", new Place("Baščaršija", 1));
        placesMap.put("C", new Place("Ciglane", 2));
        placesMap.put("D", new Place("Doglodi", 3));

        ArrayList<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge("A", "B", 7, 0.0, "High Constructions"));
        edgeList.add(new Edge("A", "C", 15, 0.0, ""));
        edgeList.add(new Edge("B", "C", 5, 0.0, ""));
        edgeList.add(new Edge("B", "D", 2, 0.0, "Foggy"));
        edgeList.add(new Edge("D", "C", 1, 0.0, ""));
        edgeList.add(new Edge("C", "D", 1, 0.0, ""));

        Graph g = new Graph(edgeList);

        assertEquals(-1, ShortestPath.shortestPathBetweenTwoVertices(g,"A", "Z"));
        assertEquals(-1, ShortestPath.shortestPathBetweenTwoVertices(g,"A", "G"));
    }


    public void testNegativeTime() {
        loadPlaces();
        loadConstraints();

        Map<String, Place> placesMap = new HashMap<>();
        placesMap.put("A", new Place("Alipašino", 0));
        placesMap.put("B", new Place("Baščaršija", 1));
        placesMap.put("C", new Place("Ciglane", 2));
        placesMap.put("D", new Place("Doglodi", 3));

        ArrayList<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge("A", "B", -7, 0.0, "High Constructions"));
        edgeList.add(new Edge("A", "C", 15, 0.0, ""));
        edgeList.add(new Edge("B", "C", 5, 0.0, ""));
        edgeList.add(new Edge("B", "D", 2, 0.0, "Foggy"));
        edgeList.add(new Edge("D", "C", 1, 0.0, ""));
        edgeList.add(new Edge("C", "D", 1, 0.0, ""));

        Graph g = new Graph(edgeList);

        //System.out.println(ShortestPath.shortestPathBetweenTwoVertices(g, "A", "C"));
        assertEquals(-1, ShortestPath.shortestPathBetweenTwoVertices(g, "A", "B"));
        assertEquals(15, ShortestPath.shortestPathBetweenTwoVertices(g, "A", "C"));
    }

    public void testGraphWithSelfLoop(){
        loadPlaces();
        loadConstraints();

        Map<String, Place> placesMap = new HashMap<>();
        placesMap.put("A", new Place("Alipašino", 0));
        placesMap.put("B", new Place("Baščaršija", 1));
        placesMap.put("C", new Place("Ciglane", 2));
        placesMap.put("D", new Place("Doglodi", 3));

        ArrayList<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge("A", "B", -7, 0.0, "High Constructions"));
        edgeList.add(new Edge("A", "C", 15, 0.0, ""));
        edgeList.add(new Edge("B", "C", 5, 0.0, ""));
        edgeList.add(new Edge("B", "D", 2, 0.0, "Foggy"));
        edgeList.add(new Edge("D", "C", 1, 0.0, ""));
        edgeList.add(new Edge("C", "D", 1, 0.0, ""));

        Graph g = new Graph(edgeList);

        assertEquals(0, ShortestPath.shortestPathBetweenTwoVertices(g, "A", "A"));
        assertEquals(0, ShortestPath.shortestPathBetweenTwoVertices(g, "D", "D"));
    }

    public void loadPlaces(){ Main.loadPlaces(); };
    public void loadConstraints(){ Main.loadConstraints(); };

}