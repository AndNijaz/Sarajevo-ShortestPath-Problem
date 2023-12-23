package org.example;

public class Edge {
    private final Integer startVertex;
    private final Integer endVertex;
    private final Integer weight;

    public Edge(Integer startVertex, Integer endVertex, Integer weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    public Integer getStartVertex() {
        return startVertex;
    }

    public Integer getEndVertex() {
        return endVertex;
    }

    public Integer getWeight() {
        return weight;
    }
}
