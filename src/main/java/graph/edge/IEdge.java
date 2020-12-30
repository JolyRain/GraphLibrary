package graph.edge;

import graph.vertex.IVertex;

import java.util.Comparator;

public interface IEdge extends Comparable<IEdge> {

    boolean contains(IVertex vertex);

    boolean isDirected();

    double getWeight();

    IVertex getStart();

    IVertex getEnd();

    @Override
    default int compareTo(IEdge o) {
        return Double.compare(getWeight(), o.getWeight());
    }
}
