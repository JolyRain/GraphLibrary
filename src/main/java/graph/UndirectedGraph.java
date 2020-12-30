package graph;

import graph.edge.IEdge;
import graph.edge.UndirectedEdge;
import graph.vertex.IVertex;
import graph.vertex.Vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UndirectedGraph implements IGraph {
    private Map<IVertex, Set<IVertex>> adjacencyMap;
    private Set<IVertex> vertices;
    private Set<IEdge> edges;

    public UndirectedGraph() {
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
        if (edge.isDirected()) return;  //надо бы вкидывать эксепшн
        UndirectedEdge undirectedEdge = (UndirectedEdge) edge;
        edges.add(undirectedEdge);

        Vertex first = undirectedEdge.getFirstVertex();
        Vertex second = undirectedEdge.getSecondVertex();
        addVertex(first);
        addVertex(second);
        addAdjacentVertices(first, second);
        addAdjacentVertices(second, first);
    }

    private void addAdjacentVertices(IVertex first, IVertex second) {
        Set<IVertex> firstAdjacent = adjacencyMap.get(first);
        firstAdjacent.add(second);
    }

    @Override
    public void removeEdge(IEdge edge) {
        if (edge.isDirected()) return;  //надо бы вкидывать эксепшн
        UndirectedEdge undirectedEdge = (UndirectedEdge) edge;
        removeVertex(undirectedEdge.getFirstVertex());
        removeVertex(undirectedEdge.getSecondVertex());
        edges.remove(edge);
    }

    @Override
    public IEdge getEdge(IVertex start, IVertex end) {
        if (start.equals(end)) return null;
        for (IEdge edge : edges) {
            if (edge.contains(start) && edge.contains(end)) {
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
