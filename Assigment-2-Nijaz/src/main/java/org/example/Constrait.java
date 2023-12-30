package org.example;

public class Constrait {

    private final String startVertex;
    private final String endVertex;
    private final Double probability;
    private final String constraintName;

    Constrait(String startVertex, String endVertex, Double probability, String constraintName){

        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.probability = probability;
        this.constraintName = constraintName;
    }

    public String getStartVertex() {
        return startVertex;
    }

    public String getEndVertex() {
        return endVertex;
    }

    public Double getProbability() {
        return probability;
    }

    public String getConstraintName() {
        return constraintName;
    }
}
