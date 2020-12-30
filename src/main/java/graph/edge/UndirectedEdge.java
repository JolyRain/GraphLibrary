package graph.edge;

import graph.vertex.IVertex;
import graph.vertex.Vertex;

import java.util.Objects;

public class UndirectedEdge implements IEdge {
    private Vertex firstVertex;
    private Vertex secondVertex;
    private double weight = 0;

    public UndirectedEdge() {
    }

    public UndirectedEdge(Vertex firstVertex, Vertex secondVertex) {
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public IVertex getStart() {
        return getFirstVertex();
    }

    @Override
    public IVertex getEnd() {
        return getSecondVertex();
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Vertex getFirstVertex() {
        return firstVertex;
    }

    public void setFirstVertex(Vertex firstVertex) {
        this.firstVertex = firstVertex;
    }

    public Vertex getSecondVertex() {
        return secondVertex;
    }

    public void setSecondVertex(Vertex secondVertex) {
        this.secondVertex = secondVertex;
    }

    @Override
    public boolean contains(IVertex vertex) {
        return firstVertex.equals(vertex) || secondVertex.equals(vertex);
    }

    @Override
    public boolean isDirected() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UndirectedEdge that = (UndirectedEdge) o;
        return (Objects.equals(firstVertex, that.firstVertex) && Objects.equals(secondVertex, that.secondVertex))
                || (Objects.equals(secondVertex, that.firstVertex) && Objects.equals(firstVertex, that.secondVertex));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstVertex, secondVertex);
    }

    @Override
    public String toString() {
        return firstVertex + "---" + secondVertex;
    }
}
