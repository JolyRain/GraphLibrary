package graph;

import graph.edge.DirectedEdge;
import graph.edge.IEdge;
import graph.vertex.IVertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DirectedGraph implements IGraph {
    private Map<IVertex, Set<IVertex>> adjacencyMap;
    private Set<IVertex> vertices;
    private Set<IEdge> edges;

    public DirectedGraph() {
        adjacencyMap = new HashMap<>();
        vertices = new HashSet<>();
        edges = new HashSet<>();
    }

    @Override
    public boolean containsVertex(IVertex vertex) {
        return vertices.contains(vertex);
    }

    @Override
    public boolean containsEdge(IEdge edge) {
        return edges.contains(edge);
    }

    @Override
    public void addVertex(IVertex vertex) {
        if (!containsVertex(vertex)) {
            adjacencyMap.put(vertex, new HashSet<>());
            vertices.add(vertex);
        }
    }

    @Override
    public void addEdge(IEdge edge) {
        if (!edge.isDirected()) return;  //надо бы вкидывать эксепшн
        DirectedEdge directedEdge = (DirectedEdge) edge;
        edges.add(directedEdge);

        IVertex start = directedEdge.getStart();
        IVertex end = directedEdge.getEnd();
        addVertex(start);
        addVertex(end);
        addAdjacentVertices(start, end);
    }

    private void addAdjacentVertices(IVertex start, IVertex end) {
        Set<IVertex> startAdjacent = adjacencyMap.get(start);
        startAdjacent.add(end);
    }

    @Override
    public void removeEdge(IEdge edge) {
        if (!edge.isDirected()) return;  //надо бы вкидывать эксепшн
        DirectedEdge directedEdge = (DirectedEdge) edge;
        removeVertex(directedEdge.getStart());
        removeVertex(directedEdge.getEnd());
        edges.remove(edge);
    }

    @Override
    public IEdge getEdge(IVertex start, IVertex end) {
        if (start.equals(end)) return null;
        for (IEdge edge : edges) {
            DirectedEdge directedEdge = (DirectedEdge) edge;
            IVertex source = directedEdge.getStart();
            IVertex target = directedEdge.getEnd();
            if (source.equals(start) && target.equals(end)) {
                return edge;
            }
        }
        return null;
    }

    @Override
    public Map<IVertex, Set<IVertex>> getAdjacencyMap() {
        return adjacencyMap;
    }

    @Override
    public void removeVertex(IVertex vertex) {
        Set<IVertex> allAdjacent = adjacencyMap.get(vertex);
        allAdjacent.forEach(adjacentVertex -> adjacencyMap.get(adjacentVertex).remove(vertex));  //вроде законно
        vertices.remove(vertex);
        adjacencyMap.remove(vertex);
    }

    @Override
    public void clear() {
        adjacencyMap.clear();
        vertices.clear();
        edges.clear();
    }

    @Override
    public Set<IVertex> getVertices() {
        return vertices;

    }

    @Override
    public Set<IEdge> getEdges() {
        return edges;
    }

    @Override
    public int degreeOf(IVertex vertex) {
        return adjacencyMap.get(vertex).size();
    }
}
