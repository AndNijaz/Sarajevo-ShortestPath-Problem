package org.example;

import java.util.ArrayList;

public class Graph {
    ArrayList<Edge> edgeList;
    int[][] matrix;
    Integer size;
    public Graph(ArrayList<Edge> edgeList) {
        size = edgeList.size()-1;
        System.out.println(size);
        this.edgeList = edgeList;
        this.matrix = new int[size+1][size+1];

        for(Edge edge : edgeList){
            matrix[edge.getStartVertex()][edge.getEndVertex()] = edge.getWeight();
        }
    }

    void printGraph(){
        for(int i=1; i<=size; i++){
            for(int j=1; j<=size; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        String s = "";
        for(int i=1; i<=size; i++){
            for(int j=1; j<=size; j++){
                s += matrix[i][j] + " ";
            }
            s += "\n";
        }

        return s;
    }
}
