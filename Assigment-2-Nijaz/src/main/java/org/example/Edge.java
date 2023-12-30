package org.example;

public class Edge {
    private final String startVertex;
    private final String endVertex;
    private final Integer weight;
    private Double constrait;
    private String constraitName;

    public Edge(String startVertex, String endVertex, Integer weight, Double constrait, String constraitName) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
        this.constrait = constrait;
        this.constraitName = constraitName;
    }

    public String getStartVertex() {
        return startVertex;
    }

    public String getEndVertex() {
        return endVertex;
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "startVertex='" + startVertex + '\'' +
                ", endVertex='" + endVertex + '\'' +
                ", weight=" + weight +
                ", constrait=" + constrait +
                ", constraitName='" + constraitName + '\'' +
                '}';
    }

    public void setConstrait(Double constrait) {
        this.constrait = constrait;
    }

    public Double getConstrait() {
        return constrait;
    }

    public void setConstraitName(String constraitName) {
        this.constraitName = constraitName;
    }

    public String getConstraitName() {
        return constraitName;
    }
}
