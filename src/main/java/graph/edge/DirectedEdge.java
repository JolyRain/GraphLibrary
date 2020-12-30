package graph.edge;

import graph.vertex.IVertex;
import graph.vertex.Vertex;

public class DirectedEdge implements IEdge {
    private Vertex start;
    private Vertex end;
    private double weight = 0;

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public DirectedEdge() {
    }

    public DirectedEdge(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Vertex getStart() {
        return start;
    }

    public void setStart(Vertex start) {
        this.start = start;
    }

    @Override
    public Vertex getEnd() {
        return end;
    }

    public void setEnd(Vertex end) {
        this.end = end;
    }

    @Override
    public boolean contains(IVertex vertex) {
        return vertex.equals(start) || vertex.equals(end);
    }

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return start + "-->" + end;
    }
}
