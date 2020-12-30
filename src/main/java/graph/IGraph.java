package graph;

import graph.edge.IEdge;
import graph.vertex.IVertex;

import java.util.Map;
import java.util.Set;

public interface IGraph {

    boolean containsVertex(IVertex vertex);

    boolean containsEdge(IEdge edge);

    void addVertex(IVertex vertex);

    void addEdge(IEdge edge);

    void removeEdge(IEdge edge);

    IEdge getEdge(IVertex start, IVertex end);

    Map<IVertex, Set<IVertex>> getAdjacencyMap();

    void removeVertex(IVertex vertex);

    void clear();

    Set<IVertex> getVertices();

    Set<IEdge> getEdges();

    int degreeOf(IVertex vertex);


}
