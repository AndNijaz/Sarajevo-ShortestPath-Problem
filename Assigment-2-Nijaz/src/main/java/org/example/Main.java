package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Edge e1 = new Edge(1,4, 2);
        Edge e2 = new Edge(4,2, 15);
        Edge e3 = new Edge(2,5, 8);
        Edge e4 = new Edge(3,5, 6);
        Edge e5 = new Edge(3,1, 10);
        Edge e6 = new Edge(3,4, 5);

        ArrayList<Edge> edgeList = new ArrayList<>();
        edgeList.add(e1);
        edgeList.add(e2);
        edgeList.add(e3);
        edgeList.add(e4);
        edgeList.add(e5);
        edgeList.add(e6);

        Graph ga = new Graph(edgeList);

        System.out.println(ga);
        ga.printGraph();
        //System.out.println("aa");
    }
}